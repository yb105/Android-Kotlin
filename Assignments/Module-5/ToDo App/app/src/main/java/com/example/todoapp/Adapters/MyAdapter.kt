package com.example.todoapp.Adapters

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
import kotlin.math.log

class MyAdapter(var context: Context,var arrayList: MutableList<ToDoModel>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var task = view.findViewById<TextView>(R.id.txtTask)
        var imedit = view.findViewById<ImageView>(R.id.imEdit)
        var imdone = view.findViewById<ImageView>(R.id.imDone)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var v = LayoutInflater.from(parent.context).inflate(R.layout.tasklayout,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var m = arrayList[position]
        holder.task.text = m.task
        holder.imedit.setOnClickListener {
            var d = Dialog(context)
            d.setContentView(R.layout.editdialog)
            d.show()

            var ed = d.findViewById<EditText>(R.id.edEditTOdo)
            var btn = d.findViewById<Button>(R.id.btnEditAdd)
            btn.setOnClickListener {

                var dataBaseHandler = DataBaseHandler(context)
                dataBaseHandler.updateData(ToDoModel(m.idd, ed.text.toString()))

                var i = Intent(context,MainActivity::class.java)
                context.startActivity(i)
        }


    }

        holder.imdone.setOnClickListener {

            Log.d("t", "hii")

            Toast.makeText(context, "Hiii", Toast.LENGTH_SHORT).show()

            var dataBaseHandler = DataBaseHandler(context)
            var done =  dataBaseHandler.addCompletedData(ToDoModel(it.id,m.task))
            if (done>1){
                Toast.makeText(context, "Successfully Addded", Toast.LENGTH_SHORT).show()
            }

            dataBaseHandler.deleteData(ToDoModel(m.idd,m.task))

            var i = Intent(context,MainActivity::class.java)
            context.startActivity(i)


        }




    }

    override fun getItemCount() = arrayList.size
}