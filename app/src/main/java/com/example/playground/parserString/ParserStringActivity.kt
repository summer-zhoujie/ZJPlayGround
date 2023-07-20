package com.example.playground.parserString

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import com.xm.ark.encode.EncodeUtils


class ParserStringActivity : AppCompatActivity() {
    private lateinit var editTextText:EditText
    private lateinit var tv2:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parser_string)
        editTextText = findViewById(R.id.editTextText)
        tv2 = findViewById(R.id.textView2)
    }

    fun clickParser(view: View) {
        val toString = editTextText.text.toString()
        val result = a(toString)
        tv2.text = result
    }


    fun a(str: String?): String? {
        val b: String? = b(str, "-1876124981")
        return b
    }

    fun b(str: String?, str2: String?): String? {
        return try {
            val decrypt: String? = decrypt(str)
            if (System.currentTimeMillis() < Build.VERSION.SDK_INT.toLong()) {
                println("i am a java")
            }
            decrypt
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun decrypt(str: String?): String? {
        if (TextUtils.isEmpty(str)) {
            for (i in 0..9) {
            }
            return ""
        }
        val aDe1: String = EncodeUtils.aDe1(str)
        return aDe1
    }
}