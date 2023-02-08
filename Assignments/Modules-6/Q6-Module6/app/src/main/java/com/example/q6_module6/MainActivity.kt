package com.example.q6_module6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    lateinit var imageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      var r = 0f

        var btnRotate = findViewById<Button>(R.id.btnRotate)
        var btnBlink = findViewById<Button>(R.id.btnBlink)
       imageView = findViewById(R.id.imageView)


        btnRotate.setOnClickListener {
            r = r + 90f
           imageView.rotation = r
        }


        btnBlink.setOnClickListener {

           var animation:Animation = AnimationUtils.loadAnimation(this,R.anim.blink)
            imageView.startAnimation(animation)


        }
    }



}