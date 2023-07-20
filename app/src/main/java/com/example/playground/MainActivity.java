package com.example.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playground.activityfindviewbyid.FindViewByIdActivity;
import com.example.playground.bitmap.BitmapTestActivity;
import com.example.playground.customview.CustomViewActivity;
import com.example.playground.dexload.DexLoadTest;
import com.example.playground.dexload.DexLoadTestActivity;
import com.example.playground.dexload.DexReplaceUtils;
import com.example.playground.dialogtest.DialogTestActivity;
import com.example.playground.dragview.DragViewActivity;
import com.example.playground.drawcolor.DrawClolorActivity;
import com.example.playground.exchangeCard.ExchangeCardActivity;
import com.example.playground.floatappView.FloatViewActivity;
import com.example.playground.guessidiom.GenerateIdiom;
import com.example.playground.largeImage.LargeImageViewActivity;
import com.example.playground.onmeasure.OnMeasureTestActivity;
import com.example.playground.parserString.ParserStringActivity;
import com.example.playground.permissionrequesttest.PermissionRequestTestActivity;
import com.example.playground.rotateconfigchanged.RotateConfigChangeActivity;
import com.example.playground.step.StepCountActivity;
import com.example.playground.strokenanimation.StrokeTestActivity;
import com.example.playground.tigermachine.TigerMachineActivity;
import com.example.playground.viewinvisiable.InVisiableViewClickActivity;
import com.example.playground.webviewtest.WebViewTestActivity;
import com.zj.tools.mylibrary.ZJHiddenApiUtils;
import com.zj.tools.mylibrary.ZJLog;

import java.io.File;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    public static final String ITEM_1 = "1. 创建爱计步成语所需数据库";
    public static final String ITEM_2 = "2. 猜扑克牌";
    public static final String ITEM_3 = "3. Rxjava测试";
    public static final String ITEM_4 = "4. 测试矩形框动画";
    public static final String ITEM_5 = "5. 老虎机";
    public static final String ITEM_6 = "6. Dex加载测试";
    public static final String ITEM_7 = "7. 弹窗关闭测试";
    public static final String ITEM_8 = "8. 权限请求测试";
    public static final String ITEM_9 = "9. FindViewById测试";
    public static final String ITEM_10 = "10. 测试视图不可见时的点击";
    public static final String ITEM_11 = "11. 系统悬浮视图";
    public static final String ITEM_12 = "12. 自定义View";
    public static final String ITEM_13 = "13. LocationOnScreen方法测试";
    public static final String ITEM_14 = "14. 着色测试";
    public static final String ITEM_15 = "15. WebView&&mac地址";
    public static final String ITEM_16 = "16. Bitmap 测试";
    public static final String ITEM_17 = "17. Bitmap大图无损加载";
    public static final String ITEM_18 = "18. 计步";
    public static final String ITEM_19 = "19. onMeasure打点测试";
    public static final String ITEM_20 = "20. 事件分发测试代码";
    public static final String ITEM_21 = "21. 旋转屏幕UI默认恢复情况";
    public static final String ITEM_22 = "22. TextView缩放";
    public static final String ITEM_23 = "23. 反编译字符串解析";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);

        final ArrayList<String> data = new ArrayList<>();
        data.add(ITEM_23);
        data.add(ITEM_22);
        data.add(ITEM_21);
        data.add(ITEM_20);
        data.add(ITEM_19);
        data.add(ITEM_18);
        data.add(ITEM_17);
        data.add(ITEM_16);
        data.add(ITEM_15);
        data.add(ITEM_14);
        data.add(ITEM_13);
        data.add(ITEM_12);
        data.add(ITEM_11);
        data.add(ITEM_10);
        data.add(ITEM_9);
        data.add(ITEM_8);
        data.add(ITEM_7);
        data.add(ITEM_6);
        data.add(ITEM_5);
        data.add(ITEM_4);
        data.add(ITEM_3);
        data.add(ITEM_2);
        data.add(ITEM_1);
        final MainAdapter adapter = new MainAdapter(data);
        adapter.setListener(new OnAdapterListener() {
            @Override
            public void onItemClick(String title) {
                switch (title) {
                    case ITEM_23:
                        startActivity(new Intent(MainActivity.this, ParserStringActivity.class));
                        break;
                    case ITEM_22:
                        startActivity(new Intent(MainActivity.this, TextViewAutosizeActivity.class));
                        break;
                    case ITEM_21:
                        startActivity(new Intent(MainActivity.this, RotateConfigChangeActivity.class));
                        break;
                    case ITEM_20:
                        startActivity(new Intent(MainActivity.this, DragViewActivity.class));
                        break;
                    case ITEM_19:
                        OnMeasureTestActivity.launch(MainActivity.this);
                        break;
                    case ITEM_18:
                        StepCountActivity.launch(MainActivity.this);
                        break;
                    case ITEM_17:
                        LargeImageViewActivity.launch(MainActivity.this);
                        break;
                    case ITEM_16:
                        BitmapTestActivity.launch(MainActivity.this);
                        break;
                    case ITEM_15:
                        WebViewTestActivity.launch(MainActivity.this);
                        break;
                    case ITEM_14:
                        DrawClolorActivity.launch(MainActivity.this);
                        break;
                    case ITEM_13:
//                        LocationOnScreenActivity.launch(MainActivity.this);
                        break;
                    case ITEM_12:
                        CustomViewActivity.launch(MainActivity.this);
                        break;
                    case ITEM_11:
                        startActivity(new Intent(MainActivity.this, FloatViewActivity.class));
                        break;
                    case ITEM_10:
                        InVisiableViewClickActivity.launch(MainActivity.this);
                        break;
                    case ITEM_9:
                        startActivity(new Intent(MainActivity.this, FindViewByIdActivity.class));
                        break;
                    case ITEM_8:
                        startActivity(new Intent(MainActivity.this, PermissionRequestTestActivity.class));
                        break;
                    case ITEM_7:
                        startActivity(new Intent(MainActivity.this, DialogTestActivity.class));
                        break;
                    case ITEM_6:
                        startActivity(new Intent(MainActivity.this, DexLoadTestActivity.class));
                        break;
                    case ITEM_5:
                        startActivity(new Intent(MainActivity.this, TigerMachineActivity.class));
                        break;
                    case ITEM_4:
                        startActivity(new Intent(MainActivity.this, StrokeTestActivity.class));
                        break;
                    case ITEM_3:
                        testRxJava();
                        break;
                    case ITEM_2:
                        startActivity(new Intent(MainActivity.this, ExchangeCardActivity.class));
                        break;
                    case ITEM_1:
                        GenerateIdiom.generate(MainActivity.this);
                        break;
                    default:
                        break;
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);


        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTimeInMillis();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        calendar.setTimeInMillis(currentTime);
        ZJLog.d("getTodayBeginTime: " + calendar.getTimeInMillis());
        ZJLog.d("currentTimeMillis: " + System.currentTimeMillis());
    }

    /**
     * Rxjava测试
     */
    public void testRxJava() {
        int maxnumber = 10000;
        String[] strings = new String[maxnumber];
        for (int i = 0; i < maxnumber; i++) {
            strings[i] = "" + i;
        }
        FlowableFromArray flowableFromArray = new FlowableFromArray(strings);
        final Disposable subscribe = flowableFromArray
                .subscribeOn(Schedulers.computation())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        Log.d("=summerzhou=", "(MainActivity.accept): o=" + o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("=summerzhou=", "(MainActivity.Error): msg=" + throwable.getLocalizedMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("=summerzhou=", "(MainActivity.complete): ");
                    }
                });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("=summerzhou=", "(MainActivity.dispose): ");
                subscribe.dispose();
            }
        }, 300);

    }

    public interface OnAdapterListener {
        void onItemClick(String title);
    }

    private static class MainAdapter extends RecyclerView.Adapter {

        private final List<String> data;
        private OnAdapterListener listener;

        MainAdapter(List<String> data) {
            this.data = data;
        }

        public void setListener(OnAdapterListener listener) {
            this.listener = listener;
        }

        private View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    final String title = (String) v.getTag();
                    listener.onItemClick(title);
                }

            }
        };

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_adapter_item, parent, false);
            return new MyViewHodler(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MyViewHodler h = (MyViewHodler) holder;
            String title = data.get(position);
            h.title.setText(title);
            h.itemView.setTag(title);
            h.itemView.setOnClickListener(clickListener);
        }

        @Override
        public int getItemCount() {
            return this.data == null ? 0 : data.size();
        }

        private class MyViewHodler extends RecyclerView.ViewHolder {

            TextView title;

            public MyViewHodler(View inflate) {
                super(inflate);
                title = inflate.findViewById(R.id.tv_value);
                itemView.setBackground(itemView.getResources().getDrawable(R.drawable.list_item_bg));
            }
        }
    }
}
