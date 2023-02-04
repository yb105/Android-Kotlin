package com.example.taskmanagement

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.ClipData.Item
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.nio.file.attribute.AclEntry.Builder
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter(var context: Context,var arrayList: ArrayList<MyModel>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(item: View):RecyclerView.ViewHolder(item) {
       var name = item.findViewById<TextView>(R.id.txtname)
        var des = item.findViewById<TextView>(R.id.txtdes)
        var dAndt = item.findViewById<TextView>(R.id.txtdate_and_time)
        var btnedit = item.findViewById<Button>(R.id.btnEdit)
        var btndelete = item.findViewById<Button>(R.id.btnDelete)
        var card = item.findViewById<CardView>(R.id.cardColor)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var v = LayoutInflater.from(parent.context).inflate(R.layout.listviewlayout,parent,false)
        return MyViewHolder(v)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       var data = arrayList[position]
        holder.name.text = data.name
        holder.des.text = data.des
        holder.dAndt.text = data.timeanddate

       when(arrayList[position].priority){

           "High" -> holder.card.setCardBackgroundColor(ContextCompat.getColor(context,R.color.red))
           "Average" -> holder.card.setCardBackgroundColor(ContextCompat.getColor(context,R.color.blue))
           "Low" -> holder.card.setCardBackgroundColor(ContextCompat.getColor(context,R.color.green))
       }


        holder.btndelete.setOnClickListener {

            deleteDialog(MyModel(data.id,data.name,data.des,data.priority,data.timeanddate))


        }


        holder.btnedit.setOnClickListener {

            updateData(MyModel(data.id,data.name,data.des,data.priority,data.timeanddate))
        }








    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateData(myModel: MyModel) {
        val d = Dialog(context)
        var priority = arrayOf("High", "Average", "Low")
        d.setContentView(R.layout.add_data_dialog)
        d.show()

        val edname = d.findViewById<EditText>(R.id.edName)
        val edDes = d.findViewById<EditText>(R.id.edDes)
        val btnadd = d.findViewById<Button>(R.id.btnaddtask)
        val spinner = d.findViewById<Spinner>(R.id.spinner)
        val txtdate = d.findViewById<TextView>(R.id.txtdate)
        val txttime = d.findViewById<TextView>(R.id.txttime)

        var prio = ""

        if (spinner != null) {
            val adapter = ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                priority
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    when (position) {

                        0 -> prio = "High"
                        1 -> prio = "Average"
                        2 -> prio = "Low"
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }


            var calendar = Calendar.getInstance()
            var tim:String
            var dat:String
            var dateandtime =""




            txttime.setOnClickListener {

                var t = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                    tim = "$i : $i2"
                    txttime.setText(tim)
                    dateandtime = tim

                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true)
                t.show()
            }

            txtdate.setOnClickListener {

                var d = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    var mon = 1 + i2
                    dat = "$i3 - $mon - $i"
                    txtdate.setText(dat)
                    dateandtime = dat + dateandtime


                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))

                d.show()
            }



            btnadd.setOnClickListener {


                var databasehandler = DataBaseHandler(context)

                if (edname.text.toString().isEmpty() && edDes.text.toString().length>0){
                    databasehandler.updateData(MyModel(myModel.id,myModel.name,edDes.text.toString(),prio, timeanddate = dateandtime))
                }else if (edDes.text.toString().isEmpty() && edname.text.toString().length>0){
                    databasehandler.updateData(MyModel(myModel.id,edname.text.toString(),myModel.des,prio, timeanddate = dateandtime))
                }
                else{
                    databasehandler.updateData(MyModel(myModel.id,myModel.name,myModel.des,prio,dateandtime))
                }

                d.dismiss()

                var i = Intent(context, MainActivity::class.java)
                context.startActivity(i)


            }
        }

    }

    private fun deleteDialog(myModel: MyModel) {
        var alert = AlertDialog.Builder(context)
        alert.setTitle("Are You Sure You Want to Delete This Task?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->

            var db = DataBaseHandler(context)
            db.deleteData(MyModel(myModel.id,myModel.name,myModel.des,myModel.priority,myModel.timeanddate))
            loadIntent()

        })

        alert.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

        })

        alert.show()

    }

    override fun getItemCount() = arrayList.size

    fun loadIntent(){

        var i = Intent(context,MainActivity::class.java)
        context.startActivity(i)
    }
}