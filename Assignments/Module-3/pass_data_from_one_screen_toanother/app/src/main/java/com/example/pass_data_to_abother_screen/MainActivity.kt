package com.example.pass_data_to_abother_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ed = findViewById<EditText>(R.id.eddata)
        var btn = findViewById<Button>(R.id.btnsend)

        btn.setOnClickListener {

            var i = Intent(this@MainActivity,ReceiverScreen::class.java)
            i.putExtra("data",ed.text.toString())
            startActivity(i)
        }
    }
}