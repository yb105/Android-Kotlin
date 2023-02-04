package com.example.buttoncolor

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         var r :Boolean = true
        var b :Boolean = true
        var g: Boolean = true
        var btnred:Button = findViewById(R.id.btnRed)
        var btngreen: Button = findViewById(R.id.btnGreen)
        var btnblue: Button = findViewById(R.id.btnBlue)

        btnred.setOnClickListener {
            if (r == true){
                btnred.setBackgroundColor(getColor(R.color.red))
                r = false
            }else
            {
                btnred.setBackgroundColor(getColor(R.color.white))
                r =true
            }


        }
        btngreen.setOnClickListener {
            if (g == true){
                btngreen.setBackgroundColor(getColor(R.color.green))
                g = false
            }else
            {
                btngreen.setBackgroundColor(getColor(R.color.white))
                g =true
            }
        }
        btnblue.setOnClickListener {
            if (b == true){
                btnblue.setBackgroundColor(getColor(R.color.blue))
                b = false
            }else
            {
                btnblue.setBackgroundColor(getColor(R.color.white))
                b =true
            }

        }

    }
}