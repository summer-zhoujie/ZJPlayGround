package com.example.stepcount;

import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;


import androidx.core.app.NotificationCompat;

import com.example.stepcount.beans.StepData;
import com.example.stepcount.db.LocalDBHelper;
import com.example.stepcount.interfaces.StepValuePassListener;
import com.example.stepcount.interfaces.UpdateUiCallBack;
import com.example.stepcount.utils.PreferencesHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class StepService extends Service implements SensorEventListener {
    private String TAG = "StepService";

    /**
     * 默认为30秒之内没有移动，则不计算累计时间
     */
    private static int break_duration = 30;
    /**
     * 默认为30秒进行一次存储
     */
    private static int duration = 30 * 1000;
    /**
     * 当前的日期
     */
    private static int CURRENT_DATE = 0;
    /**
     * 传感器管理对象
     */
    private SensorManager sensorManager;
    /**
     * 广播接受者
     */
    private BroadcastReceiver mBatInfoReceiver;
    /**
     * 保存记步计时器
     */
    private TimeCount time;
    /**
     * 当前所走的步数
     */
    private int CURRENT_STEP;
    /**
     * 当前所走的时间
     */
    private int CURRENT_TIME;
    /**
     * 计步传感器类型  Sensor.TYPE_STEP_COUNTER或者Sensor.TYPE_STEP_DETECTOR
     */
    private static int stepSensorType = -1;
    /**
     * 每次第一次启动记步服务时是否从系统中获取了已有的步数记录
     */
    private boolean hasRecord = false;
    /**
     * 系统中获取到的已有的步数
     */
    private int hasStepCount = 0;
    /**
     * 上一次的步数
     */
    private int previousStepCount = 0;
    /**
     * 通知管理对象
     */
    private NotificationManager mNotificationManager;
    /**
     * 加速度传感器中获取的步数
     */
    private StepCount mStepCount;
    /**
     * IBinder对象，向Activity传递数据的桥梁
     */
    private StepBinder stepBinder = new StepBinder();
    /**
     * 通知构建者
     */
    private NotificationCompat.Builder mBuilder;
    /**
     * 启动到今天为止所有的步数总计
     */
    private int TOTAL_OF_BOOT = 0;
    /**
     * 今天是否重启过
     */
    private int HAS_REBOOT = LocalDBHelper.HAS_NOT_REBOOT;
    /**
     * 手机上次启动至今运行的时间
     */
    private long ELAPSED_REALTIME;

    @Override
    public void onCreate() {
        super.onCreate();
        initTodayData();
        Log.d(TAG, "onCreate() curstep = " + CURRENT_STEP);
        countTime();
        initBroadcastReceiver();
        new Thread(new Runnable() {
            public void run() {
                startStepDetector();
            }
        }).start();
        startTimeCount();
    }

    /**
     * 获取当天日期
     *
     * @return
     */
    private int getTodayDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.valueOf(sdf.format(date));
    }

    /**
     * 初始化当天的步数
     */
    private void initTodayData() {
        CURRENT_DATE = getTodayDate();
        ELAPSED_REALTIME = PreferencesHelper.getElapsedRealtime(getApplicationContext());
        List<StepData> list = LocalDBHelper.getInstance(this).getStepByDate(CURRENT_DATE, CURRENT_DATE);
        if (list == null || list.size() == 0) {
            CURRENT_STEP = 0;
            CURRENT_TIME = 0;
            TOTAL_OF_BOOT = 0;
            HAS_REBOOT = LocalDBHelper.HAS_NOT_REBOOT;
        } else if (list.size() == 1) {
            // 有条目, 说明今天服务启动过 TOTAL_OF_BOOT 值是有效的
            CURRENT_STEP = list.get(0).getStep();
            CURRENT_TIME = list.get(0).getTime();
            TOTAL_OF_BOOT = list.get(0).getTotalOfBoot();
            HAS_REBOOT = list.get(0).getHasReboot();
            Log.d(TAG, "initTodayData: TOTAL_OF_BOOT = " + TOTAL_OF_BOOT);
            if (TOTAL_OF_BOOT >= 0) {
                hasRecord = true;
                hasStepCount = TOTAL_OF_BOOT;
            }
        } else {
            Log.v(TAG, "initTodayData error");
        }

        recorrectDataIfReboot();

        if (mStepCount != null) {
            mStepCount.setSteps(CURRENT_STEP);
        }

        updateNotification();
    }

    /**
     * 重新计算数据如果重启过
     */
    private void recorrectDataIfReboot() {
        long l = SystemClock.elapsedRealtime();
        Log.d(TAG, "recorrectDataIfReboot: HAS_REBOOT = " + HAS_REBOOT + ",TOTAL_OF_BOOT=" + TOTAL_OF_BOOT + ",ELAPSED_REALTIME=" + ELAPSED_REALTIME + "->" + l);
        if (HAS_REBOOT == LocalDBHelper.HAS_REBOOT || ELAPSED_REALTIME > l) {
            // 总步数清理, 当天步数不变
            TOTAL_OF_BOOT = 0;
            hasStepCount = TOTAL_OF_BOOT;
            HAS_REBOOT = LocalDBHelper.HAS_NOT_REBOOT;
            save();
        }
    }

    /**
     * 注册广播
     */
    private void initBroadcastReceiver() {
        final IntentFilter filter = new IntentFilter();
        // 屏幕灭屏广播
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        //关机广播
        filter.addAction(Intent.ACTION_SHUTDOWN);
        // 屏幕亮屏广播
        filter.addAction(Intent.ACTION_SCREEN_ON);
        // 屏幕解锁广播
//        filter.addAction(Intent.ACTION_USER_PRESENT);
        // 当长按电源键弹出“关机”对话或者锁屏时系统会发出这个广播
        // example：有时候会用到系统对话框，权限可能很高，会覆盖在锁屏界面或者“关机”对话框之上，
        // 所以监听这个广播，当收到时就隐藏自己的对话，如点击pad右下角部分弹出的对话框
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        //监听日期变化
        filter.addAction(Intent.ACTION_DATE_CHANGED);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        filter.addAction(Intent.ACTION_TIME_TICK);

        mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                String action = intent.getAction();
                if (Intent.ACTION_SCREEN_ON.equals(action)) {
                    Log.d(TAG, "screen on");
                } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                    Log.d(TAG, "screen off");
                    //改为60秒一存储
                    duration = 60000;
                } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                    Log.d(TAG, "screen unlock");
                    //改为30秒一存储
                    duration = 30000;
                } else if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(intent.getAction())) {
                    Log.i(TAG, " receive Intent.ACTION_CLOSE_SYSTEM_DIALOGS");
                    //保存一次
                    save();
                } else if (Intent.ACTION_SHUTDOWN.equals(intent.getAction())) {
                    Log.i(TAG, " receive ACTION_SHUTDOWN");
                    // 重启判断二

                    // 只做记录不作处理, 下次启动App时处理(防止清完零又被Sensor最新数据覆盖)
                    HAS_REBOOT = LocalDBHelper.HAS_REBOOT;
                    save();
                } else if (Intent.ACTION_DATE_CHANGED.equals(action)) {//日期变化步数重置为0
//                    Logger.d("重置步数" + StepDcretor.CURRENT_STEP);
                    save();
                    isNewDay();
                } else if (Intent.ACTION_TIME_CHANGED.equals(action)) {
                    //时间变化步数重置为0
                    save();
                    isNewDay();
                } else if (Intent.ACTION_TIME_TICK.equals(action)) {//日期变化步数重置为0
//                    Logger.d("重置步数" + StepDcretor.CURRENT_STEP);
                    save();
                    isNewDay();
                }
            }
        };
        registerReceiver(mBatInfoReceiver, filter);
    }


    /**
     * 监听晚上0点变化初始化数据
     */
    private void isNewDay() {
        String time = "00:00";
        if (time.equals(new SimpleDateFormat("HH:mm").format(new Date())) || CURRENT_DATE != getTodayDate()) {
            initTodayData();
        }
    }


    /**
     * 开始保存记步数据
     */
    private void startTimeCount() {
        if (time == null) {
            time = new TimeCount(duration, 1000);
        }
        time.start();
    }

    /**
     * 更新步数通知
     */
    private void updateNotification() {
        if (mCallback != null) {
            mCallback.updateUi(CURRENT_STEP, CURRENT_TIME);
        }
    }

    /**
     * UI监听器对象
     */
    private UpdateUiCallBack mCallback;

    /**
     * 注册UI更新监听
     *
     * @param paramICallback
     */
    public void registerCallback(UpdateUiCallBack paramICallback) {
        this.mCallback = paramICallback;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return stepBinder;
    }

    /**
     * 向Activity传递数据的纽带
     */
    public class StepBinder extends Binder {

        /**
         * 获取当前service对象
         *
         * @return StepService
         */
        public StepService getService() {
            return StepService.this;
        }
    }

    /**
     * 获取当前步数
     *
     * @return
     */
    public int getStepCount() {
        return CURRENT_STEP;
    }

    /**
     * 获取当前累计时间
     *
     * @return
     */
    public int getTimeCount() {
        return CURRENT_TIME;
    }


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    /**
     * 获取传感器实例
     */
    private void startStepDetector() {
        if (sensorManager != null) {
            sensorManager = null;
        }
        // 获取传感器管理器的实例
        sensorManager = (SensorManager) this
                .getSystemService(SENSOR_SERVICE);
        //android4.4以后可以使用计步传感器
        int VERSION_CODES = Build.VERSION.SDK_INT;
        if (VERSION_CODES >= 19) {
            addCountStepListener();
        } else {
            addBasePedometerListener();
        }
    }

    /**
     * 添加传感器监听
     * 1. TYPE_STEP_COUNTER API的解释说返回从开机被激活后统计的步数，当重启手机后该数据归零，
     * 该传感器是一个硬件传感器所以它是低功耗的。
     * 为了能持续的计步，请不要反注册事件，就算手机处于休眠状态它依然会计步。
     * 当激活的时候依然会上报步数。该sensor适合在长时间的计步需求。
     * <p>
     * 2.TYPE_STEP_DETECTOR翻译过来就是走路检测，
     * API文档也确实是这样说的，该sensor只用来监监测走步，每次返回数字1.0。
     * 如果需要长事件的计步请使用TYPE_STEP_COUNTER。
     */
    private void addCountStepListener() {
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (countSensor != null) {
            stepSensorType = Sensor.TYPE_STEP_COUNTER;
            boolean b = sensorManager.registerListener(StepService.this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
            Log.v(TAG, "Sensor.TYPE_STEP_COUNTER = " + b);
        } else if (detectorSensor != null) {
            stepSensorType = Sensor.TYPE_STEP_DETECTOR;
            Log.v(TAG, "Sensor.TYPE_STEP_DETECTOR");
            sensorManager.registerListener(StepService.this, detectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.v(TAG, "Count sensor not available!");
            addBasePedometerListener();
        }
    }

    /**
     * 传感器监听回调
     * 记步的关键代码
     * 1. TYPE_STEP_COUNTER API的解释说返回从开机被激活后统计的步数，当重启手机后该数据归零，
     * 该传感器是一个硬件传感器所以它是低功耗的。
     * 为了能持续的计步，请不要反注册事件，就算手机处于休眠状态它依然会计步。
     * 当激活的时候依然会上报步数。该sensor适合在长时间的计步需求。
     * <p>
     * 2.TYPE_STEP_DETECTOR翻译过来就是走路检测，
     * API文档也确实是这样说的，该sensor只用来监监测走步，每次返回数字1.0。
     * 如果需要长事件的计步请使用TYPE_STEP_COUNTER。
     *
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (stepSensorType == Sensor.TYPE_STEP_COUNTER) {
            //获取当前传感器返回的临时步数
            int tempStep = (int) event.values[0];
            Log.d(TAG, "onSensorChanged: tempStep = " + tempStep);
            //首次如果没有获取手机系统中已有的步数则获取一次系统中APP还未开始记步的步数
            if (!hasRecord) {
                hasRecord = true;
                hasStepCount = tempStep;
            } else {
                //获取APP打开到现在的总步数=本次系统回调的总步数-APP打开之前已有的步数
                int thisStepCount = tempStep - hasStepCount;
                // 重启判断一
                if (thisStepCount < 0) {
                    thisStepCount = tempStep;
                    hasStepCount = 0;
                }
                //本次有效步数=（APP打开后所记录的总步数-上一次APP打开后所记录的总步数）
                int thisStep = thisStepCount - previousStepCount;
                //总步数=现有的步数+本次有效步数
                CURRENT_STEP += (thisStep);
                //记录最后一次APP打开到现在的总步数
                previousStepCount = thisStepCount;
                isMoving = true;
            }
            TOTAL_OF_BOOT = tempStep;
        } else if (stepSensorType == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0) {
                CURRENT_STEP++;
            }
        }
        updateNotification();
    }

    /**
     * 通过加速度传感器来记步
     */
    private void addBasePedometerListener() {
        mStepCount = new StepCount();
        mStepCount.setSteps(CURRENT_STEP);
        // 获得传感器的类型，这里获得的类型是加速度传感器
        // 此方法用来注册，只有注册过才会生效，参数：SensorEventListener的实例，Sensor的实例，更新速率
        Sensor sensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        boolean isAvailable = sensorManager.registerListener(mStepCount.getStepDetector(), sensor,
                SensorManager.SENSOR_DELAY_UI);
        mStepCount.initListener(new StepValuePassListener() {
            @Override
            public void stepChanged(int steps) {
                CURRENT_STEP = steps;
                updateNotification();
            }
        });
        if (isAvailable) {
            Log.v(TAG, "accelerometer sensor is usable");
        } else {
            Log.v(TAG, "accelerometer sensor is unusable");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    int mSecond = 0;
    boolean isMoving = false;
    boolean isStartCountTime = false;

    private void countTime() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isMoving && !isStartCountTime) {
                    isStartCountTime = true;
                    isMoving = false;
                    mSecond = 0;
                }
                if (isStartCountTime) {
                    mSecond++;
                    if (isMoving) {
                        CURRENT_TIME += mSecond;
                        mSecond = 0;
                        isMoving = false;
                    }
                    if (mSecond >= break_duration) {
                        isStartCountTime = false;
                        mSecond = 0;
                    }
                }
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    /**
     * 保存记步数据
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            // 如果计时器正常结束，则开始计步
            time.cancel();
            save();
            startTimeCount();
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

    }

    /**
     * 保存记步数据
     */
    private void save() {
        Log.d(TAG, "save: TOTAL_OF_BOOT = " + TOTAL_OF_BOOT);
        List<StepData> list = LocalDBHelper.getInstance(this).getStepByDate(CURRENT_DATE, CURRENT_DATE);
        if (list == null || list.size() == 0) {
            LocalDBHelper.getInstance(this).insertStep(CURRENT_DATE, CURRENT_STEP, CURRENT_TIME, TOTAL_OF_BOOT, HAS_REBOOT);
        } else if (list.size() == 1) {
            LocalDBHelper.getInstance(this).updateStep(CURRENT_DATE, CURRENT_STEP, CURRENT_TIME, TOTAL_OF_BOOT, HAS_REBOOT);
        } else {

        }

        // 重启判断三(记录上次手机启动运行的时间)
        ELAPSED_REALTIME = SystemClock.elapsedRealtime();
        PreferencesHelper.setElapsedRealtime(getApplicationContext(), ELAPSED_REALTIME);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消前台进程
        Log.d("ZXH", "###### onDestroy");
        stopForeground(true);
        unregisterReceiver(mBatInfoReceiver);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
