package com.example.q2_module4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ed1 = findViewById<EditText>(R.id.ed1)
        var ed2 = findViewById<EditText>(R.id.ed2)
        var btn = findViewById<Button>(R.id.btndone)



        btn.setOnClickListener {
            var num1:Int = ed1.text.toString().toInt()
            var num2:Int = ed2.text.toString().toInt()

            var i = Intent(this@MainActivity,MainActivity2::class.java)
            i.putExtra("num1",num1)
            i.putExtra("num2",num2)
            startActivity(i)
        }
    }
}