package com.example.digitalsociety.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digitalsociety.Model.UserModel
import com.example.digitalsociety.R

class AllMemberAdapter(var context: Context,var arraylist: ArrayList<UserModel>):RecyclerView.Adapter<AllMemberAdapter.MyViewHolder>() {
    class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
            var im = itemview.findViewById<ImageView>(R.id.profile_imageViewAllmember)
        var txtusername = itemview.findViewById<TextView>(R.id.username_viewallmember_rev)
        var txtpostion = itemview.findViewById<TextView>(R.id.position_viewallmember_rev)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.revviewallmembers,parent,false)

        return MyViewHolder(v)
    }

    override fun getItemCount() = arraylist.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtusername.text = arraylist[position].username
        holder.txtpostion.text = arraylist[position].postion

        if (arraylist[position].profile_pic == ""){
            holder.im.setImageResource(R.drawable.chairman)
        }else{
            Glide.with(context).load(arraylist[position].profile_pic).placeholder(R.drawable.chairman).into(holder.im)
        }
    }
}