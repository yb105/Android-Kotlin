package com.example.loginapp_sqlite

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter(var context: Context,var listData:List<ProjectModel>): RecyclerView.Adapter<MyAdapter.MYViewHolder>() {
    class MYViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var name = itemView.findViewById<TextView>(R.id.txtname)
        var detail = itemView.findViewById<TextView>(R.id.txtdetail)
        var threedot = itemView.findViewById<TextView>(R.id.txtEllipsis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.revlayout,parent,false)
        return MYViewHolder(v)
    }
    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        var lis = listData[position]
        holder.name.text = lis.project_name
        holder.detail.text = lis.project_des

        holder.threedot.setOnClickListener{

                  updateanddelete(lis.project_name,lis.id)
        }

    }
    override fun getItemCount(): Int {
        return listData.size
    }



    @SuppressLint("SuspiciousIndentation")
    fun updateanddelete(name:String, id:Int){

        var dialog = Dialog(context)
        dialog.setContentView(R.layout.dot)
        dialog.show()


        var txtedit = dialog.findViewById<TextView>(R.id.txtedit)
        var txtdelete = dialog.findViewById<TextView>(R.id.txtdelete)


        txtedit.setOnClickListener {

            var d = Dialog(context)
            d.setContentView(R.layout.updateprojectdialog)
            d.show()



            var edT = d.findViewById<EditText>(R.id.edTitle)
            var edD = d.findViewById<EditText>(R.id.edDescription)
            var btnAdd = d.findViewById<Button>(R.id.btnAddSticky)

              edT.setText(name)

            btnAdd.setOnClickListener{

                var result = Retro.retrofit.update_student(edT.text.toString(),edD.text.toString())
                result.enqueue(object : Callback<ProjectModel?> {
                    override fun onResponse(
                        call: Call<ProjectModel?>,
                        response: Response<ProjectModel?>
                    ) {
                        Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show()
                        d.dismiss()
                        dialog.dismiss()
                        var i = Intent(context, HomeActivity::class.java)
                        context.startActivity(i)
                    }

                    override fun onFailure(call: Call<ProjectModel?>, t: Throwable) {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                        d.dismiss()
                        dialog.dismiss()
                        var i = Intent(context, HomeActivity::class.java)
                        context.startActivity(i)
                    }
                })

            }
        }

        txtdelete.setOnClickListener {
             var resu = Retro.retrofit.delete_project(id)
            resu.enqueue(object : Callback<ProjectModel?> {
                override fun onResponse(
                    call: Call<ProjectModel?>,
                    response: Response<ProjectModel?>
                ) {
                    Toast.makeText(context, "Deleted Succesfully", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    var i = Intent(context, HomeActivity::class.java)
                    context.startActivity(i)
                }

                override fun onFailure(call: Call<ProjectModel?>, t: Throwable) {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    var i = Intent(context, HomeActivity::class.java)
                    context.startActivity(i)
                }
            })
        }


    }


}