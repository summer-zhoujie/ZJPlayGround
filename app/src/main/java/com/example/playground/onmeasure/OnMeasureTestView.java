package com.example.playground.onmeasure;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class OnMeasureTestView extends View {

    private static final String TAG = "OnMeasureTestView";

    public OnMeasureTestView(Context context) {
        this(context, null);
    }

    public OnMeasureTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OnMeasureTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int modeW_parent = MeasureSpec.getMode(widthMeasureSpec);
        final int sizeW_parent = MeasureSpec.getSize(widthMeasureSpec);
        final int modeH_parent = MeasureSpec.getMode(heightMeasureSpec);
        final int sizeH_parent = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "onMeasure: MeasureSpec.AT_MOST = " + MeasureSpec.AT_MOST + ", MeasureSpec.EXACTLY = " + MeasureSpec.EXACTLY);
        Log.d(TAG, "onMeasure: modeW_parent = " + modeW_parent + ", modeH_parent = " + modeH_parent);
        Log.d(TAG, "onMeasure: sizeW_parent = " + sizeW_parent + ", sizeH_parent = " + sizeH_parent);
        final ViewGroup.LayoutParams lp = getLayoutParams();
        Log.d(TAG, "onMeasure: " + lp.width + "," + lp.height);

//        int measuredWidth;
//        int measuredHeight;
//
//        if (modeW_parent == MeasureSpec.AT_MOST) {
//
//        }
//
//        setMeasuredDimension();
    }
}
