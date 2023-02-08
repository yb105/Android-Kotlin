package com.example.api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var edname = findViewById<EditText>(R.id.edNAme)
        var edsub = findViewById<EditText>(R.id.edSubject)
        var edcity = findViewById<EditText>(R.id.edcity)
        var btnadd = findViewById<Button>(R.id.btnAdd)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://yashpalbamniya.000webhostapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiInterface::class.java)

        btnadd.setOnClickListener {

            var results = retrofit.insert_student(edname.text.toString(),edsub.text.toString(),edcity.text.toString())

            results.enqueue(object :Callback<StudentData?>{
                override fun onResponse(
                    call: Call<StudentData?>,
                    response: Response<StudentData?>
                ) {
                    Log.d("mydata","Succesfully data Added")
                }

                override fun onFailure(call: Call<StudentData?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

            val i = Intent(this,All_Student::class.java)
            startActivity(i)

        }
    }
}