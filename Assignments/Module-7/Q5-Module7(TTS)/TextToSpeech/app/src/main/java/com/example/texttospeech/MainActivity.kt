package com.example.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var ed:EditText
    lateinit var btn:Button

    var tts:TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ed = findViewById(R.id.edText)
        btn = findViewById(R.id.btnTTS)

        tts = TextToSpeech(this,this)

        btn.setOnClickListener {
            speakOut()
        }


    }

    override fun onInit(status: Int) {
        var results = tts!!.setLanguage(Locale.US)
        if (results == TextToSpeech.LANG_MISSING_DATA || results == TextToSpeech.LANG_NOT_SUPPORTED){

            Toast.makeText(this@MainActivity, "Language Not Supported", Toast.LENGTH_SHORT).show()
        }
    }

    fun speakOut(){
        var text = ed.text.toString()
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        if (tts!=null){

            tts!!.stop()
            tts!!.shutdown()
        }

        super.onDestroy()
    }

}

