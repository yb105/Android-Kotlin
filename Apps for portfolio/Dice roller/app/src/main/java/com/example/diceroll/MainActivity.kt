package com.example.diceroll

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRoll = findViewById<Button>(R.id.btnRoll)
        var imageView  = findViewById<ImageView>(R.id.imageView)

        var array = arrayOf<Int>(R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six)

        btnRoll.setOnClickListener {

              for (i in array){
                  var rand = (0..5).random()
                  imageView.setImageResource(array[rand])
              }
        }




    }
}