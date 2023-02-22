package com.example.users_task1

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.users_task1.databinding.ActivityUpdateBinding
import com.example.users_task1.databinding.ActivityUserBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class UpdateActivity : AppCompatActivity() {
    lateinit private var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var id = intent.getIntExtra("updateId",0)
        var postion = intent.getIntExtra("position",0)
        var name = intent.getStringExtra("updateName")
        var email = intent.getStringExtra("updateEmail")
        var age = intent.getStringExtra("updateAge")
        var contact = intent.getStringExtra("updateContact")
        var gendery = intent.getStringExtra("updateGender")
        var dob = intent.getStringExtra("updateDob")
        var gender =gendery


        binding.edUpdateName.setText(name)
        binding.edUpdateEmail.setText(email)
        binding.txtUpdateDob.setText(dob)
        binding.edUpdateContact.setText(contact)
        binding.edUpdateAge.setText(age)

        binding.txtUpdateDob.setOnClickListener {
            var calendar = Calendar.getInstance()

            var d = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                var mon = 1 + i2
                binding.txtUpdateDob.setText("$i3 - $mon - $i")

            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))

            d.show()

        }

        binding.radioUpdateGroup.setOnCheckedChangeListener{group,checkedId ->
            if (checkedId == R.id.radioUpdateMale){
                gender = "male"
            }else if(checkedId == R.id.radioUpdateFemale){
                gender = "female"
            }
        }



        binding.btnUpdateUser.setOnClickListener {



            var name1 = binding.edUpdateName.text.toString()
            var email1 = binding.edUpdateEmail.text.toString()
            var dob1 = binding.txtUpdateDob.text.toString()
            var contact1 = binding.edUpdateContact.text.toString()
            var age1 = binding.edUpdateAge.text.toString()

            GlobalScope.launch {
                var dataBase = DataBase.getDatabase(this@UpdateActivity).taskdao().updateData(UserData(id,name1,email1,age1,dob1,contact1,gender))
            }



            var intent = Intent(this@UpdateActivity,MainActivity::class.java)



            startActivity(intent)


        }
    }
}