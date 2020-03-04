package com.example.playground.tigermachine.effect1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Scroller;

/**
 * 老虎机控件
 * Tiger Machine control 
 * 
 * laozhang
 */
public class TigerMachineView extends View {

	public TigerMachineView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public TigerMachineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TigerMachineView(Context context) {
		super(context);
		init();
	}

	private void init() {
		mScroller = new Scroller(getContext(), new OvershootInterpolator(0.5f));

		if (null == sDefChars) {
			int len = '9' - '0' + 1;
			sDefChars = new char[len];
			for (int i = 0; i < len; i++) {
				sDefChars[i] = (char) ('0' + i);
			}
		}
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.RED);
	}

	private Scroller mScroller;
	private static char[] sDefChars = null;
	private Paint mPaint;
	private int mCurIdx = 0;

	/**
	 * 设置数字颜色
	 * set digit color
	 *  
	 * @param c
	 * 要设置的颜色. 
	 * color for digit
	 * 
	 */
	public void setColor(int c){
		mPaint.setColor(c);
	}
	
	/**
	 * 滚动到某个数字
	 * scroll to a digit
	 * 
	 * @param idx
	 * 要滚动的数字，只能是 0-9
	 * digit scroll to, must be 0 - 9
	 * 
	 * @param dir
	 * 滚动方向，上或下
	 * scroll direction, up or down
	 * 
	 * @param time
	 * 滚动时间，毫秒
	 * scroll time, in million second
	 * 
	 */
	public void scrollTo(int idx, boolean dir, int time) {

		int h = getHeight();
		if (!mScroller.isFinished()) {
			mScroller.abortAnimation();
			mScroller.forceFinished(true);
		}
		if (idx < 0 || idx >= sDefChars.length || h <= 0) {

		} else {

			// calculate the offset ...
			int myOff = mCurIdx * h; // cur off
			int toOff = idx * h;
			int dy = -myOff + toOff;
			if (dy == 0) {
				dy = dir ? h * sDefChars.length : -h * sDefChars.length;
			} else {

				if (dir && dy < 0) {
					dy -= h * sDefChars.length;
				} else if (!dir && dy > 0) {
					dy += h * sDefChars.length;
				}
			}

			mScroller.startScroll(0, myOff, 0, dy, time);
			mCurIdx = idx;
		}

		postInvalidate();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (!changed) {
			return;
		}
		int w = right - left;
		float h = bottom - top;
		h = h * 9 / 10;
		// caclate the font
		for (float i = 10.5f; i < 300.1f; i += 1.5f) {
			mPaint.setTextSize(i);
			Paint.FontMetrics fm = mPaint.getFontMetrics();
			float hei = fm.bottom - fm.top;
			if (hei > h) {
				break;
			}
		}
		mScroller.abortAnimation();
		mScroller.forceFinished(true);
		//		invalidate();
	}

	public void onDraw(Canvas c) {
		super.onDraw(c);
		int w = getWidth();
		int h = getHeight();
		mScroller.computeScrollOffset();

		int cIdx = mCurIdx;
		int off = 0;
		if (mScroller.isFinished()) {
			// just draw the char
		} else {
			int cury = mScroller.getCurrY();
			while (cury < 0) {
				cury += h * sDefChars.length;
			}
			cIdx = (cury / h) % sDefChars.length;
			off = cury % h;
		}
		draw(c, cIdx, off, w, h);
		if (off != 0) {
			draw(c, cIdx == sDefChars.length - 1 ? 0 : cIdx + 1, off - h, w, h);
			postInvalidateDelayed(40);
		}

	}

	private Rect mRect = new Rect();

	private void draw(Canvas c, int idx, int off, int width, int height) {
		if (off != 0) {
			c.save();
			c.translate(0, off);
		}
		mPaint.getTextBounds(sDefChars, idx, 1, mRect);
		c.drawText(sDefChars, idx, 1, ((width - mRect.width()) >> 1), ((height + mRect.height()) >> 1), mPaint);
		if (off != 0) {
			c.restore();
		}
	}

	/**
	 * 获取当前的数字
	 * get current digit displaying
	 * 
	 * @return
	 * 0 - 9
	 */
	public int getCurIdx() {
		return mCurIdx;
	}
}
