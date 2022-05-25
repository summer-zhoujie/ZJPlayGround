package com.example.playground.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


import com.zj.tools.mylibrary.ZJLog;

import java.util.Random;

public class RunDayProcessViewClear extends View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private float process = 0.0f;
    private float max = 1.0f;
    private int w = -1;
    private int h = -1;
    /**
     * 进度条轨道的颜色
     */
    private final static int BACKGROUND_COLOR = Color.WHITE;//Color.parseColor("#3D51BA");
    /**
     * 进度条的颜色
     */
    private final static int PROCESS_COLOR_START = Color.RED;//Color.parseColor("#F1FF61");
    private final static int PROCESS_COLOR_END = Color.GREEN;//Color.parseColor("#62FFA6");
    private Paint paint_bg;
    private Paint paint_process_bg;
    private Paint paint_bitmap = new Paint();
    private RectF arcRect = null;
    /**
     * 定义进度条的宽度
     */
    int PROCESS_WIDTH;
    /**
     * 定义轨道背景弧形区域绘制的百分比
     */
    private float actPercent = 1.0f;
    /**
     * 轨道背景的起始角度
     */
    private float bgStartAngle = -90;
    /**
     * 轨道背景的绘制弧长
     */
    private float bgSweepAngle;
    private Paint paint_process;
    private float processSweepAngle = 0;
    private Bitmap process_bg_bitmap;
    private Path path = new Path();
    private RectF arcRectOut;
    private RectF arcRectInner;
    private Bitmap process_bg_fore_bitmap;
    private Canvas canvas_2;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func

    public RunDayProcessViewClear(Context context) {
        this(context, null);
    }

    public RunDayProcessViewClear(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RunDayProcessViewClear(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private void init() {

        //TODO Summer
        PROCESS_WIDTH = 30;

        paint_bg = new Paint();
        paint_bg.setColor(BACKGROUND_COLOR);
        paint_bg.setAntiAlias(true);
        paint_bg.setStyle(Paint.Style.STROKE);
        paint_bg.setStrokeWidth(PROCESS_WIDTH);
        paint_bg.setStrokeCap(Paint.Cap.ROUND);

        paint_process = new Paint();
        paint_process.setColor(Color.RED);
        paint_process.setAntiAlias(true);
        paint_process.setStyle(Paint.Style.STROKE);
        paint_process.setStrokeWidth(PROCESS_WIDTH);
        paint_process.setStrokeCap(Paint.Cap.ROUND);

        paint_process_bg = new Paint();
        paint_process_bg.setAntiAlias(true);
        paint_process_bg.setStyle(Paint.Style.FILL);

        path.setFillType(Path.FillType.EVEN_ODD);

        paint_bitmap.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        int padding = PROCESS_WIDTH;
        arcRect = new RectF(0 + padding, 0 + padding, w - padding, w - padding);
        arcRectOut = new RectF(0 , 0 , w , w );
        arcRectInner = new RectF(0 + padding, 0 + padding, w - padding, w - padding);

        // 计算进度和背景圆弧的长度
        bgSweepAngle = actPercent * 360;
        processSweepAngle = bgSweepAngle * process / max;

        LinearGradient linearGradient = new LinearGradient(w/2,0,w/2,h,PROCESS_COLOR_START,PROCESS_COLOR_END, Shader.TileMode.CLAMP);
        paint_process_bg.setShader(linearGradient);
        paint_process.setShader(linearGradient);

        process_bg_bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(process_bg_bitmap);
        canvas.drawRect(0, 0, w, h, paint_process_bg);

        process_bg_fore_bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas_2 = new Canvas(process_bg_fore_bitmap);
        canvas_2.drawColor(0x00FFFFFF);
        ZJLog.d("");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (h != -1 && w != -1 && arcRect != null) {
//            canvas.drawArc(arcRect, bgStartAngle, bgSweepAngle, false, paint_bg);
//            canvas.save();

//            path.reset();
//            path.addArc(arcRectOut, bgStartAngle, processSweepAngle);
//            path.addArc(arcRectInner, bgStartAngle, processSweepAngle);
//            canvas.clipPath(path);
//            canvas.drawRect(0,0,w,h,paint_process_bg);
            ZJLog.d("");
//            canvas.drawBitmap(process_bg_bitmap,0,0,paint_bitmap);
            canvas.drawArc(arcRect, bgStartAngle, processSweepAngle, false, paint_process);
//            paint_process_bg.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//            canvas.drawRect(0, 0, w, h, paint_process_bg);
//            paint_bitmap.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//            canvas.drawBitmap(process_bg_fore_bitmap,0,0,paint_bitmap);
//            canvas.restore();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    /**
     * 更新进度
     *
     * @param newValue 0.0f~1.0f
     */
    public void setProcess(float newValue) {
        //TODO Summer
        Random random = new Random();
        newValue = 0.8f;
        this.process = Math.max(newValue, 0.0f);
        this.process = Math.min(newValue, 1.0f);

        // 重新计算进度圆弧的长度
        processSweepAngle = bgSweepAngle * process / max;
        postInvalidateOnAnimation();
    }
}
