package com.example.playground;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.playground.dexload.DexLoadTest;
import com.example.playground.exchangeCard.ExchangeCardActivity;
import com.example.playground.guessidiom.GenerateIdiom;
import com.example.playground.strokenanimation.StrokeTestActivity;

import org.reactivestreams.Subscriber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableOperator;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.schedulers.TestScheduler;

public class MainActivity extends Activity {

    public static void main(String[] args) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dex load test
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DexLoadTest.run();
        }
    }


    /**
     * 创建爱计步成语所需数据库
     *
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

    /**
     * Rxjava测试
     *
     * @param view
     */
    public void testRxJava(View view) {
        int maxnumber = 10000;
        String[] strings = new String[maxnumber];
        for (int i = 0; i < maxnumber; i++) {
            strings[i] = "" + i;
        }
        FlowableFromArray flowableFromArray = new FlowableFromArray(strings);
        final Disposable subscribe = flowableFromArray
                .subscribeOn(Schedulers.computation())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        Log.d("=summerzhou=", "(MainActivity.accept): o=" + o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("=summerzhou=", "(MainActivity.Error): msg=" + throwable.getLocalizedMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("=summerzhou=", "(MainActivity.complete): ");
                    }
                });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("=summerzhou=", "(MainActivity.dispose): ");
                subscribe.dispose();
            }
        }, 300);

    }

    /**
     * 测试矩形框动画
     *
     * @param view
     */
    public void testStrokeAnimation(View view) {
        startActivity(new Intent(this, StrokeTestActivity.class));
    }
}
