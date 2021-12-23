package com.example.playground.onmeasure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.playground.R;

public class OnMeasureTestActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context,OnMeasureTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_measure_test);
    }
}