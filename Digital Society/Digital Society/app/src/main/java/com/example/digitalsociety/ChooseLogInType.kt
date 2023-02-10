package com.example.digitalsociety

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast

class ChooseLogInType : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_log_in_type)

        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        var imChairman = findViewById<ImageView>(R.id.imChairman)
        var imEmployee = findViewById<ImageView>(R.id.imEmployee)
        var slideAnimation = AnimationUtils.loadAnimation(this,R.anim.slideanimation)
        var revserslideAnimation =  AnimationUtils.loadAnimation(this,R.anim.revserslide)
        imChairman.startAnimation(slideAnimation)
        imEmployee.startAnimation(revserslideAnimation)

        imChairman.setOnClickListener {
            startActivity(Intent(this,LoginRegisterEmployee::class.java))
        }

        imEmployee.setOnClickListener {
            startActivity(Intent(this,LoginRegisterEmployee::class.java))
        }
    }


}