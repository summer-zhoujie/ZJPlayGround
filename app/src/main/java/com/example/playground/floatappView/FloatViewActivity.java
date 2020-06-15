package com.example.playground.floatappView;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.graphics.PixelFormat;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.playground.R;
import com.zj.tools.mylibrary.ZjLog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FloatViewActivity extends AppCompatActivity {

    View inflate;
    WindowManager windowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_view);
        windowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 1000);
        }
    }

    public void clickShowFloatView(View view) {

        if (inflate != null) {
            return;
        }

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        // 设置为始终
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        // 设置弹出的Window不持有焦点
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 大小
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 位置
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
        // 设置背景透明
        layoutParams.format = PixelFormat.TRANSLUCENT;

        inflate = LayoutInflater.from(this).inflate(R.layout.float_view_layout, null);
        inflate.findViewById(R.id.bt_increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZjLog.d("增加系统时间30s");
                increaseSystemTime("22", "30", "00");
            }
        });
        inflate.findViewById(R.id.bt_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZjLog.d("重置时间");

                new Thread() {
                    @Override
                    public void run() {
                        String webUrl2 = "http://www.baidu.com";//百度
                        final long websiteDatetime = getWebsiteDatetime(webUrl2);
                        if (inflate != null) {
                            inflate.post(new Runnable() {
                                @Override
                                public void run() {
                                    SystemClock.setCurrentTimeMillis(websiteDatetime);
                                }
                            });
                        }

                    }
                }.start();

            }
        });
        windowManager.addView(inflate, layoutParams);
    }

//    public static void main(String[] args) {
//        String webUrl1 = "http://www.bjtime.cn";//bjTime
//
//        String webUrl3 = "http://www.taobao.com";//淘宝
//        String webUrl4 = "http://www.ntsc.ac.cn";//中国科学院国家授时中心
//        String webUrl5 = "http://www.360.cn";//360
//        String webUrl6 = "http://www.beijing-time.org";//beijing-time
//        System.out.println(getWebsiteDatetime(webUrl1) + " [bjtime]");
//        System.out.println(getWebsiteDatetime(webUrl2) + " [百度]");
//        System.out.println(getWebsiteDatetime(webUrl3) + " [淘宝]");
//        System.out.println(getWebsiteDatetime(webUrl4) + " [中国科学院国家授时中心]");
//        System.out.println(getWebsiteDatetime(webUrl5) + " [360安全卫士]");
//        System.out.println(getWebsiteDatetime(webUrl6) + " [beijing-time]");
//    }

    /**
     * 获取指定网站的日期时间
     *
     * @param webUrl
     * @return
     * @author SHANHY
     * @date 2015年11月27日
     */
    private static long getWebsiteDatetime(String webUrl) {
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            return date.getTime();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void increaseSystemTime(String hour, String minute, String ss) {

        final Date date = new Date();
        long newtime = date.getTime() + 30_000;
//            final Class<?> aClass = Class.forName("java.lang.Class");
//            Method method_forName = Class.class.getDeclaredMethod("forName", String.class);
//            Method method_getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
//
//
//            final Class class_SystemClock = (Class) method_forName.invoke(null, "android.os.SystemClock");
//            final Method method_setCurrentTimeMillis = (Method) method_getDeclaredMethod.invoke(class_SystemClock, "setCurrentTimeMillis", new Class[]{long.class});
//
//            method_setCurrentTimeMillis.invoke(null, newtime);
        SystemClock.setCurrentTimeMillis(newtime);
    }

    public void click2ClearFloatView(View view) {
        if (inflate != null && windowManager != null) {
            windowManager.removeView(inflate);
        }
    }
}