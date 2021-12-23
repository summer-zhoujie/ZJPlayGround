package com.example.playground.dragview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.playground.R;

public class DragViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_view);
    }

    public void clickTest(View view) {
        Log.d("DragViewActivity","点击按钮");
        Toast.makeText(this, "点击按钮!", Toast.LENGTH_SHORT).show();
    }
}