package com.example.playground.dialogtest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.playground.R;

public class TestNoDimDialog extends Dialog {
    public TestNoDimDialog(@NonNull Context context) {
        this(context, 0);
    }

    public TestNoDimDialog(@NonNull final Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.testdialog_nodim_layout);
        Window window = getWindow();
        window.setBackgroundDrawable(null);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.TOP;
        window.setAttributes(attributes);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final boolean hasDialogUp = DialogTestActivity.isHasDialogUp((Activity) context);
//                Log.d("=summerzhou=", "hasDialogUp = " + hasDialogUp);
//                Toast.makeText(context, hasDialogUp + "", Toast.LENGTH_SHORT).show();
//            }
//        });
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
