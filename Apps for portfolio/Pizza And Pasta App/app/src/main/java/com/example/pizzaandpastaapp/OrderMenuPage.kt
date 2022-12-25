package com.example.pizzaandpastaapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.pizzaandpastaapp.Adapters.MyAdapter
import com.example.pizzaandpastaapp.Models.MyModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OrderMenuPage : AppCompatActivity() {

     lateinit var recyclerView: RecyclerView
     lateinit var arrayList: ArrayList<MyModel>
     var q1 = 0
    var q2 = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_menu_page)

        var btnorder = findViewById<Button>(R.id.btnorderNow)

        arrayList = ArrayList<MyModel>()

        arrayList.add(MyModel("Large Pizza",R.drawable.pizza,"₹100"))
        arrayList.add(MyModel("Large Pasta",R.drawable.pasta,"₹80"))

        recyclerView = findViewById(R.id.recylerView)

        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = MyAdapter(this@OrderMenuPage,arrayList)

        var obj =MyAdapter(this@OrderMenuPage,arrayList)
        q1 = obj.q1
        q2 = obj.q2

        btnorder.setOnClickListener {

               loadDialog()
        }



    }
    fun loadDialog(){
        var d = Dialog(this@OrderMenuPage)
        d.setContentView(R.layout.namedialoglayout)
        d.setCancelable(false)
        d.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        var ed = d.findViewById<EditText>(R.id.edtName)
        var btn = d.findViewById<Button>(R.id.btnNameDone)

        btn.setOnClickListener {

            var i = Intent(this@OrderMenuPage,OrderPage::class.java)
            i.putExtra("q1", q1)
            i.putExtra("q2",q2)
            i.putExtra("name","${ed.text.toString()}")
            startActivity(i)


        }
        d.show()

    }
}