package com.example.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemCwise : AppCompatActivity() {

    lateinit var revView:RecyclerView
    lateinit var database:TaskDataBase
    var ulist =  ArrayList<ItemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_cwise)
        var cname = intent.getStringExtra("cname")

        supportActionBar?.title = cname

        database = TaskDataBase.getDatabase(this)


        Toast.makeText(this, "$cname", Toast.LENGTH_SHORT).show()

        revView = findViewById(R.id.cwiseRev)
        revView.layoutManager = LinearLayoutManager(this)

        database.taskdao().getallItemData().observe(this, {list ->

            ulist = list.filter {
                it.category.lowercase() == cname?.lowercase() } as ArrayList<ItemModel>

            revView.adapter = AllitemAdapter(this@ItemCwise,ulist)
        })

    }
}