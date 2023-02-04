package com.example.q8_module4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnh = findViewById<Button>(R.id.btnhide)
        var btns = findViewById<Button>(R.id.btnShow)
        var txt = findViewById<TextView>(R.id.txt)

        btnh.setOnClickListener {
            txt.visibility = View.GONE
        }
        btns.setOnClickListener {
            txt.visibility = View.VISIBLE
        }
    }
}