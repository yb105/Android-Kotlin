package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUser : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        var edname = findViewById<EditText>(R.id.edname)
        var edcompany  = findViewById<EditText>(R.id.edcompany)
        var edpostion = findViewById<EditText>(R.id.edpostion)
        var btn = findViewById<Button>(R.id.btnAdd)


        btn.setOnClickListener {

            var result = Retro.retrofit.insert_user(edname.text.toString(),edcompany.text.toString(),edpostion.text.toString())
            result.enqueue(object : Callback<DataUser?> {
                override fun onResponse(call: Call<DataUser?>, response: Response<DataUser?>) {
                    Toast.makeText(this@AddUser, "Data Added", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this@AddUser,MainActivity::class.java))
                }

                override fun onFailure(call: Call<DataUser?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}