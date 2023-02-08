package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        var edadd = findViewById<EditText>(R.id.edAddTodo)
        var btnadd = findViewById<Button>(R.id.btnAddTodo)

        var dataBaseHandler = DataBaseHandler(this@AddTask)
        var id:Int

        btnadd.setOnClickListener {

           var adddata =  dataBaseHandler.addStudentData(ToDoModel(it.id,edadd.text.toString()))
            if (adddata>1) {
                Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show()
            }

            var i = Intent(this@AddTask,MainActivity::class.java)
            startActivity(i)

        }
    }
}