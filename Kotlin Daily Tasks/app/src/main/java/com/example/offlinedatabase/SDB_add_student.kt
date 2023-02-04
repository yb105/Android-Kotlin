package com.example.offlinedatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SDB_add_student : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sdb_add_student)

        var ed_name = findViewById<EditText>(R.id.ed_name)
        var ed_subject = findViewById<EditText>(R.id.ed_subject)
        var btn_save = findViewById<Button>(R.id.btn_save)

        var mydatabasehandler = DataBaseHandler(this@SDB_add_student)
        var id: Int

        btn_save.setOnClickListener {

            var insertId = mydatabasehandler.addStudentData(StudentModel(it!!.id,ed_name.text.toString(),ed_subject.text.toString()))
            if(insertId>0){
                Toast.makeText(this@SDB_add_student, "Successfully data inserted", Toast.LENGTH_SHORT).show()
            }

            var i = Intent(this@SDB_add_student,MainActivity::class.java)
            finish()
           startActivity(i)
        }
    }
}