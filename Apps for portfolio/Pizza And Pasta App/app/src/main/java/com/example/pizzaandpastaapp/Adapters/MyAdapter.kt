package com.example.pizzaandpastaapp.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaandpastaapp.Models.MyModel
import com.example.pizzaandpastaapp.OrderMenuPage
import com.example.pizzaandpastaapp.OrderPage
import com.example.pizzaandpastaapp.R

class MyAdapter(var context: Context, var arrayList: ArrayList<MyModel>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


   lateinit var quantityList: ArrayList<Int>
   var q1 = 0
    var q2 = 0

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var t = itemView.findViewById<TextView>(R.id.txttitle)
        var p = itemView.findViewById<TextView>(R.id.txtprice)
        var im = itemView.findViewById<ImageView>(R.id.imageItem)
        var txtQ = itemView.findViewById<TextView>(R.id.txtQuantity)
        var btnplus = itemView.findViewById<Button>(R.id.btnPlus)
        var btnMinus = itemView.findViewById<Button>(R.id.btnMinus)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.layoutcard,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       var model = arrayList[position]
        holder.t.text = model.title
        holder.p.text = model.price
        holder.im.setImageResource(model.image)

        quantityList = ArrayList<Int>()
        quantityList.add(0)
        quantityList.add(0)

        holder.txtQ.text = quantityList[position].toString()

        var q = quantityList[position]

        holder.btnplus.setOnClickListener {

            q = q + 1
            holder.txtQ.text = q.toString()

            when(position){

                0 -> q1  += 1
                1 -> q2 += 1
            }

            Log.d("hi", "$q1")
            Log.d("hi", "$q2")


        }

        holder.btnMinus.setOnClickListener {

            if(q > 0) {
                q = q - 1
                holder.txtQ.text = q.toString()
                when(position){

                    0 -> q1  -= 1
                    1 -> q2 -= 1
                }

                Log.d("hi", "$q1")
                Log.d("hi", "$q2")

            }
        }
    }

    override fun getItemCount() = arrayList.size



}
