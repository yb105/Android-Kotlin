package com.example.q4_module7

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.MediaController
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        videoView = findViewById(R.id.videoView)

        var mediacontroller = MediaController(this)
        mediacontroller.setAnchorView(videoView)

        var uri = Uri.parse("android.resource://$packageName/${R.raw.queen}")
        videoView.setMediaController(mediacontroller)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
    }
}