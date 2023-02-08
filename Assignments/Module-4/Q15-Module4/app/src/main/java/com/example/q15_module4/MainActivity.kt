package com.example.q15_module4

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       var radioGroup = findViewById<RadioGroup>(R.id.radiog)
        var layout = findViewById<ConstraintLayout>(R.id.clayout)

        radioGroup.setOnCheckedChangeListener { radioGroup, i ->


            when(i){
                R.id.red -> layout.setBackgroundColor(Color.RED)
                R.id.blue -> layout.setBackgroundColor(Color.BLUE)
                R.id.green -> layout.setBackgroundColor(Color.GREEN)
            }

        }
    }
}