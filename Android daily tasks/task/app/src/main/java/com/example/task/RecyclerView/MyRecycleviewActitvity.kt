package com.example.task.RecyclerView

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R

class MyRecycleviewActitvity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_recycleview_actitvity)

        var recyclerview = findViewById<RecyclerView>(R.id.rev_view)

        recyclerview.layoutManager = LinearLayoutManager(this@MyRecycleviewActitvity)

        var myarraylist = ArrayList<MyModel>()

        myarraylist.add(MyModel(R.mipmap.ic_launcher,"Android"))
        myarraylist.add(MyModel(R.mipmap.ic_launcher,"Java"))
        myarraylist.add(MyModel(R.mipmap.ic_launcher,"Php"))
        myarraylist.add(MyModel(R.mipmap.ic_launcher,"Python"))

        var adapter = MyAdapter(this@MyRecycleviewActitvity,myarraylist)
        recyclerview.adapter = adapter
    }
}