package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

        var btnfinish = findViewById<Button>(R.id.btnfinish)
        var txtscore = findViewById<TextView>(R.id.txtscore)
        var txtname = findViewById<TextView>(R.id.txtfname)
        var txtcheer = findViewById<TextView>(R.id.txtcheer)




        var score = intent.getIntExtra(Constants.score,0)

      if (score==0){

          txtcheer.text = "Do Better Next Time!"
      }

        txtname.text = intent.getStringExtra(Constants.name2)
        txtscore.text = "Your score is $score out of 10."

        btnfinish.setOnClickListener {

            var i = Intent(this,MainActivity::class.java)
            finish()
            startActivity(i)
        }

    }
}