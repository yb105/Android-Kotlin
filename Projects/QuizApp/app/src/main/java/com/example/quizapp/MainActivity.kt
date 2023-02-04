package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn = findViewById<Button>(R.id.btnStart)
        var edt = findViewById<EditText>(R.id.edNAme)

        btn.setOnClickListener {

           var name = edt.text.toString()

            var i = Intent(this,QuestionScreen::class.java)
            i.putExtra(Constants.name,name)
            startActivity(i)

        }
    }
}