package com.example.playground.dialogtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.playground.R;

public class DialogCoverActivity extends AppCompatActivity {

    public static Context contextBelow;

    public static void launch(Context context) {
        contextBelow = context;
        context.startActivity(new Intent(context,DialogCoverActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_cover);
        findViewById(R.id.tv_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestDialog(contextBelow).show();
            }
        });
    }
}