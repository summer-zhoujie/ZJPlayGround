package com.example.hotfixdex;

import android.content.Context;
import android.widget.Toast;

import java.time.Duration;

public class ClassBeenHotFixed {
    public void print(Context context) {
        Toast.makeText(context,"有bug", Toast.LENGTH_SHORT).show();
    }
}
