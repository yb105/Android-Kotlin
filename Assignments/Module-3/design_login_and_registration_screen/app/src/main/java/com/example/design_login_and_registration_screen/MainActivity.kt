package com.example.design_login_and_registration_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt = findViewById<TextView>(R.id.txtRegister)

        txt.setOnClickListener {

            var i = Intent(this,RegistrationScreen::class.java)
            startActivity(i)
        }


    }
}