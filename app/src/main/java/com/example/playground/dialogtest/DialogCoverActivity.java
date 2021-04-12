package com.example.playground.dialogtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.playground.R;
import com.zj.tools.mylibrary.ZJLog;

public class DialogCoverActivity extends AppCompatActivity {

    public static Context contextBelow;

    public static void launch(Context context) {
        contextBelow = context;
        context.startActivity(new Intent(context,DialogCoverActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZJLog.d("");
        setContentView(R.layout.activity_dialog_cover);
        findViewById(R.id.tv_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestDialog(contextBelow).show();
            }
        });
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
}