package com.example.q1_module7

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

     var mediaPlayer:MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var start = findViewById<Button>(R.id.buttonStart)
        var stop = findViewById<Button>(R.id.buttonStop)
        var pause = findViewById<Button>(R.id.buttonPause)



        start.setOnClickListener {

        if (mediaPlayer == null){

            mediaPlayer = MediaPlayer.create(this,R.raw.srivalli)


        }
            mediaPlayer!!.start()

        }

        stop.setOnClickListener {
              stopPlayer()
        }

        pause.setOnClickListener {
            if (mediaPlayer != null){

                mediaPlayer!!.pause()
            }

        }
    }

    private fun stopPlayer() {
        if (mediaPlayer != null){

            mediaPlayer!!.release()
            mediaPlayer = null
            Toast.makeText(this@MainActivity, "Media Player Released", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        stopPlayer()
    }
}