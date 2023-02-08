package com.example.sharedpreference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnsave = findViewById<Button>(R.id.btn_save)
        var edname = findViewById<EditText>(R.id.edsharedname)
        var edsubject = findViewById<EditText>(R.id.edsharedsubject)

        var sharedPreferences:SharedPreferences = this.getSharedPreferences("mypref_file",Context.MODE_PRIVATE)
        var editor:SharedPreferences.Editor = sharedPreferences.edit()

        if (sharedPreferences.getBoolean("status",false)){

            var i = Intent(this,DashBoard::class.java)
            finish()
            startActivity(i)
        }

        btnsave.setOnClickListener{


            editor.putString("name",edname.text.toString())
            editor.putString("subject",edsubject.text.toString())
            editor.putBoolean("status",true)
            editor.apply()
            editor.commit()

            var i = Intent(this,DashBoard::class.java)
            finish()
            startActivity(i)
        }
    }
}