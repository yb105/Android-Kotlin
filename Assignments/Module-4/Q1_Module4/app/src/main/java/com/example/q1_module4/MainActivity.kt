package com.example.q1_module4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ed = findViewById<EditText>(R.id.edNum)
        var t = findViewById<TextView>(R.id.txtNum)
        var num = 0
//
//        ed.addTextChangedListener(object : TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                t.setText("")
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                var num = ed.text.toString()
//
//                t.setText(num)
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
//            }
//        })

        ed.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
                Log.d("mydata","before text")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                num = ed.text.toString().toInt()
                var reverse = 0
                var rem : Int

                while (num>0){

                    rem = num%10
                    reverse = reverse *10 + rem
                    num = num/10
                }

                t.setText(""+reverse)

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

        }




