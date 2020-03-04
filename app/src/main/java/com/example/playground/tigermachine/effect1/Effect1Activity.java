package com.example.playground.tigermachine.effect1;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.playground.R;

public class Effect1Activity extends Activity {

	@Override
	protected void onCreate(Bundle saved) {
		super.onCreate(saved);
		setContentView(R.layout.tiger_machine);

		mTigerView1 = (TigerMachineView) findViewById(R.id.t1);
		mTigerView2 = (TigerMachineView) findViewById(R.id.t2);
		mTigerView3 = (TigerMachineView) findViewById(R.id.t3);

		mHandler = new Handler();
		mHandler.post(mTask);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacks(mTask);
	}

	private Handler mHandler;
	private TigerMachineView mTigerView1, mTigerView2, mTigerView3;

	private Runnable mTask = new Runnable() {

		private void doIt(TigerMachineView v, int time) {
			Random r = new Random();

			int cur = v.getCurIdx();
			int idx;

			do {
				idx = r.nextInt() % 10;
				if (idx < 0) {
					idx = -idx;
				}
				if (idx != cur) {
					break;
				}
			} while (true);
			boolean dir = r.nextInt() % 2 == 0;
			v.scrollTo(idx, dir, time);

		}

		@Override
		public void run() {
			mHandler.removeCallbacks(mTask);
			int time = 500;
			doIt(mTigerView1, time);
			doIt(mTigerView2, time);
			doIt(mTigerView3, time);
			mHandler.postDelayed(mTask, time * 2);
		}
	};
}
