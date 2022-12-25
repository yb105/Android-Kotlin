package com.example.task

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast

class Menu : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var btnPopUp = findViewById<Button>(R.id.btnPopup)
        var contextM = findViewById<TextView>(R.id.contextTxt)


        registerForContextMenu(contextM)

        btnPopUp.setOnClickListener {


            var pm = PopupMenu(this@Menu, btnPopUp)
            pm.menuInflater.inflate(R.menu.mymenu,pm.menu)
            pm.show()

            pm.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
                when(it.itemId){

                    R.id.setting -> Toast.makeText(this@Menu, "Setting", Toast.LENGTH_SHORT).show()
                }


                return@OnMenuItemClickListener true
            })
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.mymenu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.setting -> Toast.makeText(this@Menu, "Setting", Toast.LENGTH_SHORT).show()

            R.id.profile -> Toast.makeText(this@Menu, "Profile", Toast.LENGTH_SHORT).show()


            R.id.search -> Toast.makeText(this@Menu, "Search", Toast.LENGTH_SHORT).show()

            R.id.logout -> Toast.makeText(this@Menu, "logout", Toast.LENGTH_SHORT).show()

        }




        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {

        menuInflater.inflate(R.menu.mymenu,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.setting -> Toast.makeText(this@Menu, "Setting", Toast.LENGTH_SHORT).show()
            R.id.profile -> Toast.makeText(this@Menu, "Profile", Toast.LENGTH_SHORT).show()
        }

        return super.onContextItemSelected(item)
    }
}