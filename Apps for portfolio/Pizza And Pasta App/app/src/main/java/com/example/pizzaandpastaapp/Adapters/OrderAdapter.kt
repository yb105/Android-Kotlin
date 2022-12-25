package com.example.pizzaandpastaapp.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaandpastaapp.Models.OrderModel
import com.example.pizzaandpastaapp.R

class OrderAdapter(var arrayList: ArrayList<OrderModel>): RecyclerView.Adapter<OrderAdapter.MyOrderViewholder>() {


    class MyOrderViewholder(itemview: View): RecyclerView.ViewHolder(itemview) {

        var im = itemview.findViewById<ImageView>(R.id.orderImageView)
        var qt = itemview.findViewById<TextView>(R.id.txtorderQ)
        var p = itemview.findViewById<TextView>(R.id.txtorderP)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderViewholder {
      var v = LayoutInflater.from(parent.context).inflate(R.layout.order_recycle_view,parent,false)
        return MyOrderViewholder(v)
    }

    override fun onBindViewHolder(holder: MyOrderViewholder, position: Int) {
        var orderModel = arrayList[position]
        holder.im.setImageResource(orderModel.imageOrder)
        holder.qt.setText(orderModel.qty.toString())
        holder.p.setText(orderModel.price)
    }

    override fun getItemCount() = arrayList.size
}