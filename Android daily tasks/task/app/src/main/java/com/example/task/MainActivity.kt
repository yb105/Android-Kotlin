package com.example.task

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btn = findViewById<Button>(R.id.btnA)

        btn.setOnClickListener {
            var i = Intent(this@MainActivity,listview::class.java)
            finish()
            startActivity(i)
        }
    }
}