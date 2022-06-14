package com.example.playground.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class RunBigProcessView extends View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private float process = 0.0f;
    private float max = 1.0f;
    private int w = -1;
    private int h = -1;
    /**
     * 进度条轨道的颜色
     */
    private static int background_color = Color.parseColor("#1A3E83");
    private static int background_out_color = Color.parseColor("#595EA8FF");
    /**
     * 进度条的颜色
     */
    private static int process_color = Color.parseColor("#FFFFFF");
    private static int thumb_stroke_color = Color.parseColor("#FF12C56F");
    private Paint paint_bg;
    private RectF arcRect = null;
    /**
     * 定义进度条的宽度
     */
    int PROCESS_WIDTH;
    int PROCESS_BG_WIDTH;
    int thumb_radius;
    int thumb_width;
    /**
     * 定义轨道背景弧形区域绘制的百分比
     */
    private float actPercent = 0.70f;
    /**
     * 轨道背景的起始角度
     */
    private float bgStartAngle;
    /**
     * 轨道背景的绘制弧长
     */
    private float bgSweepAngle;
    private Paint paint_process;
    private float processSweepAngle = 0;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func

    public RunBigProcessView(Context context) {
        this(context, null);
    }

    public RunBigProcessView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RunBigProcessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        PROCESS_WIDTH = dp2px(getContext(), 14);
        PROCESS_BG_WIDTH = dp2px(getContext(), 30);
        thumb_radius = dp2px(getContext(), 15);
        thumb_width = dp2px(getContext(), 20);

        paint_bg = new Paint();
        paint_bg.setColor(background_color);
        paint_bg.setAntiAlias(true);
        paint_bg.setStyle(Paint.Style.STROKE);
        paint_bg.setStrokeWidth(PROCESS_WIDTH);
        paint_bg.setStrokeCap(Paint.Cap.ROUND);

        paint_process = new Paint();
        paint_process.setAntiAlias(true);
        paint_process.setStyle(Paint.Style.STROKE);
        paint_process.setStrokeWidth(PROCESS_WIDTH);
        paint_process.setStrokeCap(Paint.Cap.ROUND);


        //对单独的View在运行时阶段禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

    }

    private int dp2px(Context context , float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        int padding = PROCESS_BG_WIDTH/2;
        arcRect = new RectF(0 + padding, 0 + padding, w - padding, w - padding);

        // 计算进度和背景圆弧的长度
        bgStartAngle = 90 + (1 - actPercent) * 360 / 2;
        bgSweepAngle = actPercent * 360;
        processSweepAngle = bgSweepAngle * process / max;

        // 计算thumb的绘制坐标
        float angle = (process / max) * bgSweepAngle + (1 - actPercent) * 360 / 2; // 进度换算角度
        double angleR = (angle / 180.0f) * Math.PI;

        int color_top = Color.parseColor("#FCFF5C");
        int color_mid = Color.parseColor("#56FFEB");
        int color_bottom = Color.parseColor("#3AFFB8");
//        LinearGradient linearGradient = new LinearGradient(w/2,0,w/2,h,new int[]{color_top,color_mid,color_bottom},new float[]{0.3f,0.6f,1.0f}, Shader.TileMode.CLAMP);
        LinearGradient linearGradient = new LinearGradient(w/2,0,w/2,h,color_top, color_bottom,Shader.TileMode.CLAMP);
        paint_process.setShader(linearGradient);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (h != -1 && w != -1 && arcRect != null) {
            paint_bg.setStrokeWidth(PROCESS_BG_WIDTH);
            paint_bg.setColor(background_out_color);
            canvas.drawArc(arcRect, bgStartAngle, bgSweepAngle, false, paint_bg);
            paint_bg.setStrokeWidth(PROCESS_WIDTH);
            paint_bg.setColor(background_color);
            canvas.drawArc(arcRect, bgStartAngle, bgSweepAngle, false, paint_bg);
            canvas.drawArc(arcRect, bgStartAngle, processSweepAngle, false, paint_process);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    /**
     * 更新进度
     *
     * @param newValue 0.0f~1.0f
     */
    public void setProcess(float newValue_1) {
        //TODO Summer
        float newValue = 1.0f;
        this.process = Math.max(newValue, 0.0f);
        this.process = Math.min(newValue, 1.0f);

        // 重新计算进度圆弧的长度
        processSweepAngle = bgSweepAngle * process / max;

        postInvalidateOnAnimation();
    }
}
