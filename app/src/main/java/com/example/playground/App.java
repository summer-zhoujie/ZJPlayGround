package com.example.playground;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.zj.tools.mylibrary.ZJLog;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class App extends Application {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate() {
        super.onCreate();
        ZJLog.init(true,"summerzhou");

    }
}
