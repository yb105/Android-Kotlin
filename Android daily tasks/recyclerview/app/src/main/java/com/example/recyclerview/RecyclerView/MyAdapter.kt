package com.example.recyclerview.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class MyAdapter(var context: Context,var arraylist : ArrayList<Model>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
               var i = itemView.findViewById<ImageView>(R.id.logo)
        var t = itemView.findViewById<TextView>(R.id.time)
        var tt = itemView.findViewById<TextView>(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.layoutrecycler,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = arraylist[position]
        holder.i.setImageResource(model.image)
        holder.t.setText(model.time)
        holder.tt.setText(model.name)
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }
}