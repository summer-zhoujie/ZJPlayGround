package com.example.stepcount.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.stepcount.beans.StepData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxiaohai on 2018/4/16.
 */

public class LocalDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "com_example_stepcount.db";

    private static final String TABLE_WORKOUT = "table_step";
    private static final String _ID = "id";
    private static final String _DATE = "date";
    private static final String _STEP = "step";
    private static final String _TIME = "time";
    private static final String _STEP_OF_BOOT = "stepofboot";
    private static final String _HAS_REBOOT = "hasreboot";
    public static final int HAS_REBOOT = 1;
    public static final int HAS_NOT_REBOOT = 0;

    private static final String TABLE_WEIGHT = "table_weight";
    private static final String _WEIGHT = "weight";

    private final Object mLock = new Object();
    public static LocalDBHelper mLocalDBHelper;

    public static LocalDBHelper getInstance(Context context) {
        if (mLocalDBHelper == null) {
            mLocalDBHelper = new LocalDBHelper(context.getApplicationContext());
        }
        return mLocalDBHelper;
    }

    public LocalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_WORKOUT + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                _DATE + " INTEGER UNIQUE," +   //20180102
                _STEP + " INTEGER," +
                _TIME + " INTEGER," +
                _STEP_OF_BOOT + " INTEGER," +
                _HAS_REBOOT + " INTEGER" +
                ")");

        db.execSQL("CREATE TABLE " + TABLE_WEIGHT + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                _DATE + " INTEGER UNIQUE," +   //20180102
                _WEIGHT + " FLOAT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertWeight(int date, float weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (hasWeight(db, date)) {
            return updateWeight(db, date, weight);
        } else {
            synchronized (mLock) {
                ContentValues values = new ContentValues();
                values.put(_DATE, date);
                values.put(_WEIGHT, weight);
                long ret = db.insert(TABLE_WEIGHT, null, values);
                db.close();
                return ret;
            }
        }
    }

    private int updateWeight(SQLiteDatabase db, int date, float weight) {
        synchronized (mLock) {
            ContentValues values = new ContentValues();
            values.put(_WEIGHT, weight);
            int ret = db.update(TABLE_WEIGHT, values, _DATE + "=?", new String[]{String.valueOf(date)});
            db.close();
            return ret;
        }
    }

    private boolean hasWeight(SQLiteDatabase db, int date) {
        synchronized (mLock) {
            Cursor cur = db.query(TABLE_WEIGHT, null, _DATE + "=?",
                    new String[]{String.valueOf(date)}, null, null, null);
            if (null != cur && cur.getCount() > 0) {
                cur.close();
                return true;
            } else {
                if (null != cur) cur.close();
                return false;
            }
        }
    }

    public Map<Integer, Float> getWeight() {
        SQLiteDatabase db = this.getWritableDatabase();
        synchronized (mLock) {
            Map<Integer, Float> infoMap = new HashMap<Integer, Float>();
            Cursor cur = db.query(TABLE_WEIGHT, null, null,
                    null, null, null, _DATE + " asc");
            if (null != cur && cur.getCount() > 0) {
                cur.moveToFirst();
                do {
                    int date = cur.getInt(cur.getColumnIndex(_DATE));
                    float weight = cur.getFloat(cur.getColumnIndex(_WEIGHT));
                    infoMap.put(date, weight);
                } while (cur.moveToNext());
                cur.close();
                return infoMap;
            } else {
                if (null != cur) cur.close();
                return null;
            }
        }
    }

    public Map<Integer, Float> getWeightByYear(int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        synchronized (mLock) {
            Map<Integer, Float> infoMap = new HashMap<Integer, Float>();
            String startTime = year + "0101";
            String endTime = year + "1231";
            Cursor cur = db.query(TABLE_WEIGHT, null, _DATE + ">= ? and " + _DATE + "<= ?",
                    new String[]{startTime, endTime}, null, null, _DATE + " asc");
            if (null != cur && cur.getCount() > 0) {
                cur.moveToFirst();
                do {
                    int date = cur.getInt(cur.getColumnIndex(_DATE));
                    float weight = cur.getFloat(cur.getColumnIndex(_WEIGHT));
                    infoMap.put(date, weight);
                } while (cur.moveToNext());
                cur.close();
                return infoMap;
            } else {
                if (null != cur) cur.close();
                return null;
            }
        }
    }

    public float getNearWeight() {
        SQLiteDatabase db = this.getWritableDatabase();
        synchronized (mLock) {
            Cursor cur = db.query(TABLE_WEIGHT, null, null,
                    null, null, null, _DATE + " desc");
            if (null != cur && cur.getCount() > 0) {
                cur.moveToFirst();
                float weight = cur.getFloat(cur.getColumnIndex(_WEIGHT));
                cur.close();
                return weight;
            } else {
                if (null != cur) cur.close();
                return 0f;
            }
        }
    }


    public long updateStep(int date, int step, int time, int stepOfBoot, int hasReboot) {
        SQLiteDatabase db = this.getWritableDatabase();
        synchronized (mLock) {
            ContentValues values = new ContentValues();
            values.put(_DATE, date);
            values.put(_STEP, step);
            values.put(_TIME, time);
            values.put(_STEP_OF_BOOT, stepOfBoot);
            values.put(_HAS_REBOOT, hasReboot);
            int ret = db.update(TABLE_WORKOUT, values, _DATE + "=?", new String[]{String.valueOf(date)});
            db.close();
            return ret;
        }
    }


    public long insertStep(int date, int step, int time, int stepOfBoot, int hasReboot) {
        SQLiteDatabase db = this.getWritableDatabase();
        synchronized (mLock) {
            ContentValues values = new ContentValues();
            values.put(_DATE, date);
            values.put(_STEP, step);
            values.put(_TIME, time);
            values.put(_STEP_OF_BOOT, stepOfBoot);
            values.put(_HAS_REBOOT, hasReboot);
            long ret = db.insert(TABLE_WORKOUT, null, values);
            db.close();
            return ret;
        }
    }


    public List<StepData> getAllStep() {
        SQLiteDatabase db = this.getWritableDatabase();
        synchronized (mLock) {
            List<StepData> infoList = new ArrayList<>();
            Cursor cur = db.query(TABLE_WORKOUT, null, null, null, null, null, _DATE + " desc");
            if (null != cur && cur.getCount() > 0) {
                cur.moveToFirst();
                do {
                    StepData info = new StepData();
                    info.setDate(cur.getInt(cur.getColumnIndex(_DATE)));
                    info.setStep(cur.getInt(cur.getColumnIndex(_STEP)));
                    info.setTime(cur.getInt(cur.getColumnIndex(_TIME)));
                    info.setTotalOfBoot(cur.getInt(cur.getColumnIndex(_STEP_OF_BOOT)));
                    info.setHasReboot(cur.getInt(cur.getColumnIndex(_HAS_REBOOT)));
                    infoList.add(info);
                } while (cur.moveToNext());
                cur.close();
                return infoList;
            } else {
                if (null != cur) cur.close();
                return null;
            }
        }
    }

    public List<StepData> getStepByDate(int startDate, int endDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        synchronized (mLock) {
            List<StepData> infoList = new ArrayList<>();
            Cursor cur = db.query(TABLE_WORKOUT, null, _DATE + ">= ? and " + _DATE + "<= ?",
                    new String[]{String.valueOf(startDate), String.valueOf(endDate)}, null, null, _DATE + " desc");
            if (null != cur && cur.getCount() > 0) {
                cur.moveToFirst();
                do {
                    StepData info = new StepData();
                    info.setDate(cur.getInt(cur.getColumnIndex(_DATE)));
                    info.setStep(cur.getInt(cur.getColumnIndex(_STEP)));
                    info.setTime(cur.getInt(cur.getColumnIndex(_TIME)));
                    info.setTotalOfBoot(cur.getInt(cur.getColumnIndex(_STEP_OF_BOOT)));
                    info.setHasReboot(cur.getInt(cur.getColumnIndex(_HAS_REBOOT)));
                    infoList.add(info);
                } while (cur.moveToNext());
                cur.close();
                return infoList;
            } else {
                if (null != cur) cur.close();
                return null;
            }
        }
    }
}
