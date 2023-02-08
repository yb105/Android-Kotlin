package com.example.q9_module6

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        var splashScreenImage = findViewById<ImageView>(R.id.SplashScreenImage)
        var slideAnimation = AnimationUtils.loadAnimation(this,R.anim.side_slide)
        splashScreenImage.startAnimation(slideAnimation)

        Handler().postDelayed({
            val intent = Intent(this, Empty::class.java)
            startActivity(intent)
            finish()
        }, 3000)


    }
}