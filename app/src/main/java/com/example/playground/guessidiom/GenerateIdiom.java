package com.example.playground.guessidiom;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.playground.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

// 生成一个新的成语库
public class GenerateIdiom {

    public static void generate(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.chengyu);
        List<String> result = getString(inputStream);
        Log.d("=summerzhou=", "(MainActivity.onCreate): result.size=" + result.size());

        IdiomSQLiteHelper blcs = new IdiomSQLiteHelper(context, "guessidiom.db", null, 1);
        SQLiteDatabase db = blcs.getWritableDatabase();

        for (int i = 0; i < result.size(); i++) {
            String s = result.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put("idiom", s);
            db.insert("guessidiom", null, contentValues);
            Log.d("=summerzhou=", "(MainActivity.getString): inseert(" + i + ") = " + contentValues.toString());
        }
    }

    public static List<String> getString(InputStream inputStream) {
        List<String> result = new ArrayList<>();
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {

                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
