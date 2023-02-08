package com.example.api

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class All_Student : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_student)

        var rev = findViewById<RecyclerView>(R.id.revView)

        rev.layoutManager = LinearLayoutManager(this)




        var retrofit = Retrofit.Builder()
            .baseUrl("https://yashpalbamniya.000webhostapp.com/").addConverterFactory(
                GsonConverterFactory.create()).build()
            .create(ApiInterface::class.java)

        var results = retrofit.getallemp()

        results.enqueue(object:Callback<List<StudentData>?>{
            override fun onResponse(
                call: Call<List<StudentData>?>,
                response: Response<List<StudentData>?>
            ) {
                var responsebody = response.body() as List<StudentData>
                rev.adapter = Student_Data_Adapter(this@All_Student,responsebody)
            }

            override fun onFailure(call: Call<List<StudentData>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}