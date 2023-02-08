package com.example.q3_module4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.q3_module4.R.id.group

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var radioGroup = findViewById<RadioGroup>(R.id.group)


        var edt1 = findViewById<EditText>(R.id.ed1)
        var edt2 = findViewById<EditText>(R.id.ed2)
        var txt = findViewById<TextView>(R.id.txt)
        var btn = findViewById<Button>(R.id.btn)
        var value : Int






             radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                 if (i == R.id.add){

                     btn.setOnClickListener {
                         value = edt1.text.toString().toInt() + edt2.text.toString().toInt()
                         txt.setText("" + value)
                     }
                 }

                 if (i == R.id.sub){


                     Toast.makeText(this, "sub", Toast.LENGTH_SHORT).show()

                     btn.setOnClickListener {
                         value = edt1.text.toString().toInt() - edt2.text.toString().toInt()
                         txt.setText("" + value)
                     }
                 }

                 if (i == R.id.multi){

                     btn.setOnClickListener {
                         value = edt1.text.toString().toInt() * edt2.text.toString().toInt()
                         txt.setText("" + value)
                     }
                 }

                 if (i == R.id.div){


                     btn.setOnClickListener {
                         value = edt1.text.toString().toInt() / edt2.text.toString().toInt()
                         txt.setText("" + value)
                     }
                 }
             }

         }

    }
