package com.example.playground.customview.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class JoinUsageView extends View {

    private Paint.Join joinValue;
    private Path path;
    private Paint pathPaint;

    public JoinUsageView(Context context) {
        this(context, null);
    }

    public JoinUsageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JoinUsageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

        path = new Path();
        path.moveTo(50, 100);
        path.lineTo(1000, 100);
        path.lineTo(500, 400);

        pathPaint = new Paint();
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
        pathPaint.setStrokeWidth(60);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, pathPaint);
    }

    public void setJoinValueMITER() {
        if (pathPaint != null) {
            pathPaint.setStrokeJoin(Paint.Join.MITER);
            postInvalidate();
        }
    }

    public void setJoinValueROUND() {
        if (pathPaint != null) {
            pathPaint.setStrokeJoin(Paint.Join.ROUND);
            postInvalidate();
        }
    }

    public void setJoinValueBEVEL() {
        if (pathPaint != null) {
            pathPaint.setStrokeJoin(Paint.Join.BEVEL);
            postInvalidate();
        }
    }
}
