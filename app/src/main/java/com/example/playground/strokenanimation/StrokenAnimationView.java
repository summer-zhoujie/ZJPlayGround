package com.example.playground.strokenanimation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.playground.R;

import java.util.Timer;
import java.util.TimerTask;

public class StrokenAnimationView extends View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private Paint mPaint;
    private RectF rectStroke;
    private SweepGradient linearGradient;
    // 默认值定义
    private final int strokeWidth_default = dp2px(5);
    private final int rotate_default = 225;
    private final int frameTimeInterval_default = 80;
    private final int frameRotateInterval_default = 5;
    private final boolean isAutoStart_default = true;
    // 线宽
    private int strokeWidth = strokeWidth_default;
    // 光环的起始角度值(最大360度)
    private int rotate = rotate_default;
    // 控制动画刷新间隔(单位: 毫秒)
    private int frameTimeInterval = frameTimeInterval_default;
    // 每次刷新界面前进的角度(单位: 度)
    private int frameRotateInterval = frameRotateInterval_default;
    // 执行动画的定时器
    private Timer timer;
    private boolean isTimerStart = false;
    // 是否默认启动动画(默认: 自启动)
    private boolean isAutoStart = isAutoStart_default;
    // 光环的颜色配置
    private int[] colors = new int[]{
            StringToColor("#FFFFFFFF"),
            StringToColor("#FF78F84E"),
            StringToColor("#FFDB3EF3"),
            StringToColor("#FF60CBF8"),
            StringToColor("#FF9E68F2"),
            StringToColor("#FFFFFFF"),
            StringToColor("#E44D4D"),
            StringToColor("#7AFBEE")
    };
    // 光环颜色分布配置
    private float[] position = new float[]{
            0.11f,
            0.17f,
            0.23f,
            0.42f,
            0.44f,
            0.55f,
            1.0f,
            1.0f,
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Overrides

    public StrokenAnimationView(Context context) {
        this(context, null);
    }

    public StrokenAnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrokenAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseParamsFromXml(context, attrs);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rectStroke = new RectF(strokeWidth, strokeWidth, getMeasuredWidth() - strokeWidth, getMeasuredHeight() - strokeWidth);
        linearGradient = new SweepGradient(getMeasuredWidth() / 2, getMeasuredHeight() / 2, colors, position);
        mPaint.setShader(linearGradient);
        if (isAutoStart) {
            startAnimate();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Matrix matrix = new Matrix();
        matrix.setRotate(rotate, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        linearGradient.setLocalMatrix(matrix);
        canvas.drawRoundRect(rectStroke, 10, 10, mPaint);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // privates

    private void parseParamsFromXml(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StrokenAnimationView);
        try {
            isAutoStart = ta.getBoolean(R.styleable.StrokenAnimationView_is_auto_start, isAutoStart_default);
            frameRotateInterval = ta.getInteger(R.styleable.StrokenAnimationView_frame_rotate_interval, frameRotateInterval_default);
            frameTimeInterval = ta.getInteger(R.styleable.StrokenAnimationView_frame_time_interval, frameTimeInterval_default);
            rotate = ta.getInteger(R.styleable.StrokenAnimationView_rotate_start, rotate_default);
            strokeWidth = ta.getInteger(R.styleable.StrokenAnimationView_stroke_width, strokeWidth_default);
        } catch (RuntimeException e) {
            Log.d("=summerzhou=", "(StrokenAnimationView.parseParamsFromXml): error, just use default config: " + e.getLocalizedMessage());
        }

        ta.recycle();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Utils

    /**
     * #颜色转16进制颜色
     *
     * @param str {String} 颜色
     * @return
     */
    private int StringToColor(String str) {
        return 0xff000000 | Integer.parseInt(str.substring(2), 16);
    }

    /**
     * dp转px
     *
     * @param dpVal dp 值
     * @return px
     */
    private static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, Resources.getSystem().getDisplayMetrics());
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // publics

    /**
     * 开始动画
     */
    public void startAnimate() {
        if (isTimerStart) {
            return;
        }
        isTimerStart = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                rotate = (rotate + frameRotateInterval) % 360;
                postInvalidate();
            }
        }, frameTimeInterval, frameTimeInterval);
    }

    /**
     * 结束动画
     */
    public void pauseAnimate() {
        // 可以多次调用,不做判断
        isTimerStart = false;
        timer.cancel();
    }
}
