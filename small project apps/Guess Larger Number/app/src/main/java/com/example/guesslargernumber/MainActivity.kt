package com.example.guesslargernumber

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.Layout

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "ResourceAsColor")

    var leftnum = 0
     var rightnum  = 0
    lateinit var btnleft:Button
    lateinit var btnright:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         btnleft = findViewById<Button>(R.id.btnLeft)
        btnright = findViewById<Button>(R.id.btnRight)
        val layout = findViewById<LinearLayout>(R.id.l_main)

        random()

        btnleft.setOnClickListener {

            if (leftnum>rightnum){

                Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show()
                layout.setBackgroundColor(Color.GREEN)
                random()


            }else{
                Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show()
                layout.setBackgroundColor(Color.RED)
                random()
            }


        }

        btnright.setOnClickListener {
            if (rightnum>leftnum){
                Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show()
                layout.setBackgroundColor(Color.GREEN)
                random()
            }else{

                Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show()
                layout.setBackgroundColor(Color.RED)
                random()
            }
        }

    }

    fun random(){

        leftnum = (1..100).random()
        rightnum = (1..100).random()

        btnleft.setText(leftnum.toString())
        btnright.setText(rightnum.toString())


    }

}