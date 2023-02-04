package com.example.offlinedatabase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fab = findViewById<FloatingActionButton>(R.id.fab_add)
        var rev = findViewById<RecyclerView>(R.id.recyclerView)

        rev.layoutManager = LinearLayoutManager(this@MainActivity)


        fab.setOnClickListener{
        var i = Intent(this@MainActivity,SDB_add_student::class.java)
            finish()
            startActivity(i)

        }

        var mydb = DataBaseHandler(this)

        var mystudentlist :MutableList<StudentModel> = ArrayList()
        mystudentlist = mydb.getallData()

        rev.adapter = MyAdapter(this,mystudentlist)
    }
}


































