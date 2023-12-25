package com.example.playground;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        Button click2LoadImage = findViewById(R.id.click2LoadImage);
        ImageView imageView = findViewById(R.id.imageView3);
        click2LoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = "http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg";
                Glide.with(GlideTestActivity.this).load(string).into(imageView);
            }
        });
    }
}