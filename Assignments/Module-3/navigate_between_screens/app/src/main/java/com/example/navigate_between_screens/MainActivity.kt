package com.example.navigate_between_screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

  val btn = findViewById<Button>(R.id.btnN)
        btn.setOnClickListener {

            var intent = Intent(this@MainActivity,SecondScreen::class.java)
            startActivity(intent)
        }
    }
}