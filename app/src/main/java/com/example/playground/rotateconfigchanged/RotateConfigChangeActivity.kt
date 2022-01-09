package com.example.playground.rotateconfigchanged

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.playground.R

class RotateConfigChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotate_config_change)
        val iv = findViewById<ImageView>(R.id.imageView2)
        iv.setOnClickListener {
            iv.setImageDrawable(resources.getDrawable(R.drawable.p01))
        }
    }
}