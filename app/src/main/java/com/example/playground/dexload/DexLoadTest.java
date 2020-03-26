package com.example.playground.dexload;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class DexLoadTest {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void run() {

        try {
            HiddenApiUtil.exemptAll();

        } catch (Exception e) {
            Log.d("=summerzhou=", "error = " + Log.getStackTraceString(e));
        }
    }
}

