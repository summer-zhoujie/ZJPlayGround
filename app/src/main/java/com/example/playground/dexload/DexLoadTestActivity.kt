package com.example.playground.dexload

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.playground.R
import com.zj.tools.mylibrary.ZJLog
import java.io.File

/**
 * 热修复
 */
class DexLoadTestActivity : AppCompatActivity() {

    private lateinit var loadBtn: Button
    private lateinit var print: Button
    private val PERMISSION_REQUEST_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dex_load_test)
        loadBtn = findViewById(R.id.loadDex)
        print = findViewById(R.id.print)
        loadBtn.setOnClickListener(::clickLoadBtn)
        print.setOnClickListener(::clickPrint)

        // 必要权限检查
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val checkSelfPermission =
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (checkSelfPermission == PERMISSION_GRANTED) {
                loadBtn.isVisible = true
            } else {
                loadBtn.isVisible = false
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.isNotEmpty()) {
            var isGranted = true
            for (grantResult in grantResults) {
                if (grantResult != PERMISSION_GRANTED) {
                    isGranted = false
                    break
                }
            }
            loadBtn.isVisible = isGranted
        }
    }

    private fun clickLoadBtn(v: View) {
        ZJLog.d("开始加载修复包")
        DexReplaceUtils.doReplace(
            this,
            externalCacheDir?.absolutePath + File.separator + "hot_fix.dex"
        )
    }

    private fun clickPrint(v: View) {
        // 直接import会导致加载修复包无效, 此时findclass会直接从内存读取, 不会读取我们替换的dex
        val aClass = Class.forName("com.example.hotfixdex.ClassBeenHotFixed")
        val constructor = aClass.getConstructor()
        val o = constructor.newInstance()
        val print = aClass.getMethod("print", Context::class.java)
        print.invoke(o, this)
    }

}