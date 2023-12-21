package com.example.playground;

import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION_CODES.GINGERBREAD;
import static android.os.Build.VERSION_CODES.M;
import static android.os.Build.VERSION_CODES.O;
import static android.os.Build.VERSION_CODES.P;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

class OpenUDIDHelper {

    private static final String SHARED_PREF = "md-openudid-data";
    private static final String INSTALLATION = ".MD_OPENUDID_INSTALLATION";
    // 从 target >= Android Q 开始，此路径不可访问
    private static final String EXT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static volatile String sID = null;

    public static String getOpenUDID(Context context) {
        return id(context.getApplicationContext());
    }

    private static String id(Context context) {
        String id;
        synchronized (OpenUDIDHelper.class) {
            if (sID == null) {
                try {
                    File file = null;
                    // get installation file from cache
                    String cachedId = readInstallation(context);
                    if (!TextUtils.isEmpty(cachedId)) {
                        sID = cachedId;
                        syncInstallation(context, cachedId);
                    } else {
                        // Step 1: generate OpenUDID
                        //log("---> Step 1: generate OpenUDID");
                        sID = generateId(context);

                        // Step 2: write installation file to external storage
                        //log("---> Step 2: write installation file to sdcard");
                        writeInstallationFile(context, sID);

                        // Step 3: write installation file to sp
                        //log("---> Step 3: write installation file to media");
                        writeIdToMedia(context, sID);

                        writeInstallationSP(context, sID);
                    }
                } catch (Exception | Error e) {
                    e.printStackTrace();
                }
            }
            id = sID;
        }
        //log(String.format("#### The OpenUDID is [%s] ####", id));
        return id;
    }

    private static String generateId(Context context) {
        return new UniqueId(context).getId();
    }

    private static void log(String message) {

    }

    private static boolean hasPermission(Context context, String permission) {
        int res = context.checkCallingOrSelfPermission(permission);
        //log("permission: " + permission + " \t\t " +(res == PackageManager.PERMISSION_GRANTED ? "[GRANTED]" : "[DENIED]"));
        return res == PackageManager.PERMISSION_GRANTED;
    }

    static boolean hasPermissions(Context context, String... permissions) {
        boolean hasAllPermissions = true;
        for (String permission : permissions) {
            //you can return false instead of assigning, but by assigning you can log all permission values
            if (!hasPermission(context, permission)) {
                hasAllPermissions = false;
            }
        }
        return hasAllPermissions;
    }

    private static String readInstallation(Context context) {
        // Step 1: read installation file from sp
        //log("<--- Step 1: read installation file from sp");
        String id = readInstallationSP(context);
        if (!TextUtils.isEmpty(id)) {
            //log("Hit OpenUDID from sp.");
            return id;
        }
        //log("Failed get OpenUDID from sp.");

        // Step 2: if sp installation file not exists, read it from external storage
        //log("<--- Step 2: read installation file from sdcard");

        File file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //log("Android M+");
            if (hasPermissions(context, READ_EXTERNAL_STORAGE)) {
                //log("has READ_EXTERNAL_STORAGE permission");
                file = new File(EXT_PATH, INSTALLATION);
            } else {
                //log("don't has READ_EXTERNAL_STORAGE permission");
            }
        } else {
            //log("Android M-");
            file = new File(EXT_PATH, INSTALLATION);
        }

        if (file != null) {
            if (file.exists()) {
                //log("sdcard file exists");
                try {
                    id = readInstallationFile(file);
                } catch (InstallationException e) {
                    //log(String.format("Failed to read installation file with error %s.",e.getMessage()));
                }
            } else {
                //log("sdcard file not exists");
            }
        } else {
            //log("sdcard file is null");
        }

        if (!TextUtils.isEmpty(id)) {
            //log("Hit OpenUDID from sdcard.");
            return id;
        }
        //log("Failed to read OpenUDID from sdcard.");

        // Step 3: read from media uri
        //log("<--- Step 3: read from media uri");
        id = readIdFromMedia(context);

        if (!TextUtils.isEmpty(id)) {
            //log("Hit OpenUDID from media uri.");
        } else {
            //log("failed read OpenUDID from media uri.");
        }
        return id;
    }

    private static final String MEDIA_DISPLAY_NAME = "EVT_IO_INSTALLATION";

    private static String readIdFromMedia(Context context) {
        //log("readIdFromMedia...");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //log("Android Q+");
            if (hasPermissions(context, READ_EXTERNAL_STORAGE)) {
                //log("has READ_EXTERNAL_STORAGE permission");
            } else {
                //log("don't has READ_EXTERNAL_STORAGE permission");
                return "";
            }
        } else {
            //log("Android Q-, can't read");
            return "";
        }

        Uri uri = null;
        try {
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null,
                    MediaStore.Audio.Media.DISPLAY_NAME.concat(" like ? "),
                    new String[]{MEDIA_DISPLAY_NAME.concat("%")},
                    null);
            while (cursor.moveToNext()) {
                // String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                // String releativePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.RELATIVE_PATH));
                uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                //log("find same displayName, uri: " + uri.toString());
                break;
            }
        } catch (Exception | Error e) {
            e.printStackTrace();
        }

        String id = null;
        if (uri != null) {
            InputStream inputStream = null;
            try {
                inputStream = context.getContentResolver().openInputStream(uri);
                if (inputStream != null) {
                    id = readString(inputStream);
                }
            } catch (Exception | Error e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Exception | Error e) {
                    e.printStackTrace();
                }
            }
        } else {
            //log("uri is null");
        }
        return id;
    }

    private static void writeIdToMedia(Context context, String id) {
        if (!TextUtils.isEmpty(readIdFromMedia(context))) {
            //log("cancel writeIdToMedia, hasMediaUri");
            return;
        }
        //log("writeIdToMedia...");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            int targetSdkVersion = context.getApplicationInfo().targetSdkVersion;
            //log("Android Q(29)+, targetSdkVersion: " + targetSdkVersion);
            if (targetSdkVersion >= Build.VERSION_CODES.Q
                    || hasPermissions(context, WRITE_EXTERNAL_STORAGE)) {
                //log("targetSdkVersion >= 29, or has WRITE_EXTERNAL_STORAGE permission");
                OutputStream os = null;
                try {
                    ContentResolver resolver = context.getContentResolver();

                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Audio.Media.DISPLAY_NAME, MEDIA_DISPLAY_NAME);
                    //设置文件类型
                    values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mpeg");
                    //EXTERNAL_CONTENT_URI代表外部存储器
                    Uri external = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    //insertUri表示文件保存的uri路径
                    Uri result = resolver.insert(external, values);
                    //log("insert finish, result uri: " + result.toString());

                    //log("write id to media uri");

                    os = resolver.openOutputStream(result);
                    if (os == null) {
                        return;
                    }
                    os.write(id.getBytes());
                } catch (Exception | Error e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                    } catch (Exception | Error e) {
                        e.printStackTrace();
                    }
                }
            } else {
                //log("targetSdkVersion < 29, and don't has READ_EXTERNAL_STORAGE permission");
            }
        } else {
            //log("Android Q-, can't write");
        }
    }

    private static String readString(InputStream from) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(from, "utf-8");
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(reader);
            String read = br.readLine();
            while (read != null) {
                sb.append(read);
                read = br.readLine();
            }
            return sb.toString();
        } catch (Exception | Error e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String readInstallationFile(File f) throws InstallationException {
        RandomAccessFile raf = null;
        byte[] bytes;
        try {
            raf = new RandomAccessFile(f, "r");
            bytes = new byte[(int) raf.length()];
            raf.readFully(bytes);
            //log("Success to read id from sdcard.");
        } catch (Throwable th) {
            throw new InstallationException(th);
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (Exception | Error e) {
                e.printStackTrace();
            }
        }
        return bytes != null ? new String(bytes) : null;
    }

    private static String readInstallationSP(Context context) {
        return readInstallationSP(context, null);
    }

    private static String readInstallationSP(Context context, String id) {
        return context.getSharedPreferences(SHARED_PREF, 0).getString(INSTALLATION, id);
    }

    private static void writeInstallationFile(Context context, String id) {
        FileOutputStream out = null;
        try {
            File file = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (hasPermissions(context, WRITE_EXTERNAL_STORAGE)) {
                    file = new File(EXT_PATH, INSTALLATION);
                }
            } else {
                file = new File(EXT_PATH, INSTALLATION);
            }

            if (file != null && !file.exists()) {
                out = new FileOutputStream(file);
                out.write(id.getBytes());
                if (file.exists() && file.length() > 0) {
                    //log("Success to write installation file to sdcard");
                } else {
                    //log("Failed to write installation file to sdcard");
                }
            }
        } catch (Exception | Error e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (Exception | Error e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeInstallationSP(Context context, String id) {
        if (TextUtils.isEmpty(id)) {
            throw new NullPointerException("Oops!!! OpenUDID is empty.");
        }

        Editor editor = context.getSharedPreferences(SHARED_PREF, 0).edit();
        editor.putString(INSTALLATION, id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    private static void syncInstallation(Context context, String id) {
        if (TextUtils.isEmpty(id)) {
            throw new NullPointerException("Oops!!! OpenUDID is empty.");
        }

        String cachedId = readInstallationSP(context);
        if (TextUtils.isEmpty(cachedId)) {
            //log("<--> sync external installation file to sp");
            writeInstallationSP(context, id);
        } else {
            //log("<--> sync sp installation file to sdcard");
            writeInstallationFile(context, cachedId);

            //log("<--> sync sp installation file to media uri");
            writeIdToMedia(context, id);
        }
    }

    /**
     * Unique Hardware Id.
     * This wraps both the fetching of the DEVICE_ID with knowledge.
     */
    static class UniqueId {
        private static final String UUID_EMPTY = "00000000-0000-0000-0000-000000000000";
        private String uniqueId;

        @SuppressLint("HardwareIds")
        UniqueId(Context context) {
            uniqueId = UUID_EMPTY;

            String id = getUniqueDeviceId(context);
            try {
                if (TextUtils.isEmpty(id)) {
                    uniqueId = UUID.randomUUID().toString();
                } else {
                    uniqueId = UUID.nameUUIDFromBytes(id.getBytes("UTF-8")).toString();
                }

                if (TextUtils.isEmpty(uniqueId)) {
                    throw new NullPointerException("Oops!!! Null pointer at unique ID.");
                }
            } catch (Throwable th) {
                //log("Error message: " + th.getMessage());
                uniqueId = UUID.randomUUID().toString();
            }
            //log(String.format("The Unique ID is [%s]", uniqueId));
        }

        String getId() {
            return uniqueId;
        }

        @Override
        public boolean equals(Object other) {
            // self check
            if (this == other)
                return true;

            // null check
            if (other == null)
                return false;

            // type check and cast
            if (getClass() != other.getClass())
                return false;

            UniqueId uidOther = (UniqueId) other;

            // field comparison
            return this.uniqueId.equals(uidOther.uniqueId);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            final int result = 2;

            return (prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode()));
        }
    }

    public static class InstallationException extends Exception {

        public InstallationException(Throwable th) {
            super(th);
        }
    }

    private static String getUniqueDeviceId(Context context) {
        String result = null;

        if (Build.VERSION.SDK_INT < M) {
            //log("Android <= Android 5");
            result = getImei(context);
            //log("getUniqueDeviceId, imei: " + result);
            if (TextUtils.isEmpty(result)) {
                result = getMacAddressFromWifiManager(context);
                //log("getUniqueDeviceId, mac from wifi manager: " + result);
                if (TextUtils.isEmpty(result)) {
                    result = getAndroidID(context);
                    //log("getUniqueDeviceId, AndroidID: " + result);
                    if (TextUtils.isEmpty(result)) {
                        result = getSerial();
                        //log("getUniqueDeviceId, Serial: " + result);
                    }
                }
            }
        } else if (Build.VERSION.SDK_INT == M) {
            //log("Android = Android 6");
            result = getImei(context);
            //log("getUniqueDeviceId, imei: " + result);
            if (TextUtils.isEmpty(result)) {
                result = getMacAddressFromNetworkInterface();
                //log("getUniqueDeviceId, mac from network interface: " + result);
                if (TextUtils.isEmpty(result)) {
                    if (checkSelfPermission(context, ACCESS_WIFI_STATE)) {
                        result = getMacAddressFromFile();
                        //log("getUniqueDeviceId, mac from file: " + result);
                    } else {
                        result = getMacAddressFromWifiManager(context);
                        //log("getUniqueDeviceId, mac from wifi manager: " + result);
                    }
                }
                if (TextUtils.isEmpty(result)) {
                    result = getAndroidID(context);
                    //log("getUniqueDeviceId, AndroidID: " + result);
                    if (TextUtils.isEmpty(result)) {
                        result = getSerial();
                        //log("getUniqueDeviceId, Serial: " + result);
                    }
                }
            }
        } else if (Build.VERSION.SDK_INT <= P) {
            //log("Android 7 <= Android <= Android 9");
            result = getImei(context);
            //log("getUniqueDeviceId, imei: " + result);
            if (TextUtils.isEmpty(result)) {
                result = getSerial();
                //log("getUniqueDeviceId, Serial: " + result);
                if (TextUtils.isEmpty(result)) {
                    result = getAndroidID(context);
                    //log("getUniqueDeviceId, AndroidID: " + result);
                    if (TextUtils.isEmpty(result)) {
                        result = getMacAddressFromNetworkInterface();
                        //log("getUniqueDeviceId, mac from network interface: " + result);
                        if (TextUtils.isEmpty(result)) {
                            result = getMacAddressFromWifiManager(context);
                            //log("getUniqueDeviceId, mac from wifi manager: " + result);
                        }
                    }
                }
            }
        } else {
            //log("Android >= Android 10");
            result = getAndroidID(context);
            //log("getUniqueDeviceId, AndroidID: " + result);
        }

//        if (TextUtils.isEmpty(result)) {
//            result = getWidevineId();
//        }

        //log("The unique device id is: " + result);
        return result;
    }

    private static String getMacAddressFromFile() {
        String[] arr = {"/sys/class/net/wlan0/address",
                "/sys/class/net/eth0/address",
                "/sys/devices/virtual/net/wlan0/address"};
        for (byte b = 0; b < arr.length; b++) {
            try {
                String result = read(arr[b]);
                if (!TextUtils.isEmpty(result)) {
                    return result;
                }
            } catch (Throwable th) {
            }
        }
        return null;
    }

    private static String read(String param) {
        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(param);
            if (fr != null) {
                try {
                    br = new BufferedReader(fr, 1024);
                    return br.readLine();
                } finally {
                    if (fr != null) {
                        try {
                            fr.close();
                        } catch (IOException e) {
                        }
                    }
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    private static String getMacAddressFromNetworkInterface() {
        try {
            Enumeration enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) enumeration.nextElement();
                if ("wlan0".equals(networkInterface.getName())
                        || "eth0".equals(networkInterface.getName())) {
                    byte[] arrayOfByte = networkInterface.getHardwareAddress();
                    if (arrayOfByte == null || arrayOfByte.length == 0) {
                        return null;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    for (byte b : arrayOfByte) {
                        stringBuilder.append(String.format("%02X:", new Object[]{Byte.valueOf(b)}));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString().toLowerCase(Locale.getDefault());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    private static String getSerial() {
        String result = null;
        if (Build.VERSION.SDK_INT >= GINGERBREAD && Build.VERSION.SDK_INT < O) {
            result = Build.SERIAL;
        } else if (Build.VERSION.SDK_INT >= O) {
            try {
                Class clazz = Class.forName("android.os.Build");
                Method method = clazz.getMethod("getSerial", new Class[0]);
                result = (String) method.invoke(clazz, new Object[0]);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if ("unknown".equalsIgnoreCase(result)) {
            return null;
        }
        return result;
    }

    private static String getAndroidID(Context context) {
        String result = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if ("9774d56d682e549c".equals(result)) {
            return null;
        }
        return result;
    }

    private synchronized static String getImei(Context context) {

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = getImeis(tm);
        if (TextUtils.isEmpty(imei)) {
            imei = getDeviceIds(tm);
        }
        if (TextUtils.isEmpty(imei)) {
            imei = getMeids(tm);
        }

        return imei;
    }

    private static String getMeids(TelephonyManager tm) {
        List<String> meidList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            String meid = getMeidByIndex(tm, i);
            if (!tryAddToStringList(meidList, meid)) {
                break;
            }
            //log(String.format("meid[%d]: %s", i, meid));
        }
        if (meidList.size() == 0) {
            return getDefaultMeid(tm);
        }
        return TextUtils.join(",", meidList);
    }

    @SuppressLint("MissingPermission")
    private static String getDefaultMeid(TelephonyManager tm) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return tm.getMeid();
            }
        } catch (Throwable th) {
            //log(String.format("Couldn't read default MEID: %s", th.getMessage()));
        }
        return null;
    }

    @SuppressLint("MissingPermission")
    private static String getMeidByIndex(TelephonyManager tm, int index) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return tm.getMeid(index);
            }
        } catch (Throwable th) {
            //log(String.format("Couldn't read MEID in position %d: %s", index, th.getMessage()));
        }
        return null;
    }

    private static String getDeviceIds(TelephonyManager tm) {
        List<String> telephonyIdList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            String deviceId = getDeviceIdByIndex(tm, i);
            if (!tryAddToStringList(telephonyIdList, deviceId)) {
                break;
            }
            //log(String.format("deviceId[%d]: %s", i, deviceId));
        }
        if (telephonyIdList.size() == 0) {
            return getDefaultDeviceId(tm);
        }
        return TextUtils.join(",", telephonyIdList);
    }

    @SuppressLint("MissingPermission")
    private static String getDefaultDeviceId(TelephonyManager tm) {
        try {
            return tm.getDeviceId();
        } catch (Throwable th) {
            //log(String.format("Couldn't read default Device Id: %s", th.getMessage()));
        }
        return null;
    }

    @SuppressLint("MissingPermission")
    private static String getDeviceIdByIndex(TelephonyManager tm, int index) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return tm.getDeviceId(index);
            }
        } catch (Throwable th) {
            //log(String.format("Couldn't read Device Id in position %d: %s", index,th.getMessage()));
        }
        return null;
    }

    private static String getImeis(TelephonyManager tm) {
        List<String> imeis = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            String imei = getImeiByIndex(tm, i);
            if (!tryAddToStringList(imeis, imei)) {
                break;
            }
            //log(String.format("imei[%d]: %s", i, imei));
        }
        if (imeis.size() == 0) {
            return getDefaultImei(tm);
        }
        return TextUtils.join(",", imeis);
    }

    private static boolean tryAddToStringList(List<String> list, String value) {
        if (value == null) {
            return false;
        }
        if (list.contains(value)) {
            return false;
        }
        return list.add(value);
    }

    @SuppressLint("MissingPermission")
    private static String getDefaultImei(TelephonyManager tm) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return tm.getImei();
            }
        } catch (Throwable th) {
            //log(String.format("Couldn't read default IMEI: %s", th.getMessage()));
        }
        return null;
    }

    @SuppressLint("MissingPermission")
    private static String getImeiByIndex(TelephonyManager tm, int index) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return tm.getImei(index);
            }
        } catch (Throwable th) {
            //log(String.format("Couldn't read IMEI in position %d: %s", index, th.getMessage()));
        }
        return null;
    }

    private static String getMacAddressFromWifiManager(Context context) {
        try {
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (checkSelfPermission(context, ACCESS_WIFI_STATE)) {
                WifiInfo wifiInfo = wm.getConnectionInfo();
                return wifiInfo.getMacAddress();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    private static boolean checkSelfPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= M) {
            if ((hasPermissions(context, permission))) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
