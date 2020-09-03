package com.example.playground.customview.paint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;

public class JoinUsageActivity extends AppCompatActivity implements View.OnClickListener {

    private JoinUsageView joinUsageView;
    private Button MITER;
    private Button BEVEL;
    private Button ROUND;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, JoinUsageActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_join_usage);
        initView();
    }

    private void initView() {
        joinUsageView = findViewById(R.id.joinUsageView);
        MITER = findViewById(R.id.MITER);
        BEVEL = findViewById(R.id.BEVEL);
        ROUND = findViewById(R.id.ROUND);

        MITER.setOnClickListener(this);
        BEVEL.setOnClickListener(this);
        ROUND.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MITER:
                joinUsageView.setJoinValueMITER();
                break;
            case R.id.BEVEL:
                joinUsageView.setJoinValueBEVEL();
                break;
            case R.id.ROUND:
                joinUsageView.setJoinValueROUND();
                break;
            default:
                break;
        }
    }
}