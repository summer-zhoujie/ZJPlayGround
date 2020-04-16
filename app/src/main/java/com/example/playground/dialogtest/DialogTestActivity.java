package com.example.playground.dialogtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.playground.R;
import com.example.playground.dexload.HiddenApiUtil;
import com.zj.tools.mylibrary.ZjLog;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DialogTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);
    }

    public void click2Show(View view) {
        new TestDialog(this).show();
    }

    /**
     * 判断当前Activity是否在视图的最前面
     *
     * @param activity
     * @return true 在最前面(报错时也会返回 false)
     */
    public static boolean isHasDialogUp(Activity activity) {
        Context curContext = activity;

        HiddenApiUtil.exemptAll();
        WindowManager mWindowManager = (WindowManager) curContext.getSystemService(Context.WINDOW_SERVICE);
        try {
            final Class<?> aClass = Class.forName("android.view.WindowManagerImpl");
            final Field mGlobal = aClass.getDeclaredField("mGlobal");
            mGlobal.setAccessible(true);
            final Object global = mGlobal.get(mWindowManager);

            final Class<?> globalClass = Class.forName("android.view.WindowManagerGlobal");
            final Field mRoots = globalClass.getDeclaredField("mRoots");
            final Field mViews = globalClass.getDeclaredField("mViews");
            mRoots.setAccessible(true);
            mViews.setAccessible(true);
            final ArrayList<View> views = (ArrayList<View>) mViews.get(global);
            final ArrayList viewRoots = (ArrayList<View>) mRoots.get(global);

            final Class<?> rootClass = Class.forName("android.view.ViewRootImpl");
            final Field mContext = rootClass.getDeclaredField("mContext");
            mContext.setAccessible(true);
            if (viewRoots != null && !viewRoots.isEmpty()) {
                final Object viewRoot = viewRoots.get(viewRoots.size() - 1);
                final Object o = mContext.get(viewRoot);
                return curContext == o;
            } else {
                Log.d("=summerzhou=", "错误: viewRoots 为空");
            }

        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            Log.d("=summerzhou=", "错误: " + e.getLocalizedMessage());
        }

        return false;
    }

    public void clickPrint(View view) {
        final boolean hasDialogUp = isHasDialogUp(this);
        Log.d("=summerzhou=", "hasDialogUp = " + hasDialogUp);
        Toast.makeText(this, hasDialogUp + "", Toast.LENGTH_SHORT).show();
    }
}
