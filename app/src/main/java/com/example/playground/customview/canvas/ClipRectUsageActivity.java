package com.example.playground.customview.canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.playground.R;

public class ClipRectUsageActivity extends AppCompatActivity {

    private ClipRectView clipRectView;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ClipRectUsageActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_clip_usage);
        clipRectView = findViewById(R.id.clipOutOrInView);
    }

    public void clipOut(View view) {
        clipRectView.clipOut();
    }

    public void clip(View view) {
        clipRectView.clip();
    }
}