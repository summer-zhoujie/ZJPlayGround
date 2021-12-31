package com.example.playground.dexload

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.hotfixdex.ClassBeenHotFixed
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
        val path =
            Environment.getExternalStorageDirectory().absolutePath
//        DexReplaceUtils.doReplace(
//            this,
//            Environment.getExternalStorageDirectory().absolutePath + File.separator + "replace.aar"
//        )
        FixDexUtils.loadFixedDex(this, File(path))

    }

    private fun clickPrint(v: View) {
        ClassBeenHotFixed().print(this)
    }

}