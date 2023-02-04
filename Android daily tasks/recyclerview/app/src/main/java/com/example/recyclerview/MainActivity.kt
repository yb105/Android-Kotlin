package com.example.recyclerview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.RecyclerView.Model
import com.example.recyclerview.RecyclerView.MyAdapter
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val c = Calendar.getInstance()

        var arrayList = ArrayList<Model>()

        var hour = c.get(Calendar.HOUR)
        var min = c.get(Calendar.MINUTE)
        var time = "$hour:$min"


        var recyclerview  = findViewById<RecyclerView>(R.id.reView)

        recyclerview.layoutManager = LinearLayoutManager(this)
        arrayList.add(Model(R.mipmap.ic_launcher,"Android",time))
        arrayList.add(Model(R.mipmap.ic_launcher,"Android",time))
        arrayList.add(Model(R.mipmap.ic_launcher,"Android",time))
        arrayList.add(Model(R.mipmap.ic_launcher,"Android",time))
        arrayList.add(Model(R.mipmap.ic_launcher,"Android",time))
        arrayList.add(Model(R.mipmap.ic_launcher,"Android",time))
        arrayList.add(Model(R.mipmap.ic_launcher,"Android",time))
        arrayList.add(Model(R.mipmap.ic_launcher,"Android",time))
        arrayList.add(Model(R.mipmap.ic_launcher,"Android",time))

        var adapter = MyAdapter(this@MainActivity,arrayList)
        recyclerview.adapter = adapter


    }
}