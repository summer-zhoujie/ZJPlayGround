package com.example.playground.bitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.os.Bundle;

import com.example.playground.R;
import com.zj.tools.mylibrary.ZJLog;

public class BitmapTestActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context,BitmapTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_test);

    }
}