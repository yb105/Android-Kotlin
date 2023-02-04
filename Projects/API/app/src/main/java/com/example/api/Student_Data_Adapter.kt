package com.example.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Student_Data_Adapter(var context: Context, var list: List<StudentData>):RecyclerView.Adapter<Student_Data_Adapter.ViewHolder>() {
    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
      var tvname = itemview.findViewById<TextView>(R.id.tv_name)
        var tvcity = itemview.findViewById<TextView>(R.id.tv_city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.rev_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var list = list[position]

        holder.tvname.text = list.name
        holder.tvcity.text = list.city
    }

    override fun getItemCount() = list.size
}