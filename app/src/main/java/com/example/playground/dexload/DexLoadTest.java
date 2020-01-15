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
//            HiddenApiUtil.exemptAll();

            Method method_forName = Class.class.getDeclaredMethod("forName", String.class);
            Method method_getConstructor = Class.class.getDeclaredMethod("getConstructor", Class[].class);
            Method method_getConstructors = Class.class.getDeclaredMethod("getConstructors");

            Class class_baseDexClassLoader = (Class) method_forName.invoke(null, "dalvik.system.BaseDexClassLoader");

            Constructor constructor_baseDexClassLoader = (Constructor) method_getConstructor.invoke(class_baseDexClassLoader, (Object) new Class[]{ByteBuffer[].class,ClassLoader.class});
            Log.d("=summerzhou=","constructor = "+constructor_baseDexClassLoader);

            Constructor[] constructors_baseDexClassLoader = (Constructor[]) method_getConstructors.invoke(class_baseDexClassLoader);
            Log.d("=summerzhou=", "constructors = " + Arrays.toString(constructors_baseDexClassLoader));

        } catch (Exception e) {
            Log.d("=summerzhou=", "error = " + Log.getStackTraceString(e));
        }
    }
}

