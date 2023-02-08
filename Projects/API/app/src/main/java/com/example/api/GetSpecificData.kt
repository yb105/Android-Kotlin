package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetSpecificData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_specific_data)

        var edname = findViewById<EditText>(R.id.ed_name)
        var txtData = findViewById<TextView>(R.id.txtData)
        var btnsubmit = findViewById<Button>(R.id.btnsubmit)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://yashpalbamniya.000webhostapp.com/").addConverterFactory(
                GsonConverterFactory.create()).build()
            .create(ApiInterface::class.java)




        btnsubmit.setOnClickListener {

            var results = retrofit.getspecific(edname.text.toString())

            results.enqueue(object : Callback<List<StudentData>?>{
                override fun onResponse(
                    call: Call<List<StudentData>?>,
                    response: Response<List<StudentData>?>
                ) {
                    var responseBody = response.body()

                    for(data in responseBody!!){

                        txtData.setText(data.city)
                    }
                }

                override fun onFailure(call: Call<List<StudentData>?>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })

        }
    }
}