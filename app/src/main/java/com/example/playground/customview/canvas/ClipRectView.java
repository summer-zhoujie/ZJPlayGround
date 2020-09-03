package com.example.playground.customview.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


/**
 * 裁剪内部还是外部
 */
public class ClipRectView extends View {


    final Paint paintBg = new Paint();
    final Paint paintClip = new Paint();
    /**
     * 是否进行外部裁剪
     */
    private boolean clipOut = false;
    /**
     * 是否进行内部裁剪
     */
    private boolean clipIn = false;
    /**
     * 裁剪范围定义
     */
    final Rect rect = new Rect(200, 200, 400, 600);

    public ClipRectView(Context context) {
        this(context, null);
    }

    public ClipRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        paintClip.setColor(getResources().getColor(android.R.color.holo_red_dark));
        paintClip.setStyle(Paint.Style.STROKE);
        paintClip.setStrokeWidth(10);
        paintBg.setColor(getResources().getColor(android.R.color.darker_gray));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (clipOut) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                canvas.clipOutRect(rect);
            }
        }

        if (clipIn) {
            canvas.clipRect(rect);
        }

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);
        canvas.drawRect(rect, paintClip);
    }

    public void clipOut() {
        clipOut = true;
        clipIn = false;
        postInvalidate();
    }

    public void clip() {
        clipOut = false;
        clipIn = true;
        postInvalidate();
    }
}
