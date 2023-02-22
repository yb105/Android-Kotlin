package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rev = findViewById<RecyclerView>(R.id.revView)
        var fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        var list:List<DataUser> = listOf()

        rev.layoutManager = LinearLayoutManager(this)

        var result = Retro.retrofit.getalluser().enqueue(object : Callback<List<DataUser>?> {
            override fun onResponse(
                call: Call<List<DataUser>?>,
                response: Response<List<DataUser>?>
            ) {
                list = response.body() as List<DataUser>

                rev.adapter = UserAdapter(this@MainActivity,list)
            }

            override fun onFailure(call: Call<List<DataUser>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddUser::class.java))
        }
    }
}