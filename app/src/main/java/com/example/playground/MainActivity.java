package com.example.playground;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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
import com.example.playground.dexload.DexLoadTest;
import com.example.playground.dialogtest.DialogTestActivity;
import com.example.playground.exchangeCard.ExchangeCardActivity;
import com.example.playground.floatappView.FloatViewActivity;
import com.example.playground.guessidiom.GenerateIdiom;
import com.example.playground.permissionrequesttest.PermissionRequestTestActivity;
import com.example.playground.strokenanimation.StrokeTestActivity;
import com.example.playground.tigermachine.TigerMachineActivity;
import com.example.playground.tigermachine.effect1.Effect1Activity;
import com.example.playground.viewinvisiable.InVisiableViewClickActivity;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);

        final ArrayList<String> data = new ArrayList<>();
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
                        DexLoadTest.run();
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
                    final String title = ((TextView) v).getText().toString();
                    listener.onItemClick(title);
                }

            }
        };

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_adapter_item, null);
            return new MyViewHodler(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MyViewHodler h = (MyViewHodler) holder;
            String title = data.get(position);
            h.title.setText(title);
            h.title.setOnClickListener(clickListener);
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
            }
        }
    }
}
