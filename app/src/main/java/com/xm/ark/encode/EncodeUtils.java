package com.xm.ark.encode;

public class EncodeUtils {
    static {
        System.loadLibrary("scenesdk-native-lib");
    }
    public static native String aDe1(String str);
}
