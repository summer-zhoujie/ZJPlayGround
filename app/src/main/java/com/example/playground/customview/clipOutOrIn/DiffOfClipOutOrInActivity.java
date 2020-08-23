package com.example.playground.customview.clipOutOrIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.playground.R;

public class DiffOfClipOutOrInActivity extends AppCompatActivity {

    private ClipOutOrInView clipOutOrInView;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, DiffOfClipOutOrInActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_of_clip_out_or_in);
        clipOutOrInView = findViewById(R.id.clipOutOrInView);
    }

    public void clipOut(View view) {
        clipOutOrInView.clipOut();
    }

    public void clipIn(View view) {
        clipOutOrInView.clipIn();
    }
}