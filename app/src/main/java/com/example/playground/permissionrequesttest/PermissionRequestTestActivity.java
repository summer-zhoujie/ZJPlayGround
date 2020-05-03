package com.example.playground.permissionrequesttest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.playground.R;

import java.util.Arrays;


public class PermissionRequestTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request_test);
    }

    /**
     * click 2 request dynamic permissions
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void click2RequestPer(View view) {
        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("=summerzhou=", "permissions=" + Arrays.toString(permissions) + " grantResults=" + Arrays.toString(grantResults));
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
