package com.example.pass_data_to_abother_screen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ReceiverScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver_screen)

        var txt = findViewById<TextView>(R.id.txt)

        txt.setText(intent.getStringExtra("data"))
    }
}