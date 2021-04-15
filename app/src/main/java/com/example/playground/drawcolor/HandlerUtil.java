package com.example.playground.drawcolor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class HandlerUtil {

    private static final Handler MAIN_LOOPER = new Handler(Looper.getMainLooper());
    private static Handler BACKGROUND_LOOPER = null;

    public synchronized static void postToMain(final Runnable run) {
        MAIN_LOOPER.post(run);
    }

    public synchronized static void postToMain(final Runnable run, final long delayMillis) {
        MAIN_LOOPER.postDelayed(run, delayMillis);
    }

    public synchronized static void removeTaskFromMan(final Runnable run) {
        MAIN_LOOPER.removeCallbacks(run);
    }

    public synchronized static void postToBackground(final Runnable run) {
        postToBackground(run, 0);
    }

    public synchronized static void postToBackground(final Runnable run, final long delayMillis) {
        if (BACKGROUND_LOOPER == null) {
            HandlerThread thread = new HandlerThread("background");
            thread.start();
            BACKGROUND_LOOPER = new Handler(thread.getLooper());
        }

        BACKGROUND_LOOPER.postDelayed(run, delayMillis);
    }

    public synchronized static void removeTaskFromBackground(final Runnable run) {
        if (BACKGROUND_LOOPER != null) {
            BACKGROUND_LOOPER.removeCallbacks(run);
        }
    }

}
