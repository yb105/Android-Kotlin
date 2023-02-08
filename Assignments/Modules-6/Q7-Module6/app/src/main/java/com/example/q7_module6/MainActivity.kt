package com.example.q7_module6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        var btnMove = findViewById<Button>(R.id.btnMove)
        var btnZoomIn = findViewById<Button>(R.id.btnZoomin)
        var btnZoomOut = findViewById<Button>(R.id.btnZoomout)

       btnMove.setOnClickListener {
           imageView.animate().x(80f).y(212f).setDuration(300)
       }

        btnZoomIn.setOnClickListener {

            var zoomIn = AnimationUtils.loadAnimation(this,R.anim.zoom_in)
            imageView.startAnimation(zoomIn)

        }

        btnZoomOut.setOnClickListener {

            var zoomIn = AnimationUtils.loadAnimation(this,R.anim.zoom_out)
            imageView.startAnimation(zoomIn)

        }


    }
}