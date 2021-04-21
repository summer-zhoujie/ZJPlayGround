package com.example.playground.drawcolor;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.playground.R;
import com.zj.tools.mylibrary.ZJLog;

import java.util.ArrayList;

public class DrawColorDrawable extends ScaleView {

    /**
     * 绘制线条的Paint,即用户手指绘制Path
     */
    private Paint mPaint = new Paint();
    /**
     * 存放前景图的Bitmap
     */
    private Canvas mCanvas;
    /**
     * 前景图
     */
    private Bitmap mForeBitmap;
    private boolean mForeBitmapIsReady = false;
    /**
     * 背景图
     */
    private Bitmap mBgBitmap;
    /**
     * 前前景图(线框图)
     */
    private Bitmap mForeForeBitmap;
    private ArrayList<Path> mPaths;
    private GestureDetector gestureDetector;
    private Bitmap mClipBitmap;
    private Canvas mClipcanvas;
    /**
     * 点击动画是否正在进行中
     */
    private boolean isClickAniming = false;
    private boolean mForeForeBitmapIsReady = false;
    private Paint mBitmapPaint;

    public DrawColorDrawable(Context context) {
        this(context, null);
    }

    public DrawColorDrawable(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public DrawColorDrawable(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        init();
    }

    private void init() {

        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setFilterBitmap(true); //对Bitmap进行滤波处理
        mBitmapPaint.setAntiAlias(true);//设置抗锯齿

        // 设置画笔
        mPaint.setColor(Color.parseColor("#FF000000"));
        mPaint.setAntiAlias(true);
        mPaint.setDither(false);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        // 背景图
        Glide.with(getContext()).asBitmap().load("file:///android_asset/drawcolor/demo/ori").into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                mBgBitmap = resource;
                loadForeForeground();
                loadForeground();
            }
        });

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                float[] pointer = mapPointer(e.getX(), e.getY());
                ZJLog.d("点击了 x=" + pointer[0] + ", y=" + pointer[1]);
                Path aInThePath = PathParserHelper.findAInThePath(mPaths, pointer[0], pointer[1]);
                if (aInThePath != null) {
                    ZJLog.d("找到点击的区域了, 开始改变颜色");
                    // 执行点击动画
                    doClickAnim(aInThePath, pointer[0], pointer[1]);

                }

                return false;
            }
        });
    }

    private void loadForeForeground() {

        updateScaleParams(getMeasuredWidth(), getMeasuredHeight());
        PathParserHelper.toPathArray(getContext(), "drawcolor/demo/forefore", new PathParserHelper.ToPathArrayListener() {
            @Override
            public void onSuccess(ArrayList<Path> paths) {

                // 前前景图
                mForeForeBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(), mBgBitmap.getHeight(), mBgBitmap.getConfig());
                Canvas canvas = new Canvas(mForeForeBitmap);
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setXfermode(null);
                mPaint.setAntiAlias(true);
                mPaint.setDither(true);
                for (Path path : paths) {
                    canvas.drawPath(path, mPaint);
                }
                mForeForeBitmapIsReady = true;
                ZJLog.d("foreforeW=" + mForeForeBitmap.getWidth() + ",foreforeH=" + mForeForeBitmap.getHeight());
                postInvalidate();

            }

            @Override
            public void onFailed(int code, String msg) {
                ZJLog.d("msg = " + msg);
            }
        });
    }

    /**
     * 执行点击的动画
     */
    private void doClickAnim(Path aInThePath, final float clickX, final float clickY) {

        isClickAniming = true;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        // 计算path的外接矩形
        RectF rectF = new RectF();
        aInThePath.computeBounds(rectF, true);
        ZJLog.d("rectF = " + rectF.toString());
        final float maxRadius = findMaxInstanceToRectBounds(rectF, clickX, clickY);
        ZJLog.d("maxRadius = " + maxRadius);

        // 绘制重合区域
        if (mClipcanvas == null) {
            mClipBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(), mBgBitmap.getHeight(), mBgBitmap.getConfig());
            mClipcanvas = new Canvas(mClipBitmap);
        } else {
            mClipcanvas.restore();
        }
        mPaint.setXfermode(null);
        mPaint.setColor(Color.GREEN);
        mClipcanvas.save();
        mClipcanvas.clipPath(aInThePath);

        ValueAnimator animator = ValueAnimator.ofFloat(0, maxRadius);
        animator.setDuration(250);
        animator.setStartDelay(0);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isClickAniming = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.addUpdateListener(animation -> {
            mPaint.setXfermode(null);
            mClipcanvas.drawCircle(clickX, clickY, (Float) animation.getAnimatedValue(), mPaint);
            invalidate();
        });
        animator.start();

    }

    private float findMaxInstanceToRectBounds(RectF rectF, float clickX, float clickY) {
        float instanceToTop = clickY - rectF.top;
        float instanceBottom = rectF.bottom - clickY;
        float instanceRight = rectF.right - clickX;
        float instanceLeft = clickX - rectF.left;
        float instanceTopLeft = (float) Math.sqrt((instanceToTop * instanceToTop) + (instanceLeft * instanceLeft));
        float instanceTopRight = (float) Math.sqrt((instanceToTop * instanceToTop) + (instanceRight * instanceRight));
        float instanceBottomLeft = (float) Math.sqrt((instanceBottom * instanceBottom) + (instanceLeft * instanceLeft));
        float instanceBottomRight = (float) Math.sqrt((instanceBottom * instanceBottom) + (instanceRight * instanceRight));
        float result = Math.max(instanceTopLeft, instanceTopRight);
        result = Math.max(result, instanceBottomLeft);
        result = Math.max(result, instanceBottomRight);
        return result;
    }

    /**
     * 加载前景图
     */
    private void loadForeground() {
        updateScaleParams(getMeasuredWidth(), getMeasuredHeight());
        PathParserHelper.toPathArray(getContext(), "drawcolor/demo/pathdata", new PathParserHelper.ToPathArrayListener() {
            @Override
            public void onSuccess(ArrayList<Path> paths) {

                // 前景图
                ZJLog.d("originW=" + mBgBitmap.getWidth() + ",originH=" + mBgBitmap.getHeight());
                mForeBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(), mBgBitmap.getHeight(), mBgBitmap.getConfig());
                mCanvas = new Canvas(mForeBitmap);
                mCanvas.drawColor(Color.parseColor("#FFD1C343"));
                mPaint.setColor(Color.TRANSPARENT);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setXfermode(null);
                mPaths = paths;
                for (Path path : paths) {
                    mCanvas.drawPath(path, mPaint);
                }

                ZJLog.d("foreW=" + mForeBitmap.getWidth() + ",foreH=" + mForeBitmap.getHeight());
                mForeBitmapIsReady = true;
                postInvalidate();

            }

            @Override
            public void onFailed(int code, String msg) {
                ZJLog.d("msg = " + msg);
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // abstract-func


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 点击动画进行中不响应其他事件
        if (isClickAniming) {
            return true;
        }
        boolean b = super.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return b;
    }

    @Override
    protected void onScaleChanged(float mBaseScale) {
        ZJLog.d("mBaseScale = " + mBaseScale);

    }

    @Override
    protected void drawScaleBitmap(Canvas canvas) {

        // 画背景图
        if (mBgBitmap != null) {
            canvas.drawBitmap(mBgBitmap, 0, 0, null);
        }

        // 画前景图
        if (mForeBitmap != null && mForeBitmapIsReady) {
            canvas.drawBitmap(mForeBitmap, 0, 0, null);
        }

        // 画前前景图
        if (mForeForeBitmap != null && mForeForeBitmapIsReady) {
            canvas.drawBitmap(mForeForeBitmap, 0, 0, mBitmapPaint);
        }

        // 点击动画刷新
        if (mClipBitmap != null) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            mPaint.setColor(Color.GREEN);
            mCanvas.drawBitmap(mClipBitmap, 0, 0, mPaint);
        }
    }

    @Override
    protected Bitmap getBgBitmap() {
        return mBgBitmap;
    }
}
