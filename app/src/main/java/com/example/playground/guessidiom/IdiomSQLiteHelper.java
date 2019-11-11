package com.example.playground.guessidiom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class IdiomSQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "IdiomSQLiteHelper";
    //数据库建表语句
    public static final String sql = "create table guessidiom (id integer primary key autoincrement, idiom text(4))";
    public IdiomSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);//创建数据库调用方法
    }
    /**
     * 第一次创建数据库时调用 在这方法里面可以进行建表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        Log.d("=summerzhou=","(IdiomSQLiteHelper.onCreate): sql="+sql);
    }
    /**
     * 版本更新的时候调用
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("=summerzhou=","(IdiomSQLiteHelper.onUpgrade): ");

    }
}