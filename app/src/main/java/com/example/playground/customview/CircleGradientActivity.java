package com.example.playground.customview;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;

public class CircleGradientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_gradient);
        RunDayProcessViewClear rundayprocessviewclear = findViewById(R.id.RunDayProcessViewClear);
        rundayprocessviewclear.setProcess(1.0f);
    }
}