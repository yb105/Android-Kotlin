package com.example.task.RecyclerView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R

class MyAdapter(var context:Context,var myarraylist: ArrayList<MyModel>): RecyclerView.Adapter<MyAdapter.MyClass>() {


    class MyClass(itemview: View):RecyclerView.ViewHolder(itemview){

        var im = itemview.findViewById<ImageView>(R.id.imview)
        var tv = itemview.findViewById<TextView>(R.id.tv_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClass {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.myrowlayout,parent,false)
        return MyClass(v)
    }

    override fun onBindViewHolder(holder: MyClass, position: Int) {
        var myModel = myarraylist[position]

        holder.im.setImageResource(myModel.image)
        holder.tv.setText(myModel.name)

        holder.itemView.setOnClickListener {

            Log.d("mdata","Clicked on "+myModel.name)
        }

    }

    override fun getItemCount(): Int {
        return myarraylist.size
    }
}