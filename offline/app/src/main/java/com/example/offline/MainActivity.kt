package com.example.offline

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var t : String
    lateinit var arrayList:ArrayList<NoteModel>
    lateinit var d :String
    lateinit var rev :RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
         rev = findViewById(R.id.revView)
        arrayList = ArrayList()




        fab.setOnClickListener{

          dialog()

        }
        rev.layoutManager = LinearLayoutManager(this)
        var dataBase = DataBaseHandler(this)
        arrayList = dataBase.viewDataBase()
        rev.adapter = MyAdapter(this,arrayList)

    }

    fun dialog(){

        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialoglayout)
        dialog.setTitle("Add Note")
        val edT = dialog.findViewById<EditText>(R.id.edTitle)
        val edD = dialog.findViewById<EditText>(R.id.edDescription)
        val btnAdd = dialog.findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {

            var id: Int

            var t = edT.text.toString()
            var d = edD.text.toString()

            var dataBaseHandler:DataBaseHandler = DataBaseHandler(this)

            dataBaseHandler.addNotesToDatabase(NoteModel(it!!.id,t,d))

            dialog.dismiss()


        }


        dialog.show()
    }




}