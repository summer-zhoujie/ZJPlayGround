package com.example.playground.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.playground.R;
import com.example.playground.customview.canvas.ClipRectUsageActivity;
import com.example.playground.customview.paint.JoinUsageActivity;

public class CustomViewActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, CustomViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    /**
     * 测试Canvas.clipOut()/Canvas.clip()方法
     */
    public void canvas_ClipOutOrClip(View view) {
        ClipRectUsageActivity.launch(this);
    }

    /**
     * 测试Paint.Join( BEVEL MITER ROUND )值
     */
    public void paint_Join_BEVEL_MITER_ROUND(View view) {
        JoinUsageActivity.launch(this);
    }
}