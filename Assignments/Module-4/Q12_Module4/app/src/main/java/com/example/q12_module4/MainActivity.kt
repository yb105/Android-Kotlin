package com.example.q12_module4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.marginLeft

class MainActivity : AppCompatActivity() {
    private var num = 0
    lateinit var l:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ed = findViewById<EditText>(R.id.ed1)
        var btn = findViewById<Button>(R.id.btn)
        l = findViewById<LinearLayout>(R.id.l)

        btn.setOnClickListener {
            num = ed.text.toString().toInt()

           while(num>0){

               edText()
               num--
           }
        }
    }

    fun edText(){

        var edt = EditText(this)
        edt.layoutParams = LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT)
        edt.hint = "EnterNumber"
        edt.setPadding(30,30,30,30)
        l.addView(edt)


    }
}