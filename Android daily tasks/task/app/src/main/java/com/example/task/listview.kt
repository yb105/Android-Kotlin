package com.example.task

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class listview : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        var listView = findViewById<ListView>(R.id.listView)

        listView.setOnItemClickListener { adapterView, view, i, l ->

            when(i){

                0 -> {var c = Intent(this@listview,c::class.java)
                finish()
                startActivity(c)}

                1 -> {var c = Intent(this@listview,c_plus_plus::class.java)
                    finish()
                    startActivity(c)}

                2 -> {var c = Intent(this@listview,java::class.java)
                    finish()
                    startActivity(c)}

                3 -> {var c = Intent(this@listview,python::class.java)
                    finish()
                    startActivity(c)}


            }
        }


    }


}