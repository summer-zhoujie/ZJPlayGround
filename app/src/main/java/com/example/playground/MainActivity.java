package com.example.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.playground.dexload.DexLoadTest;
import com.example.playground.exchangeCard.ExchangeCardActivity;
import com.example.playground.guessidiom.GenerateIdiom;
import com.example.playground.strokenanimation.StrokeTestActivity;
import com.example.playground.tigermachine.TigerMachineActivity;
import com.example.playground.tigermachine.effect1.Effect1Activity;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    /**
     * 老虎机
     */
    public void tigermachine(View view) {
        startActivity(new Intent(this, TigerMachineActivity.class));
    }

    /**
     * Dex加载测试
     * @param view
     */
    public void dexLoadTest(View view) {
        DexLoadTest.run();
    }
}
