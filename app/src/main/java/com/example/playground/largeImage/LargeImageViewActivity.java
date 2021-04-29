package com.example.playground.largeImage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;
import com.example.playground.largeImage.view.MyBigView;

import java.io.IOException;
import java.io.InputStream;

public class LargeImageViewActivity extends AppCompatActivity
{

    public static void launch(Context context) {
        context.startActivity(new Intent(context, LargeImageViewActivity.class));
    }

    private MyBigView mLargeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image_view);

        mLargeImageView = findViewById(R.id.id_largetImageview);
        try
        {
            InputStream inputStream = getAssets().open("111.png");
            mLargeImageView.setImage(inputStream);

        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

}
