package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Update_Student : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_student)

        var edna = findViewById<EditText>(R.id.ed_update_name)
        var ednci = findViewById<EditText>(R.id.ed_update_city)
        var btnsubmit = findViewById<Button>(R.id.btnsubmitupdate)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://yashpalbamniya.000webhostapp.com/").addConverterFactory(
                GsonConverterFactory.create()).build()
            .create(ApiInterface::class.java)


        btnsubmit.setOnClickListener {

            var results = retrofit.update_student(edna.text.toString(),ednci.text.toString())

            results.enqueue(object : Callback<StudentData?>{
                override fun onResponse(
                    call: Call<StudentData?>,
                    response: Response<StudentData?>
                ) {
                    Toast.makeText(this@Update_Student, "Updated", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<StudentData?>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })
        }
    }
}