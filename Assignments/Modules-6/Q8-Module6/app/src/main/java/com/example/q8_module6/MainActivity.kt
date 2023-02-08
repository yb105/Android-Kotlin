package com.example.q8_module6

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    lateinit var animationDrawable:AnimationDrawable
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnStart = findViewById<Button>(R.id.btnStart)
        var btnStop = findViewById<Button>(R.id.btnStop)
        imageView = findViewById<ImageView>(R.id.imageView)

        animationDrawable = imageView.drawable as AnimationDrawable

        btnStart.setOnClickListener {
            animationDrawable.start()
        }

        btnStop.setOnClickListener {
            animationDrawable.stop()
        }

    }
}