package com.example.digitalsociety.Adapters

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalsociety.Model.ComplaintModel
import com.example.digitalsociety.R

class ViewComplaintAdapter(var context: Context,var arrayList: ArrayList<ComplaintModel>):RecyclerView.Adapter<ViewComplaintAdapter.MyViewHolder>() {
    class MyViewHolder(itemVIew:View):RecyclerView.ViewHolder(itemVIew) {

        var title = itemVIew.findViewById<TextView>(R.id.txtviewcomplainttitle)
        var cardview = itemVIew.findViewById<CardView>(R.id.viewcomplaintCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.revviewcomplaint,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = arrayList[position].ctitle

        holder.cardview.setOnClickListener {
            opendialogue(position)
        }
    }

    private fun opendialogue(p:Int) {
        var d = Dialog(context)
        d.setContentView(R.layout.viewcomplaintdialogue)
        d.show()

        var title = d.findViewById<TextView>(R.id.txtviewComplaintDialoguetitle)
        var username = d.findViewById<TextView>(R.id.txtviewcomplaintDialogueusername)
        var description = d.findViewById<TextView>(R.id.txtrequestDialogueHouse)


        title.text = arrayList[p].ctitle
        username.text = arrayList[p].username
        description.text = arrayList[p].complaint

    }
}