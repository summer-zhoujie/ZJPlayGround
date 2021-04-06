package com.example.playground.drawcolor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.playground.R;

public class DrawColorDrawable extends View {

    /**
     * 绘制线条的Paint,即用户手指绘制Path
     */
    private Paint mPaint = new Paint();
    /**
     * 记录用户绘制的Path
     */
    private Path mPath = new Path();
    /**
     * 内存中创建的Canvas
     */
    private Canvas mCanvas;
    /**
     * mCanvas绘制内容在其上
     */
    private Bitmap mBitmap;

    private Bitmap mBgBitmap;
    private boolean toExtract = false;

    public DrawColorDrawable(Context context) {
        this(context, null);
    }

    public DrawColorDrawable(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawColorDrawable(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPath = new Path();

        // 设置画笔
        mPaint.setColor(Color.parseColor("#c0c0c0"));
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        // 背景图
        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.drawcolor_bg);

        // 定义挖空的区域
        mPath = new Path();
        mPath.moveTo(100, 100);
        mPath.lineTo(100, 200);
        mPath.lineTo(200, 200);
        mPath.lineTo(200, 100);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBgBitmap, 0, 0, null);
        Log.d("=summerzhou=", "toExtract = " + toExtract);
        if (toExtract) {
            mCanvas.drawPath(mPath, mPaint);
        }
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 绘制遮盖层
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.parseColor("#c0c0c0"));
    }

    public void toExtract() {
        toExtract = !toExtract;
        invalidate();
    }

}
