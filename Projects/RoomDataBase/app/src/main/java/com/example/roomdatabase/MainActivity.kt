package com.example.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import androidx.room.Room
import com.example.roomdatabase.databinding.ActivityMainBinding
import com.example.roomdatabase.room.Data
import com.example.roomdatabase.room.personDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var database: personDB
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = Room.databaseBuilder(applicationContext,
        personDB::class.java,
        "personDB").build()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
         database.dao().getallData().observe(this,{
             var personList:List<Data> = it

             binding.recyclerView.adapter =  MyAdapter(this,personList)
        })


        binding.btnAdd.setOnClickListener {

            GlobalScope.launch {

                database.dao().insertData(Data(0,binding.edName.text.toString(),binding.edAge.text.toString().toInt()))
            }
             var i  = Intent(this,MainActivity::class.java)
            startActivity(i)

        }


    }
}