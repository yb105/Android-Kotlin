package com.example.roomdatabase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomdatabase.room.Data
import com.example.roomdatabase.room.personDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyAdapter(var context:Context,var list: List<Data>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {

        var txtname = itemview.findViewById<TextView>(R.id.txt_name)
        var txtage = itemview.findViewById<TextView>(R.id.txt_age)
        var btndelete = itemview.findViewById<ImageView>(R.id.iconDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rev_layout,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var lis = list[position]
        holder.txtname.text = lis.name
        holder.txtage.text = lis.age.toString()

        var database: personDB

        database = Room.databaseBuilder(context,
            personDB::class.java,
            "personDB").build()



        holder.btndelete.setOnClickListener {
        GlobalScope.launch {
            database.dao().deleteData(Data(lis.id,lis.name,lis.age))
        }
           refresh()
        }
    }

    fun refresh(){

        var i = Intent(context,MainActivity::class.java)
        context.startActivity(i)
    }
}