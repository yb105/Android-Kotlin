package com.example.updateentries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var context: Context, var arrayList: ArrayList<MyModel> ): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt = itemView.findViewById<TextView>(R.id.txtEntry)
        var im = itemView.findViewById<ImageView>(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.recycleliewlayout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var my = arrayList[position]

       holder.txt.setText(my.entry)
        holder.im.setImageResource(my.icon)

        var st =  holder.txt.setText(my.entry)

        holder.im.setOnClickListener{

            arrayList.remove(arrayList[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = arrayList.size

}