package com.example.playground.dialogtest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.playground.R;

import java.util.ArrayList;

public class TestDialog extends Dialog {
    public TestDialog(@NonNull Context context) {
        this(context, 0);
    }

    public TestDialog(@NonNull final Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.testdialog_layout);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean hasDialogUp = DialogTestActivity.isHasDialogUp((Activity) context);
                Log.d("=summerzhou=", "hasDialogUp = " + hasDialogUp);
                Toast.makeText(context, hasDialogUp + "", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestNoDimDialog(v.getContext()).show();
            }
        });
    }

    private void changeDim(float dimAmount) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = dimAmount;
        window.setAttributes(attributes);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public void show(float dimAmount) {
        changeDim(dimAmount);
        super.show();
    }
}
