package com.example.playground.tigermachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.playground.R;
import com.example.playground.tigermachine.effect1.Effect1Activity;

public class TigerMachineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiger_machine);
    }

    /**
     * 效果1
     */
    public void effect1(View view) {
        startActivity(new Intent(this, Effect1Activity.class));
    }
}
