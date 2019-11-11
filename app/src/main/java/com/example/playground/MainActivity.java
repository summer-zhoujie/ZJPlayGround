package com.example.playground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.playground.exchangeCard.ExchangeCardActivity;
import com.example.playground.guessidiom.GenerateIdiom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static void main(String[] args) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 创建爱计步成语所需数据库
     * @param view
     */
    public void btGenerateDb(View view) {
        GenerateIdiom.generate(MainActivity.this);
    }

    /**
     * 猜扑克牌
     *
     * @param view
     */
    public void btExchangeCard(View view) {
        startActivity(new Intent(MainActivity.this, ExchangeCardActivity.class));
    }
}
