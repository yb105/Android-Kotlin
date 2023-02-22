package com.example.crud

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAdapter(var context:Context, var list:List<DataUser>):RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    class MyViewHolder(item:View):RecyclerView.ViewHolder(item){

        var name = item.findViewById<TextView>(R.id.revName)
        var company = item.findViewById<TextView>(R.id.revcompany)
        var postion = item.findViewById<TextView>(R.id.revPostion)
        var im = item.findViewById<ImageView>(R.id.imDOt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      var v = LayoutInflater.from(parent.context).inflate(R.layout.revlayout,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount()= list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var lis = list[position]

        holder.name.text = lis.name
        holder.company.text = lis.company
        holder.postion.text = lis.postion

        holder.im.setOnClickListener {
            loadDialogue(context,position,it)
        }
    }

    private fun loadDialogue(context: Context, position: Int,view:View) {


       var popup = PopupMenu(context,view)
        popup.inflate(R.menu.menu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->

            when(item.itemId){

                R.id.menuDelete -> {

                    deleteItem(position)
                }

                R.id.menuUpdate -> {

                    updateItem(position)
                }

            }

            true
        })
        popup.show()
    }

    private fun deleteItem(position: Int) {

        var result = Retro.retrofit.delete_user(list[position].id).enqueue(object : Callback<DataUser?> {
            override fun onResponse(call: Call<DataUser?>, response: Response<DataUser?>) {
                Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<DataUser?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun updateItem(position: Int) {


        var d = Dialog(context)
        d.setContentView(R.layout.dlayout)
        d.show()

        var name = d.findViewById<EditText>(R.id.DName)
        var pos = d.findViewById<EditText>(R.id.DPosition)
        var bt = d.findViewById<Button>(R.id.btnUpdate)


        name.setText(list[position].name)
        pos.setText(list[position].postion)



        bt.setOnClickListener {
            var result = Retro.retrofit.update_user(name.text.toString(),pos.text.toString()).enqueue(object : Callback<DataUser?> {
                override fun onResponse(call: Call<DataUser?>, response: Response<DataUser?>) {
                    Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
                    d.dismiss()
                }

                override fun onFailure(call: Call<DataUser?>, t: Throwable) {
                   d.dismiss()
                }
            })
        }





    }
}