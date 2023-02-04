package com.example.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(val context: Context, val userlist:ArrayList<UserModel>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val profile_image = itemView.findViewById<ImageView>(R.id.profile_image)
        val username = itemView.findViewById<TextView>(R.id.username_display)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.row_user,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.username.text = userlist[position].username
        Glide.with(context).load(userlist[position].profile_pic).into(holder.profile_image)
    }

    override fun getItemCount(): Int  =  userlist.size
}