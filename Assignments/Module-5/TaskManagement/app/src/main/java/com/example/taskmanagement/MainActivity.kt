package com.example.taskmanagement

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var priority = arrayOf("High","Average","Low")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.btnfab)
        var arrayList:ArrayList<MyModel> = ArrayList()

        val rev = findViewById<RecyclerView>(R.id.revView)
        rev.layoutManager = LinearLayoutManager(this)

        var db = DataBaseHandler(this)
        arrayList = db.getTaskData()

        arrayList.sortBy { it.timeanddate }
        rev.adapter = MyAdapter(this,arrayList)




        fab.setOnClickListener {

            dialogFunction()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun dialogFunction() {
        val d = Dialog(this)
        d.setContentView(R.layout.add_data_dialog)
        d.show()

        val edname = d.findViewById<EditText>(R.id.edName)
        val edDes = d.findViewById<EditText>(R.id.edDes)
        val btnadd = d.findViewById<Button>(R.id.btnaddtask)
      val txtdate = d.findViewById<TextView>(R.id.txtdate)
        val txttime = d.findViewById<TextView>(R.id.txttime)
        val spinner = d.findViewById<Spinner>(R.id.spinner)

        var prio = ""

        if (spinner != null) {
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, priority)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    when(position){

                        0 ->  prio = "High"
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

                var t = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                tim = "$i : $i2"
                    txttime.setText(tim)
                    dateandtime = tim

                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true)
                t.show()
            }

            txtdate.setOnClickListener {

                var d = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    var mon = 1 + i2
                    dat = "$i3 - $mon - $i"
                    txtdate.setText(dat)
                    dateandtime = dat +" "+ dateandtime


                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))

                d.show()
            }


            btnadd.setOnClickListener {

                var databasehandler = DataBaseHandler(this)
                var result = databasehandler.addTask(MyModel(0,edname.text.toString(),edDes.text.toString(),prio, timeanddate = dateandtime))
                if (result>0){
                    Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show()
                }
                d.dismiss()

                var i = Intent(this,MainActivity::class.java)
                startActivity(i)



            }


        }
    }
}