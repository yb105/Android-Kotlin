package com.example.task2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CategoryAdapter(var context: Context,var list: List<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.MyHolder>() {

    class MyHolder(item:View):RecyclerView.ViewHolder(item){

         var image = item.findViewById<ImageView>(R.id.categoryimageview)
        var name = item.findViewById<TextView>(R.id.categoryName)
        var cardview = item.findViewById<CardView>(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.revcategorylayout,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.name.text = list[position].categoryName
        var uri = Uri.parse(list[position].categoryProfile)

        Glide.with(context).load(uri).into(holder.image)

        holder.cardview.setOnClickListener {
            var i = Intent(context,ItemCwise::class.java)
            i.putExtra("cname",list[position].categoryName)
            context.startActivity(i)
        }

    }
}