package com.example.offlinedatabase

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var context:Context, var studentList:MutableList<StudentModel>) : RecyclerView.Adapter<MyAdapter.Myclass>(){
    class Myclass(itemView: View): RecyclerView.ViewHolder(itemView) {

        var name = itemView.findViewById<TextView>(R.id.tv_db_name)
        var subject = itemView.findViewById<TextView>(R.id.tv_db_subject)
        var btnedit = itemView.findViewById<Button>(R.id._btn_db_edit)
        var btndelete = itemView.findViewById<Button>(R.id._btn_db_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myclass {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.db_rowlayout,parent,false)
        return Myclass(v)
    }

    override fun onBindViewHolder(holder: Myclass, position: Int) {
       var mystudentmodel = studentList[position]

        holder.name.text = mystudentmodel.name
        holder.subject.text = mystudentmodel.subject

        holder.btnedit.setOnClickListener {

            var d = Dialog(context)
            d.setContentView(R.layout.dialoglayout)
            d.show()

            var edtext = d.findViewById<EditText>(R.id.edUpdate)
            var btn = d.findViewById<Button>(R.id.btnUpdate)

            edtext.setText(mystudentmodel.name)

            btn.setOnClickListener {
                var mydb = DataBaseHandler(context)

                var mystudentmodel = StudentModel(mystudentmodel.id,edtext.text.toString(),mystudentmodel.subject)
                mydb.updateData(mystudentmodel)

                var i = Intent(context,MainActivity::class.java)
                (context as Activity).finish()
                context.startActivity(i)
            }
        }



        holder.btndelete.setOnClickListener {

           var mydb = DataBaseHandler(context)

            var mystudent = StudentModel(mystudentmodel.id,mystudentmodel.name,mystudentmodel.subject)
            mydb.deleteData(mystudentmodel)

            var i = Intent(context,MainActivity::class.java)
            (context as Activity).finish()
            context.startActivity(i)

        }
    }

    override fun getItemCount() = studentList.size
}

