package com.example.playground.activityfindviewbyid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.playground.R;

public class FindViewByIdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_view_by_id);

        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new FindViewByIdFragment());
        fragmentTransaction.commit();

    }

    public void find(View view) {
        final TextView viewById = findViewById(R.id.fragment_text);
        if (viewById != null) {
            viewById.setText("activity 修改成功了");
        }
    }
}
