package com.example.stickynoteapp

import android.app.Dialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var arrayList:ArrayList<StickyModel>
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        var revView = findViewById<RecyclerView>(R.id.revView)
        var fab = findViewById<FloatingActionButton>(R.id.fab)

        arrayList = ArrayList()

        revView.layoutManager = GridLayoutManager(this,2)
        var dataBaseHandler = DataBaseHandler(this)
        var arrayList = dataBaseHandler.getStickyData()
        revView.adapter = MyAdapter(this,arrayList)




        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(0).isEnabled = false

        fab.setOnClickListener {

            adddatawithdialog()


        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun adddatawithdialog() {
        var d = Dialog(this)
        d.setContentView(R.layout.adddialoglayout)
        d.show()

        val date = Calendar.getInstance().time
        val fromatter = SimpleDateFormat("hh:mm aa")
        val time = fromatter.format(date)
        var edT = d.findViewById<EditText>(R.id.edTitle)
        var edD = d.findViewById<EditText>(R.id.edDescription)
        var btnAdd = d.findViewById<Button>(R.id.btnAddSticky)

        btnAdd.setOnClickListener {


            var dataBaseHandler = DataBaseHandler(this)
            var data = dataBaseHandler.addStickyData(StickyModel(0,edT.text.toString(),edD.text.toString(),
                time.toString()
            ))

            d.dismiss()
            var i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }



    }
}