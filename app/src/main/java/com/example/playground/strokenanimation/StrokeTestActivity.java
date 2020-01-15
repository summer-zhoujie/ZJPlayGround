package com.example.playground.strokenanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.playground.R;

public class StrokeTestActivity extends Activity {

    StrokenAnimationView strokenAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroke_test);
//        strokenAnimationView = findViewById(R.id.stroke_view);
        LinearLayout viewById = findViewById(R.id.parent);

        strokenAnimationView = new StrokenAnimationView(this);
        strokenAnimationView.setLayoutParams(new LinearLayout.LayoutParams(500,300));
        viewById.addView(strokenAnimationView, 0);

    }

    public void clickStart(View view) {
        strokenAnimationView.startAnimate();
    }

    public void clickStop(View view) {
        strokenAnimationView.pauseAnimate();
    }
}
