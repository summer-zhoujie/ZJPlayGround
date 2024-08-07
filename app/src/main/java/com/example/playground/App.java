package com.example.playground;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.multidex.MultiDex;

import com.zj.tools.mylibrary.ZJLog;
import com.zj.tools.mylibrary.ZJToast;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.util.Arrays;

import aestest.AESTest;

public class App extends Application {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate() {
        super.onCreate();
        ZJLog.init(true,"summer");
        ZJToast.init(this);
        MultiDex.install(this);
        AESTest.mainMy();
    }

    class InitConfig {
        private HardwareSource hardwareSource = null;

        public void setHardwareSource(HardwareSource hardwareSource) {
            this.hardwareSource = hardwareSource;
        }
    }

    interface HardwareSource{
        Object getparams1();
        Object getparams2();
        Object getparams3();
        Object getparams4();
    }
}
