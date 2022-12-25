package com.example.pizzaandpastaapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaandpastaapp.Adapters.OrderAdapter
import com.example.pizzaandpastaapp.Models.OrderModel

class OrderPage : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var arrayList: ArrayList<OrderModel>

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_page)

        var name  = findViewById<TextView>(R.id.txtName)

        name.setText("Hi,${intent.getStringExtra("name")}")

        var quantityPizza = intent.getStringExtra("q1")
        var quantityPasta = intent.getStringExtra("q2")




        arrayList = ArrayList<OrderModel>()


            arrayList.add(OrderModel(R.drawable.pizza, quantityPizza.toString(),"₹100"))
            arrayList.add(OrderModel(R.drawable.pasta, quantityPasta.toString(),"₹80"))


        recyclerView = findViewById(R.id.orderRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = OrderAdapter(arrayList)






    }
}