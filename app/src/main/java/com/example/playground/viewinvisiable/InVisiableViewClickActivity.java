package com.example.playground.viewinvisiable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;

public class InVisiableViewClickActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button5;
    private Button switchVisiable;
    private Button setClickListener;
    private Button clearListener;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, InVisiableViewClickActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_visiable_view_click);
        initView();
    }

    private void initView() {
        button5 = findViewById(R.id.button5);
        switchVisiable = findViewById(R.id.switch_visiable);
        setClickListener = findViewById(R.id.set_click_listener);
        clearListener = findViewById(R.id.clear_listener);
        switchVisiable.setOnClickListener(this);
        setClickListener.setOnClickListener(this);
        clearListener.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_visiable:
                button5.setVisibility(button5.getVisibility()==View.VISIBLE?View.INVISIBLE:View.VISIBLE);
                break;
            case R.id.set_click_listener:
                button5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(InVisiableViewClickActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.clear_listener:
                button5.setOnClickListener(null);
                break;
            default:
                break;
        }
    }
}