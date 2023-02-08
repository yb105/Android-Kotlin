package com.example.q2_module7

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var btnSelectAudio:Button
    lateinit var btnplay:Button
    lateinit var btnpause:Button
    lateinit var btnstop:Button

    private val PICK_AUDIO_FILE = 1
    var audioUri: Uri? = null

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer()

        btnSelectAudio = findViewById(R.id.btnSelectAudio)
        btnplay = findViewById(R.id.btnPlay)
        btnpause = findViewById(R.id.btnpause)
        btnstop = findViewById(R.id.btnstop)

        btnSelectAudio.setOnClickListener {

            mediaPlayer.stop()
            val audio = Intent()
            audio.type = "audio/*"
            audio.action = Intent.ACTION_OPEN_DOCUMENT
            startActivityForResult(
                Intent.createChooser(audio, "Select Audio From Media File"),PICK_AUDIO_FILE)


        }


        btnplay.setOnClickListener {
            if (mediaPlayer == null){

                Toast.makeText(this, "Select Audio", Toast.LENGTH_SHORT).show()




            }else if (audioUri != null){
                mediaPlayer!!.start()
            }
            else if (audioUri == null){
                Toast.makeText(this, "Select Audio", Toast.LENGTH_SHORT).show()
            }

        }

        btnpause.setOnClickListener {
            if (mediaPlayer != null){

                mediaPlayer!!.pause()
            }

        }

        btnstop.setOnClickListener {
            mediaPlayer.stop()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_AUDIO_FILE && resultCode == RESULT_OK) {
            // Audio is Picked in format of URI
            audioUri = data!!.data
            mediaPlayer  = MediaPlayer.create(this@MainActivity,audioUri)

            Toast.makeText(this@MainActivity, "Audio Selected", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        mediaPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}