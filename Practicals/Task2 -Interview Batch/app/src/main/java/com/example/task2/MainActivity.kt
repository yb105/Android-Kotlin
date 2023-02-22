package com.example.task2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var revCategory:RecyclerView
    lateinit var revALlItem:RecyclerView
     lateinit var database:TaskDataBase
    var categoryList:List<CategoryModel> = listOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = TaskDataBase.getDatabase(this)

        revCategory = findViewById(R.id.revCategory)
        revALlItem = findViewById(R.id.revAllItem)


        revCategory.layoutManager = GridLayoutManager(this,3)

         database.taskdao().getallData().observe(this,{
             categoryList = it
             revCategory.adapter = CategoryAdapter(this@MainActivity,categoryList)
         })



        revALlItem.layoutManager = LinearLayoutManager(this)
        database.taskdao().getallItemData().observe(this, {

            revALlItem.adapter = AllitemAdapter(this@MainActivity,it)
        })












    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.category -> {startActivity(Intent(this@MainActivity,AddCategory::class.java))}
            R.id.additem -> {startActivity(Intent(this@MainActivity,AddItem::class.java))}
            R.id.cancel -> {this.closeContextMenu()}

        }

        return super.onOptionsItemSelected(item)
    }
}