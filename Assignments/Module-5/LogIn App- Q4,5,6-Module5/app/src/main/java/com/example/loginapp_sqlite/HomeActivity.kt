package com.example.loginapp_sqlite

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)




        var recView = findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)

        var result = Retro.retrofit.getallproject()

        result.enqueue(object : Callback<List<ProjectModel>?> {
            override fun onResponse(
                call: Call<List<ProjectModel>?>,
                response: Response<List<ProjectModel>?>
            ) {
                var responsebody = response.body() as List<ProjectModel>
                Log.d("@@@","$responsebody")
                recView.adapter = MyAdapter(this@HomeActivity,responsebody)
            }

            override fun onFailure(call: Call<List<ProjectModel>?>, t: Throwable) {
                Log.e("@@","error")
            }
        })
        var fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
         adddatawithdialog()
        }
    }

    private fun adddatawithdialog() {
        var d = Dialog(this)
        d.setContentView(R.layout.addprojectdetails)
        d.show()

        var edT = d.findViewById<EditText>(R.id.edTitle)
        var edD = d.findViewById<EditText>(R.id.edDescription)
        var btnAdd = d.findViewById<Button>(R.id.btnAddSticky)

        btnAdd.setOnClickListener {

             var results = Retro.retrofit.project_details(edT.text.toString(),edD.text.toString())
            results.enqueue(object : Callback<ProjectModel?> {
                override fun onResponse(
                    call: Call<ProjectModel?>,
                    response: Response<ProjectModel?>
                ) {
                    d.dismiss()
                    var i = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(i)
                }

                override fun onFailure(call: Call<ProjectModel?>, t: Throwable) {
                    d.dismiss()
                    var i = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(i)
                }
            })


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.profile,menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var id = intent.getIntExtra("id",0)
        var username = intent.getStringExtra("user")
        if (item.itemId == R.id.profileMenu){
          var i = Intent(this,ProfileActivity::class.java)
            i.putExtra("idd",id)
            i.putExtra("userr",username)
            startActivity(i)

        }

        return super.onOptionsItemSelected(item)
    }

}