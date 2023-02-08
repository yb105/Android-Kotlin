package com.example.q2_module4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var txt = findViewById<TextView>(R.id.txt)

        var num1:Int = intent.getIntExtra("num1",0)
        var num2:Int = intent.getIntExtra("num2",0)

        for (i in num1..num2){

            txt.append("\n" + i)
        }
    }
}