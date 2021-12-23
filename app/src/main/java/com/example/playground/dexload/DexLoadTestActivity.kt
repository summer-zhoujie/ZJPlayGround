package com.example.playground.dexload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.playground.R
import com.zj.tools.mylibrary.ZJLog
import com.zj.tools.mylibrary.ZJToast

/**
 * 热修复
 */
class DexLoadTestActivity : AppCompatActivity() {

    private lateinit var loadBtn: Button
    private lateinit var print: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dex_load_test)
        loadBtn = findViewById(R.id.loadDex)
        print = findViewById(R.id.print)
        loadBtn.setOnClickListener(::clickLoadBtn)
        print.setOnClickListener(::clickPrint)
    }

    private fun clickLoadBtn(v: View){
        ZJLog.d("开始加载修复包")

    }

    private fun clickPrint(v:View) {
        ClassBeenHotFixed().print(this)
    }

}