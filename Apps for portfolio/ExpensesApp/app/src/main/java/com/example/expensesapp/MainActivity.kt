package com.example.expensesapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fab = findViewById<FloatingActionButton>(R.id.btnFab)
        var rev = findViewById<RecyclerView>(R.id.revView)
        var txtSalary = findViewById<TextView>(R.id.txtsalary)

        txtSalary.setOnLongClickListener {

            var d = Dialog(this)
            d.setContentView(R.layout.dialoglayout)
            d.show()

            var edS = d.findViewById<EditText>(R.id.edSalary)
            var btnS = d.findViewById<Button>(R.id.btnAdd)


            btnS.setOnClickListener {

                txtSalary.text = "₹${edS.text.toString()}"
                d.dismiss()
            }

            return@setOnLongClickListener true
        }

        fab.setOnClickListener{
            addScreen()
        }
    }

    fun addScreen(){
        var i = Intent(this@MainActivity,AddExpenses::class.java)
        startActivity(i)
    }
}