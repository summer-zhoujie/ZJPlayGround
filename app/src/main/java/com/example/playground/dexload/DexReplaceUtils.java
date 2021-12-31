package com.example.playground.dexload;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.hotfixdex.ClassBeenHotFixed;
import com.example.playground.tigermachine.effect2.ReflectUtil;
import com.zj.tools.mylibrary.ZJLog;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

public class DexReplaceUtils {

    public static void doReplace(Context context, String addPath) {
        if (TextUtils.isEmpty(addPath)) {
            ZJLog.d("doReplace error, addPath == null");
            return;
        }
        File file = new File(addPath);
        if (!file.canRead() || !file.exists()) {
            ZJLog.d("doReplace error, !file.canRead() || !file.exists()");
            return;
        }

        try {
            Field field_pathList = ReflectUtil.getField(BaseDexClassLoader.class, "pathList");
            field_pathList.setAccessible(true);
            Class<?> class_DexPathList = Class.forName("dalvik.system.DexPathList");
            Field field_dexElements = ReflectUtil.getField(class_DexPathList, "dexElements");
            field_dexElements.setAccessible(true);

            // 获取旧的 Element[]
            ClassLoader parentClassLoad = context.getClassLoader();
            Object object_parentPathList = field_pathList.get(parentClassLoad);
            Object object_dexParentElements = field_dexElements.get(object_parentPathList);

            // 获取新的 Element[]
            DexClassLoader dexClassLoader = new DexClassLoader(addPath, null, null, parentClassLoad);
            Object object_PathList = field_pathList.get(dexClassLoader);
            Object object_dexElements = field_dexElements.get(object_PathList);

            // 合成一个新的 Elements[]
            assert object_dexParentElements != null;
            assert object_dexElements != null;
            Object array_elements_new = combineArray(object_dexElements,object_dexParentElements);

            // 将新合成的 Element[] 塞进 ParentClassLoader 里面
//            parentClassLoad = context.getClassLoader();
//            object_parentPathList = field_pathList.get(parentClassLoad);
            field_dexElements.set(object_parentPathList, array_elements_new);
            ZJLog.d(">>>success!!!");

            Class<?> aClass = Class.forName("com.example.hotfixdex.ClassBeenHotFixed");
            Constructor<?> constructor = aClass.getConstructor();
            Object o = constructor.newInstance();
            Method print = aClass.getMethod("print", Context.class);
            print.invoke(o, context);

        } catch (Throwable e) {
            ZJLog.d("doReplace error, " + Log.getStackTraceString(e));
        }


    }

    /**
     * 数组合并
     */
    private static Object combineArray(Object arrayLhs, Object arrayRhs) {
        Class<?> componentType = arrayLhs.getClass().getComponentType();
        int i = Array.getLength(arrayLhs);// 得到左数组长度（补丁数组）
        int j = Array.getLength(arrayRhs);// 得到原dex数组长度
        int k = i + j;// 得到总数组长度（补丁数组+原dex数组）
        Object result = Array.newInstance(componentType, k);// 创建一个类型为componentType，长度为k的新数组
        System.arraycopy(arrayLhs, 0, result, 0, i);
        System.arraycopy(arrayRhs, 0, result, i, j);
        return result;
    }

}
