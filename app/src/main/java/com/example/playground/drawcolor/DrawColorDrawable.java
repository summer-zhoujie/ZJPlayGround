package com.example.playground.drawcolor;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
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
import java.util.Random;

public class DrawColorDrawable extends ScaleView {

    /**
     * 绘制线条的Paint,即用户手指绘制Path
     */
    private Paint mClipPaint = new Paint();
    /**
     * 中间图层是否准备好
     */
    private boolean mMidBitmapIsReady = false;
    /**
     * 背景图
     */
    private Bitmap mBgBitmap;
    /**
     * 中间图层
     */
    private ArrayList<Area> mMidPaths = new ArrayList<>();
    /**
     * 前景图(线框图)
     */
    private ArrayList<Area> mForePaths = new ArrayList<>();

    /**
     * 点击事件处理
     */
    private GestureDetector clickGestureDetector;
    /**
     * 定义点击动画的画笔
     */
    private Bitmap mClipBitmap;
    /**
     * 点击动画的画布
     */
    private Canvas mClipcanvas;
    /**
     * 点击动画是否正在进行中
     */
    private boolean isClickAniming = false;
    /**
     * 前景图是否准备好
     */
    private boolean mForeBitmapIsReady = false;
    /**
     * 定义绘制背景图的画笔
     */
    private Paint mBgBitmapPaint;
    /**
     * 定义绘制中间图层的画笔
     */
    private Paint mMidPaint;
    /**
     * 定义前景图画笔
     */
    private Paint mForePaint;
    private Float clickAnimatedValue;
    private float clickX;
    private float clickY;
    private Paint shaderPaint;


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

        // 透明画布
        shaderPaint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.hint_shader64_planb);
        shaderPaint.setFilterBitmap(true);
        shaderPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

        // 前景图画笔
        mForePaint = new Paint();
        mForePaint.setAntiAlias(true);
        mForePaint.setDither(true);
        mForePaint.setStyle(Paint.Style.FILL);
        mForePaint.setColor(Color.BLACK);

        // 中间图层画笔
        mMidPaint = new Paint();
        mMidPaint.setAntiAlias(false);
        mMidPaint.setDither(false);
        mMidPaint.setStyle(Paint.Style.FILL);
        mMidPaint.setColor(Color.WHITE);

        // 背景图画笔
        mBgBitmapPaint = new Paint();
        mBgBitmapPaint.setFilterBitmap(true); //对Bitmap进行滤波处理
        mBgBitmapPaint.setAntiAlias(false);//设置抗锯齿
        mMidPaint.setDither(true);

        // 设置画笔
        mClipPaint.setAntiAlias(false);
        mClipPaint.setDither(false);
        mClipPaint.setFilterBitmap(true);
        mClipPaint.setColor(Color.WHITE);
        mClipPaint.setStyle(Paint.Style.FILL);

        // 背景图
        Glide.with(getContext()).asBitmap().load("file:///android_asset/drawcolor/demo/ori").into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                mBgBitmap = resource;
                loadForeForeground();
                loadForeground();
            }
        });

        clickGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                float[] pointer = mapPointer(e.getX(), e.getY());
                ZJLog.d("点击了 x=" + pointer[0] + ", y=" + pointer[1]);
                Area aInThePath = PathParserHelper.findAInThePath(mMidPaths, pointer[0], pointer[1]);
                if (aInThePath != null&& aInThePath.status == Area.STATUS.SHADER) {
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

                // 前景图
                for (Path path : paths) {
                    Area e = new Area();
                    e.path = path;
                    e.status = Area.STATUS.DEFAULT;
                    mForePaths.add(e);
                }
                mForeBitmapIsReady = true;
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
    private void doClickAnim(Area aInThePath, final float clickX, final float clickY) {

        isClickAniming = true;
        aInThePath.status = Area.STATUS.CLIPING;
        this.clickX = clickX;
        this.clickY = clickY;

        // 计算path的外接矩形
        RectF rectF = new RectF();
        aInThePath.path.computeBounds(rectF, true);
        ZJLog.d("rectF = " + rectF.toString());
        final float maxRadius = findMaxInstanceToRectBounds(rectF, clickX, clickY);
        ZJLog.d("maxRadius = " + maxRadius);

        // 绘制重合区域
        if (mClipcanvas == null) {
            int width = mBgBitmap.getWidth();
            int height = mBgBitmap.getHeight();
            mClipBitmap = Bitmap.createBitmap(width, height, mBgBitmap.getConfig());
            mClipcanvas = new Canvas(mClipBitmap);
            mClipcanvas.drawRect(new Rect(0,0,width,height),shaderPaint);
        } else {
            mClipcanvas.restore();
        }
        mClipPaint.setXfermode(null);
        mClipcanvas.save();
        mClipcanvas.clipPath(aInThePath.path);

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
                aInThePath.status = Area.STATUS.CLIPED;
                postInvalidate();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.addUpdateListener(animation -> {
            clickAnimatedValue = (Float) animation.getAnimatedValue();
            postInvalidate();
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
     * 加载中间图层
     */
    private void loadForeground() {
        updateScaleParams(getMeasuredWidth(), getMeasuredHeight());
        PathParserHelper.toPathArray(getContext(), "drawcolor/demo/pathdata", new PathParserHelper.ToPathArrayListener() {
            @Override
            public void onSuccess(ArrayList<Path> paths) {

                // 中间图层
                for (Path path : paths) {
                    Area e = new Area();
                    e.path = path;
                    e.status = Area.STATUS.DEFAULT;
                    mMidPaths.add(e);
                }
                mMidBitmapIsReady = true;
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
        clickGestureDetector.onTouchEvent(event);
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
            canvas.drawBitmap(mBgBitmap, 0, 0, mBgBitmapPaint);
        }

        // 画中间图层
        if (mMidBitmapIsReady) {
            for (Area mMidPath : mMidPaths) {

                canvas.save();
                // 挖成透明
                if (mMidPath.status == Area.STATUS.SHADER) {

                    // 用图片绘制
                    canvas.clipPath(mMidPath.path);
                    canvas.drawPath(mMidPath.path, shaderPaint);
                }

                // 点击动画
                else if (mMidPath.status == Area.STATUS.CLIPING) {
                    canvas.clipPath(mMidPath.path);
                    mClipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                    mClipcanvas.drawCircle(clickX, clickY, clickAnimatedValue, mClipPaint);
                    mClipPaint.setXfermode(null);
                    canvas.drawBitmap(mClipBitmap, 0, 0, mClipPaint);
                }
                // 挖空完成
                else if (mMidPath.status == Area.STATUS.CLIPED) {
                    mMidPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                    canvas.drawPath(mMidPath.path, mMidPaint);
                } else {
                    mMidPaint.setXfermode(null);
                    canvas.drawPath(mMidPath.path, mMidPaint);
                }

                canvas.restore();
            }

        }

        // 画前景图
        if (mForeBitmapIsReady) {
            for (Area mForePath : mForePaths) {
                canvas.drawPath(mForePath.path, mForePaint);
            }
        }

    }

    @Override
    protected Bitmap getBgBitmap() {
        return mBgBitmap;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // public - func

    public void randomShader() {
        if (mMidPaths != null && !mMidPaths.isEmpty()) {
            for (Area area : mMidPaths) {
                area.status = Area.STATUS.DEFAULT;
            }
        }
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(40);
            if (mMidPaths != null && mMidPaths.size() > randomIndex) {
                mMidPaths.get(randomIndex).status = Area.STATUS.SHADER;
            }
        }
        postInvalidate();
    }
}
