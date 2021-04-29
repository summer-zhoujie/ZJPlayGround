package com.example.playground.step;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.example.playground.BuildConfig;
import com.example.playground.R;
import com.example.playground.drawcolor.HandlerUtil;
import com.example.stepcount.StepService;
import com.example.stepcount.interfaces.UpdateUiCallBack;
import com.zj.tools.mylibrary.ZJLog;

public class StepCountActivity extends AppCompatActivity {

    private TextView tvStep;
    private boolean isNeedUnbind;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, StepCountActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);
        initView();
        startStep();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopStep();
    }

    private void stopStep() {
        if (isNeedUnbind) {
            unbindService(conn);
        }
    }

    private void initView() {
        tvStep = findViewById(R.id.tv_step);
    }

    private void startStep() {
        boolean isNeedRequest = requestPermissionIfNeed();
        if (!isNeedRequest) {
            diStartStepService();
        }
    }

    private boolean requestPermissionIfNeed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            int i = ContextCompat.checkSelfPermission(this, "android.permission.ACTIVITY_RECOGNITION");
            if (i != PermissionChecker.PERMISSION_GRANTED) {
                requestPermissions(new String[]{"android.permission.ACTIVITY_RECOGNITION"}, 1000);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1000) {
            boolean hasGrant = false;
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals("android.permission.ACTIVITY_RECOGNITION") && grantResults[i] == PermissionChecker.PERMISSION_GRANTED) {
                    diStartStepService();
                    hasGrant = true;
                    break;
                }
            }

            if (!hasGrant) {
                final Dialog dialog = new AlertDialog
                        .Builder(StepCountActivity.this)
                        .setMessage("我们需要您设置'PermissionChecker.PERMISSION_GRANTED'权限, 来为您计步")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(StepCountActivity.this,"PermissionChecker.PERMISSION_GRANTED")) {
                                    //提示用户前往设置界面自己打开权限
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.setData(Uri.fromParts("package", BuildConfig.APPLICATION_ID, null));
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    requestPermissionIfNeed();
                                }
                            }
                        })
                        .create();
                dialog.show();
            }
        }
    }

    private void diStartStepService() {
        Intent intent = new Intent(this, StepService.class);
        isNeedUnbind = bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService stepService = ((StepService.StepBinder) service).getService();
            //设置步数监听回调
            stepService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount, int time) {
                    ZJLog.d("数据更新 = " + stepCount + "," + time);
                    HandlerUtil.postToMain(new Runnable() {
                        @Override
                        public void run() {
                            if (tvStep != null) {
                                tvStep.setText(stepCount + "");
                            }
                        }
                    });
                }
            });
            tvStep.setText(stepService.getStepCount()+"");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}