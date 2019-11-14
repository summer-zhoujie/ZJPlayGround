package com.example.playground.strokenanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.playground.R;

public class StrokeTestActivity extends AppCompatActivity {

    StrokenAnimationView strokenAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroke_test);
        strokenAnimationView = findViewById(R.id.stroke_view);
    }

    public void clickStart(View view) {
        strokenAnimationView.startAnimate();
    }

    public void clickStop(View view) {
        strokenAnimationView.pauseAnimate();
    }
}
