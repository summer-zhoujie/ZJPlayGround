package com.example.playground.drawcolor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;

public class DrawClolorActivity extends AppCompatActivity {


    public static void launch(Context context) {
        context.startActivity(new Intent(context,DrawClolorActivity.class));
    }

    private DrawColorDrawable iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_clolor);
        initView();
    }

    public void clickStart(View view) {
        iv.toExtract();
    }

    private void initView() {
        iv = findViewById(R.id.iv);
    }
}