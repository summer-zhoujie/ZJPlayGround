package com.example.playground.exchangeCard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.playground.R;

import java.util.ArrayList;
import java.util.Random;

public class ExchangeCardActivity extends Activity {

    private Button btnplayagian;// 再玩一次
    private ImageView puke1, puke2, puke3;// 三张扑克
    private Drawable bg;// 扑克背面（用于判断是否开牌）
    private int[] pks = {R.drawable.p01, R.drawable.p02, R.drawable.p03};// 存放3张扑克
    private ArrayList<Integer> ps = new ArrayList<Integer>();// 存放洗过的扑克（随机排列）

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_card);
        puke1 = (ImageView) findViewById(R.id.puke1);
        puke2 = (ImageView) findViewById(R.id.puke2);
        puke3 = (ImageView) findViewById(R.id.puke3);
        btnplayagian = (Button) findViewById(R.id.btnplayagian);
        puke1.setOnClickListener(l);
        puke2.setOnClickListener(l);
        puke3.setOnClickListener(l);
        btnplayagian.setOnClickListener(l);
    }

    protected void onResume() {
        randomPuke();
        super.onResume();
    }

    View.OnClickListener l = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.puke1:
                    showPuke(0);
                    // 将玩家没选择的牌面以灰暗效果处理,设置透明度，取值范围为0~255，数值越小越透明。
                    puke2.setAlpha(100);
                    puke3.setAlpha(100);
                    break;
                case R.id.puke2:
                    showPuke(1);
                    puke1.setAlpha(100);
                    puke3.setAlpha(100);
                    break;
                case R.id.puke3:
                    showPuke(2);
                    puke1.setAlpha(100);
                    puke2.setAlpha(100);
                    break;
                case R.id.btnplayagian:
                    randomPuke();
                    break;
            }
        }
    };

    /**
     * 洗牌
     */
    public void randomPuke() {
        // 所有牌翻到背面
        puke1.setImageResource(R.drawable.p04);
        puke2.setImageResource(R.drawable.p04);
        puke3.setImageResource(R.drawable.p04);
        bg = puke1.getDrawable();
        // 实例化清空（收牌）
        ps = new ArrayList<Integer>();
        // 随机填充（发牌）
        do {
            int item = new Random().nextInt(3);// 随机出｛0,1，2｝中其中一个数
            if (!ps.contains(pks[item])) {// 如果桌上没有这张牌才发这张牌
                ps.add(pks[item]);
            }
        } while (ps.size() < 3);// 发完3张牌就不发牌了
    }

    /**
     * 开牌
     */
    public void showPuke(int p) {
        // 如果牌还没开才能开牌
        if (puke1.getDrawable() == bg) {
            puke1.setImageResource((Integer) ps.get(0));
            puke2.setImageResource((Integer) ps.get(1));
            puke3.setImageResource((Integer) ps.get(2));
            Toast.makeText(
                    ExchangeCardActivity.this,
                    R.drawable.p01 == (Integer) ps.get(p) ? "恭喜你猜对了" : "遗憾你猜错了",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
