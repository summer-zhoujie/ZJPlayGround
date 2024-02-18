package aestest;

import android.util.Log;

public class AESTest {
    public static final String KEY = "8900667df7f36504";
    public static final String KEY_IV = "bea0241928db2cf7";
    public static final String INPUT = "{\"nw\":29,\"rwvv_id\":\"be4524d7-e950-4eb8-8668-07dc305ec7651213\",\"weid\":\"2d89f13e-cb3d-308f-887b-1af079c20a671231\"}";

    public static void mainMy() {
//        byte[] gzipBody = GZIPUtils.compress(INPUT, "UTF-8");
//        gzipBody = hexStringToByteArray("1F8B080000000000000B1DCB3B0AC3300C00D0BB688E401FDB927399125B3274E9D0211E4AEF5EE8DBDF075E1B4EE907BCF77D3F9E01278C2C554A1866AF842587A3B7E6481653A9E6B4565958E1809DFF21E17DB126CEA1814ABED0DD06F2B5C8FA14BA9AB128C3F70797DB888E70000000");
//        String s = byteArrayToHexString(gzipBody);
//        byte[] encryptedBody = AESUtil.encrypt(gzipBody,
//                KEY,
//                KEY_IV);
//        String s2 = byteArrayToHexString(encryptedBody);
//        Log.d("zhoujie", "encryptedBody = " + encryptedBody);
//        Log.d("zhoujie", "s = " + s);
//        Log.d("zhoujie", "s2 = " + s2);

        String encodedData = "zFGE3wwKwYsEGMVwhIz94hYx5TQgr0AH6WJ6V2pm5y8=";
        String app_id = "c4dc0b26-b861-4bfb-829c-318c73c2b5ee";
        try {
            String decodedData = AESUtil.decrypt(encodedData, app_id);
            Log.d("zhoujie", "decodedData = " + decodedData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();

        // 确保字符串长度是偶数
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Invalid hex string length");
        }

        // 计算字节数组长度
        int byteArrayLength = length / 2;
        byte[] byteArray = new byte[byteArrayLength];

        for (int i = 0; i < length; i += 2) {
            // 截取每两个字符
            String hex = hexString.substring(i, i + 2);

            // 将每两个字符解析为一个字节
            byteArray[i / 2] = (byte) Integer.parseInt(hex, 16);
        }

        return byteArray;
    }

    public static String byteArrayToHexString(byte[] byteArray) {
        StringBuilder hexStringBuilder = new StringBuilder();

        // 遍历字节数组，将每个字节转换为两位十六进制表示
        for (byte b : byteArray) {
            hexStringBuilder.append(String.format("%02X", b));
        }

        return hexStringBuilder.toString();
    }
}
