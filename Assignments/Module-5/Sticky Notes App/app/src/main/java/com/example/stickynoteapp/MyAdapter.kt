package com.example.stickynoteapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter(var context:Context,var arrayList: ArrayList<StickyModel>):RecyclerView.Adapter<MyAdapter.MYViewHolder>() {
    class MYViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById<TextView>(R.id.txtTitle)
        var des = itemView.findViewById<TextView>(R.id.txtdes)
        var time = itemView.findViewById<TextView>(R.id.txttime)
        var threedot = itemView.findViewById<TextView>(R.id.txtEllipsis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.revlayout,parent,false)
        return MYViewHolder(v)
    }

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        var model = arrayList[position]

        holder.title.text = model.title
        holder.des.text = model.des
        holder.time.text = model.time

        holder.threedot.setOnClickListener{

            dialog(StickyModel(model.id,model.title,model.des,model.time))
        }
    }

    override fun getItemCount() = arrayList.size

    fun dialog(stickyModel: StickyModel){

        var d = Dialog(context)
        d.setContentView(R.layout.dot)
        d.show()

         var edit = d.findViewById<TextView>(R.id.txtedit)
        var delete = d.findViewById<TextView>(R.id.txtdelete)

        delete.setOnClickListener{

            var dataBaseHandler = DataBaseHandler(context)
            dataBaseHandler.deleteStickyData(stickyModel)

            d.dismiss()
            loadScreen()

        }

        edit.setOnClickListener{

            d.dismiss()


            var di = Dialog(context)
            di.setContentView(R.layout.adddialoglayout)
            di.show()

            val date = Calendar.getInstance().time
            val fromatter = SimpleDateFormat("hh:mm aa")
            val time = fromatter.format(date)
            var edT = di.findViewById<EditText>(R.id.edTitle)
            var edD = di.findViewById<EditText>(R.id.edDescription)
            var btnAdd = di.findViewById<Button>(R.id.btnAddSticky)

            btnAdd.setOnClickListener {

                var dataBaseHandler = DataBaseHandler(context)

                if (edT.text.length >0 && edD.text.isEmpty()  ){

                    dataBaseHandler.updateStickyData(StickyModel(stickyModel.id,edT.text.toString(),stickyModel.des,stickyModel.time))
                } else if (edD.text.length>0 && edT.text.isEmpty()){

                    dataBaseHandler.updateStickyData(StickyModel(stickyModel.id,stickyModel.title,edD.text.toString(),stickyModel.time))
                }else{
                    dataBaseHandler.updateStickyData(StickyModel(stickyModel.id,edT.text.toString(),edD.text.toString(),stickyModel.time))
                }

                di.dismiss()
                loadScreen()
            }
        }


    }


    fun loadScreen(){

        var i = Intent(context,MainActivity::class.java)
        context.startActivity(i)
    }
}