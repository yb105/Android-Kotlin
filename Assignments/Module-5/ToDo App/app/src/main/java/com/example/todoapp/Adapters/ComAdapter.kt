package com.example.todoapp.Adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.DataBaseHandler
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.ToDoModel
import com.example.todoapp.fragments.CompletedTasksFragment

class ComAdapter(var context: Context,var arrayList: MutableList<ToDoModel>):RecyclerView.Adapter<ComAdapter.MyViewHolder>() {
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var task = view.findViewById<TextView>(R.id.txtcomtask)
        var imcom = view.findViewById<ImageView>(R.id.imCom)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.comlayout,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount() = arrayList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       var m = arrayList[position]
        holder.task.text = m.task

        holder.imcom.setOnClickListener {

            var dataBaseHandler = DataBaseHandler(context)
            dataBaseHandler.deleteCompletedData(ToDoModel(m.idd,m.task))
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            var i = Intent(context,MainActivity::class.java)
            context.startActivity(i)
        }
    }
}
