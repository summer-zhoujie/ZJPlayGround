package com.example.playground.dialogtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.playground.R;
import com.example.playground.dexload.HiddenApiUtil;
import com.zj.tools.mylibrary.ZJLog;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DialogTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZJLog.d("");
        setContentView(R.layout.activity_dialog_test);
    }

    public void click2Show(View view) {
        new TestDialog(this).show();
    }

    /**
     * 判断当前Activity是否在视图的最前面
     *
     * @param activity
     * @return false 在最前面 (报错时也会返回 false, 吐司和PopWindow弹出时,函数会返回false)
     */
    public static boolean isHasDialogUp(Activity activity) {
        Context curContext = activity;

        Log.d("=summerzhou=", "curcontext = " + curContext);
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
                return curContext != o;
            } else {
                Log.d("=summerzhou=", "错误: viewRoots 为空");
            }

        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            Log.d("=summerzhou=", "错误: " + e.getLocalizedMessage());
        }

        return false;
    }

    /**
     * 点击判断当前Activity是否在栈顶
     *
     * @param view
     */
    public void clickPrint(View view) {
        final boolean hasDialogUp = isHasDialogUp(this);
        Log.d("=summerzhou=", "hasDialogUp = " + hasDialogUp);
        Toast.makeText(this, hasDialogUp + "", Toast.LENGTH_SHORT).show();
    }

    public void clickShowPopwindow(View view) {
        // 生成 View 对象
        PopupWindow popupWindow;
        View popRootView = View.inflate(this, R.layout.dialogtest_popwindow_layout, null);
        popRootView.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean hasDialogUp = isHasDialogUp(DialogTestActivity.this);
                Log.d("=summerzhou=", "hasDialogUp = " + hasDialogUp);
                Toast.makeText(DialogTestActivity.this, hasDialogUp + "", Toast.LENGTH_SHORT).show();
            }
        });
        popupWindow = new PopupWindow(popRootView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAsDropDown(findViewById(R.id.bt_popWindow));
    }

    public void clickShowToast(View view) {
        Toast.makeText(DialogTestActivity.this, "这是一个测试吐司,结果请看log", Toast.LENGTH_LONG).show();
        final boolean hasDialogUp = isHasDialogUp(DialogTestActivity.this);
        Log.d("=summerzhou=", "hasDialogUp = " + hasDialogUp);
    }

    public void jump2CoverActivity(View view) {
        DialogCoverActivity.launch(this);
    }

    public void clickShowSysDialog(View view) {
        final TestDialog dialog = new TestDialog(getApplicationContext());

        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                dialog.getWindow().setType(2038);
            } else {
                dialog.getWindow().setType(2003);
            }
            dialog.show();
        } catch (Exception exp) {
            exp.getMessage();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZJLog.d("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ZJLog.d("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZJLog.d("");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ZJLog.d("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        ZJLog.d("");
    }

    public void clickShowDialog_Popwindow(View view) {
        click2Show(view);
        clickShowPopwindow(view);
    }

    public void clickShowPopwindow_Dialog(View view) {
        clickShowPopwindow(view);
        click2Show(view);
    }
}
