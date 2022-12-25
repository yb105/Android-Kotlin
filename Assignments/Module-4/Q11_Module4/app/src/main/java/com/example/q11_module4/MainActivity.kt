package com.example.q11_module4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var fontsize = 14f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt = findViewById<TextView>(R.id.textView)
        var btnp = findViewById<Button>(R.id.p)
        var btnm = findViewById<Button>(R.id.m)


        btnp.setOnClickListener {
            fontsize += 4
            txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
        }

        btnm.setOnClickListener {
            fontsize -= 4
            txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
        }
    }
}