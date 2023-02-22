package com.example.task2

import android.content.Context
import android.graphics.Paint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class AllitemAdapter(var context:Context,var list:List<ItemModel>):RecyclerView.Adapter<AllitemAdapter.MyViewHolder>() {
    class MyViewHolder(item: View):RecyclerView.ViewHolder(item) {

        var im = item.findViewById<CircleImageView>(R.id.revAllItemImageView)
        var name = item.findViewById<TextView>(R.id.revProfuctname)
        var des = item.findViewById<TextView>(R.id.revProfuctDescription)
        var mrp = item.findViewById<TextView>(R.id.revProfuctMRP)
        var discount = item.findViewById<TextView>(R.id.revProfuctDiscount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var v = LayoutInflater.from(parent.context).inflate(R.layout.allitemrev,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var l = list[position]
        holder.name.text = l.name
        holder.des.text = l.description
        holder.mrp.text = "₹${l.mrp}"
        holder.mrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        var discountPrice = l.mrp - (l.mrp * l.discount/100 )
        holder.discount.text = "₹$discountPrice"

        var uri = Uri.parse(l.itemImage)

        Glide.with(context).load(uri).into(holder.im)



    }
}