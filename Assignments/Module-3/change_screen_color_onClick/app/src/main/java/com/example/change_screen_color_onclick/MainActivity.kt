package com.example.change_screen_color_onclick

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTap = findViewById<Button>(R.id.btnTap)
        val lLayout = findViewById<LinearLayout>(R.id.l_Linear)
        var clicked = 0



        btnTap.setOnClickListener {
            clicked += 1

            if (clicked>5) clicked = 1

                when(clicked){
                    1 -> lLayout.setBackgroundColor(Color.RED)
                    2 -> lLayout.setBackgroundColor(Color.GREEN)
                    3 -> lLayout.setBackgroundColor(Color.BLUE)
                    4 -> lLayout.setBackgroundColor(Color.YELLOW)
                    5 -> lLayout.setBackgroundColor(Color.parseColor("#FF731D"))
                }


        }

    }
}