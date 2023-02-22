package com.example.users_task1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserAdapter(var context:Context, var arrayList: ArrayList<UserData>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    class MyViewHolder(item: View):RecyclerView.ViewHolder(item) {

        var name = item.findViewById<TextView>(R.id.revName)
        var age = item.findViewById<TextView>(R.id.revAge)
        var im = item.findViewById<ImageView>(R.id.imDOt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var v = LayoutInflater.from(parent.context).inflate(R.layout.listdata,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.age.text = arrayList[position].age
        holder.name.text = arrayList[position].name

        holder.im.setOnClickListener {
            loadDialogue(context,it,position)
        }

    }

    private fun loadDialogue(context: Context, view: View, position: Int) {
        var popup = PopupMenu(context,view)
        popup.inflate(R.menu.dialogue)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->

            when(item.itemId){

                R.id.deleteMenu -> {

                    deleteItem(position)
                }

                R.id.updateMenu -> {

                    updateItem(position)
                }

                R.id.cancelMenu -> {

                   popup.dismiss()

                }
            }

            true
        })
        popup.show()
    }

    private fun updateItem(position: Int) {
             var arr = arrayList[position]
        var i = Intent(context,UpdateActivity::class.java)
        i.putExtra("updateId",arr.id)
        i.putExtra("updateName",arr.name)
        i.putExtra("updateEmail",arr.email)
        i.putExtra("updateAge",arr.age)
        i.putExtra("updateContact",arr.contact)
        i.putExtra("updateGender",arr.gender)
        i.putExtra("updateDob",arr.dob)
        i.putExtra("position",position)
        context.startActivity(i)

    }

    private fun deleteItem(position: Int) {

        var arr = arrayList[position]
      var database = DataBase.getDatabase(context)
        GlobalScope.launch {
            database.taskdao().deleteData(UserData(arr.id,arr.name,arr.email,arr.age,arr.dob,arr.contact,arr.gender))
        }
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()

    }

}
