package com.example.offline

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var context: Context, var arrayList: ArrayList<NoteModel>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var t = itemView.findViewById<TextView>(R.id.txtTitle)
        var d = itemView.findViewById<TextView>(R.id.txtDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.adapter,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var model = arrayList[position]

        holder.t.text = model.title
        holder.d.text = model.description
    }

    override fun getItemCount() = arrayList.size
}