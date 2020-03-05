package com.example.playground.tigermachine.effect2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.playground.R;
import com.zj.tools.mylibrary.ZjLog;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Effect2Activity extends AppCompatActivity {

    private SlotMachine mSlotMachine;
    private boolean mIsPlaying = false;
    private Random mRandom = new Random();
    private CopyOnWriteArrayList<Bitmap> bitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effect2);

        mSlotMachine = (SlotMachine) findViewById(R.id.slotmachine);

        bitmaps = new CopyOnWriteArrayList<Bitmap>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.slot_01));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.slot_02));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.slot_03));

        mSlotMachine.setData(bitmaps,1, 0, 1);
        mSlotMachine.setSlotMachineListener(new SlotMachine.SlotMachineListener() {
            @Override
            public void onFinish(int pos01, int pos02, int pos03) {
                mIsPlaying = false;
                Toast.makeText(getApplicationContext(), pos01 + "," + pos02 + "," + pos03, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean acceptWinResult(int position) {
                ZjLog.d("win!!! pos = " + position);
                return true;
            }
        });
    }

    public void play(View view) {
        if (mIsPlaying) {
            return;
        }
        mIsPlaying = true;
        mSlotMachine.playMulti(1,2,2);
    }
}
