package com.example.playground.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.playground.R;
import com.example.playground.customview.clipOutOrIn.DiffOfClipOutOrInActivity;

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
     * 测试裁剪外部和内部的区别
     */
    public void diffOfClipOutAndIn(View view) {
        DiffOfClipOutOrInActivity.launch(this);
    }
}