package com.example.q3_module7

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    var uriLink = "https://ghantalele.com/uploads/files/data-77/38145/Tu%20You%20-%20Armaan%20Malik_192(Ghantalele.com).mp3"

    var mediaPlayer:MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var start = findViewById<Button>(R.id.buttonStart)
        var stop = findViewById<Button>(R.id.buttonStop)
        var pause = findViewById<Button>(R.id.buttonPause)



        start.setOnClickListener {

            if (mediaPlayer == null){

                mediaPlayer = MediaPlayer.create(this,uriLink.toUri())
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
    } }
