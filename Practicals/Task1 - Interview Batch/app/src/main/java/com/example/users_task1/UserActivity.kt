package com.example.users_task1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.users_task1.databinding.ActivityUserBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class UserActivity : AppCompatActivity() {
    lateinit private var binding:ActivityUserBinding
    lateinit var dataBase:DataBase

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase = DataBase.getDatabase(this)

        var gender = ""


        binding.txtDob.setOnClickListener {
            var calendar = Calendar.getInstance()

            var d = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                var mon = 1 + i2
                binding.txtDob.setText("$i3 - $mon - $i")

            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))

            d.show()

        }

        binding.radioGroup.setOnCheckedChangeListener{group,checkedId ->
            if (checkedId == R.id.radioMale){
                gender = "male"
            }else if(checkedId == R.id.radioFemale){
                gender = "female"
            }
        }
        binding.btnUser.setOnClickListener {



            var name = binding.edName.text.toString()
            var email = binding.edEmail.text.toString()
            var dob = binding.txtDob.text.toString()
            var contact = binding.edContact.text.toString()
            var age = binding.edAge.text.toString()

            GlobalScope.launch {
                var userData = UserData(0,name,email,age,dob,contact,gender)
                dataBase.taskdao().insertData(userData)
            }

          var intent = Intent(this@UserActivity,MainActivity::class.java)

            startActivity(intent)


        }


    }



}