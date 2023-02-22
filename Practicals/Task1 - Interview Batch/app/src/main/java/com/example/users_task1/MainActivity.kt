package com.example.users_task1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var fab:FloatingActionButton
    var arrayList:ArrayList<UserData> = ArrayList()
    lateinit var dataBase: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataBase  = DataBase.getDatabase(this)

        recyclerView = findViewById(R.id.recyclerView)
        fab = findViewById(R.id.floatingActionButton)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dataBase.taskdao().getallData().observe(this, Observer {
            arrayList = it as ArrayList<UserData>
            recyclerView.adapter = UserAdapter(this,arrayList)
        })






        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity,UserActivity::class.java))
        }
    }

}