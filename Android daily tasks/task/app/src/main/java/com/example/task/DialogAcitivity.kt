package com.example.task

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Build
import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class DialogAcitivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_acitivity)

        var btn = findViewById<Button>(R.id.alertdialog)
        var btnsingle = findViewById<Button>(R.id.alertdialogsingle)
        var btnsinglechoice = findViewById<Button>(R.id.alertdialogsinglechoice)
        var btndatepicker = findViewById<Button>(R.id.btndatepicker)
        var btntimepicker = findViewById<Button>(R.id.timepicker)
        var btncustomdialog = findViewById<Button>(R.id.btncustomdialog)
        var selectchoice = ""

         var calendar = Calendar.getInstance()
        var dd = calendar.get(Calendar.DAY_OF_MONTH)
        var mm = 1 + calendar.get(Calendar.MONTH)
        var year = calendar.get(Calendar.YEAR)

        btndatepicker.setText("$dd - $mm - $year")

        btncustomdialog.setOnClickListener {
            
         var d = Dialog(this@DialogAcitivity)

            d.setContentView(R.layout.custom_dialog)

            var ed = d.findViewById<EditText>(R.id.edcustomdialog)
            var btn = d.findViewById<Button>(R.id.btncustomdialog)

            btn.setOnClickListener {
                Toast.makeText(this, ""+ ed.text.toString(), Toast.LENGTH_SHORT).show()
                d.dismiss()
            }
            d.show()

        }

        btntimepicker.setOnClickListener {
            var t = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                btntimepicker.setText("$i : $i2")

            },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true)
            t.show()
        }

        btndatepicker.setOnClickListener {

            var d = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                var mon = 1 + i2
                btndatepicker.setText("$i3 - $mon - $i")

            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))

            d.show()
        }





        btnsinglechoice.setOnClickListener {

            var builder = AlertDialog.Builder(this)
            builder.setTitle("Selcet subject")
            var subject = arrayOf("c","java","android","python")
            var checkeditem = 0
            builder.setSingleChoiceItems(subject,checkeditem) { dialog, which ->
                when (which) {

                    0 -> selectchoice = "c"
                    1 -> selectchoice = "java"
                    2 -> selectchoice = "android"
                    3 -> selectchoice = "python"
                }
            }
                builder.setPositiveButton("ok", DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this, ""+selectchoice, Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                })

                    builder.setNegativeButton("cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.cancel()
                        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show()
                    })
            var dialog = builder.create()
            dialog.show()


        }

      btnsingle.setOnClickListener {

          var builder = AlertDialog.Builder(this)
          builder.setTitle("Select Any one")

          var subject =  arrayOf("C","Java","Python","android")
          builder.setItems(subject){dialog,which ->

              when(which){

                  0 -> Toast.makeText(this, "C", Toast.LENGTH_SHORT).show()
                  1 -> Toast.makeText(this, "java", Toast.LENGTH_SHORT).show()
                  2 -> Toast.makeText(this, "python", Toast.LENGTH_SHORT).show()
                  3 -> Toast.makeText(this, "android", Toast.LENGTH_SHORT).show()
              }

          }

          var dialog = builder.create()
          dialog.show()
      }
    }

    override fun onBackPressed() {
       // super.onBackPressed() when this is applied acttvity gets destroyed
        var alert = AlertDialog.Builder(this@DialogAcitivity)
            .setTitle("Alert")
            .setMessage("Are you sure you want to exist?")
            .setPositiveButton("yes", DialogInterface.OnClickListener { dialogInterface, i ->
                finish()
            })
            .setNegativeButton("no", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
                Toast.makeText(this@DialogAcitivity, "No", Toast.LENGTH_SHORT).show()
            })

            .setNeutralButton("cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
            .setCancelable(false)
            .show()
    }
}