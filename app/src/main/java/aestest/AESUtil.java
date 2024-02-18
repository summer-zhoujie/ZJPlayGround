package aestest;

import android.content.Context;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    public static byte[] encrypt(byte[] dataBytes, String aesKey, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(aesKey.getBytes("UTF-8"), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return encrypted;
//            return new String(encrypted, "UTF-8");
//            return parseByte2HexStr(encrypted);
        } catch (Exception | Error e) {
//            LogUtil.e(null, "encrypt error : " + e);
        }
        return null;
    }

    public static String encrypt(String data, String aesKey, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(aesKey.getBytes("UTF-8"), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return parseByte2HexStr(encrypted);
        } catch (Exception e) {
//            LogUtil.e(null, "encrypt error : " + e);
        }
        return null;
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

//    public static String desEncrypt(String data) {
//        if (AES_KEY.equals("")) {
//            AES_KEY = new String(Base64.decode((AESUtil.getCode() + "==\n").getBytes(), Base64.DEFAULT));
//        }
//        try {
//            byte[] encrypted1 = Base64.decode(data, Base64.CRLF);
//            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
//            SecretKeySpec keyspec = new SecretKeySpec(AES_KEY.getBytes("UTF-8"), "AES");
//            IvParameterSpec ivspec = new IvParameterSpec(AES_KEY.getBytes());
//
//            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
//
//            byte[] original = cipher.doFinal(encrypted1);
//            return new String(original, "UTF-8");
//        } catch (Exception e) {
////			SDKLogger.e(null, "desEncrypt error : " + e);
//        }
//        return null;
//    }

//    private static String getCode() {
//        String strValue;
//        strValue = "RVdFUldSUkOXNDU2N2k4bw";
//        return strValue;
//    }

    private final static String ALGORITHM_NAME = "AES/CBC/NoPadding";
    private final static int AES_BLOCK_SIZE = 16;
    public static final String AES_PASSWD = "iEP[90v8vdi1xoow";

    public static String decrypt(String encryptedText, String appId) throws Exception {
        String content;
        try {
            byte[] base64dec = Base64.decode(encryptedText, Base64.DEFAULT);

            byte[] keyData = getAesKey(appId, AES_PASSWD);
            SecretKey ks = new SecretKeySpec(keyData, ALGORITHM_NAME);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(base64dec, 0, AES_BLOCK_SIZE);

            Cipher c = Cipher.getInstance(ALGORITHM_NAME);
            c.init(Cipher.DECRYPT_MODE, ks, ivParameterSpec);
            byte[] returnText = c.doFinal(base64dec, AES_BLOCK_SIZE, base64dec.length - AES_BLOCK_SIZE);
            content = new String(returnText, "UTF-8").trim();
        } catch (Exception e) {
//            LogUtil.e("AESUtil", "decrypt error.");
            e.printStackTrace();
            content = null;
        }
        return content;
    }

    public static byte[] getAesKey(String appKey, String passwd) {
        MessageDigest md;
        byte[] aesKey = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(appKey.getBytes());
            md.update(passwd.getBytes());

            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 16; ++i) {
                int value;
                for (int j = 0; j < 2; ++j) {
                    if (j == 0) {
                        value = (bytes[i] >>> 4) & 0xF;
                    } else {
                        value = bytes[i] & 0xF;
                    }

                    if (value <= 9) {
                        sb.append((char) (value + 48));
                    } else {
                        sb.append((char) (value + 87));
                    }
                }
            }
            aesKey = sb.toString().getBytes();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return aesKey;
    }

    public static byte[] decrypt(String encryptedText, Context context) {
        try {
            Cipher c = Cipher.getInstance(ALGORITHM_NAME);
            SecretKeySpec keyspec = new SecretKeySpec(AESTest.KEY.
                    getBytes("UTF-8"), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(AESTest.KEY_IV.getBytes());
            c.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = parseHexStr2Byte(encryptedText);
            byte[] finalByte = c.doFinal(encrypted);
            return  finalByte;
        } catch (Exception e) {
//            LogUtil.e("AESUtils", "decrypt error.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
