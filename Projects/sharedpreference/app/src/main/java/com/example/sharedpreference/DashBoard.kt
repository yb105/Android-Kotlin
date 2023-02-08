package com.example.sharedpreference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        var tv = findViewById<TextView>(R.id.tvName)
        var btnlogout = findViewById<Button>(R.id.btnlogout)

        var sharedPreferences: SharedPreferences = this.getSharedPreferences("mypref_file", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()

        tv.text = sharedPreferences.getString("name","default")

        btnlogout.setOnClickListener {

            editor.clear()
            editor.commit()

            var i = Intent(this,MainActivity::class.java)
            finish()
            startActivity(i)
        }
    }
}