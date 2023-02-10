package com.example.digitalsociety.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalsociety.Model.NoticeModel
import com.example.digitalsociety.R

class NoticeAdapter(var context: Context, var arrayList: ArrayList<NoticeModel>): RecyclerView.Adapter<NoticeAdapter.MyViewHolder>() {
    class MyViewHolder(itemVIew: View):RecyclerView.ViewHolder(itemVIew) {

        var title = itemVIew.findViewById<TextView>(R.id.txtviewnoticetitle)
        var des = itemVIew.findViewById<TextView>(R.id.txtviewnoticedes)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.viewnoticerev,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = arrayList[position].ntitle
        holder.des.text = arrayList[position].notice

    }
}