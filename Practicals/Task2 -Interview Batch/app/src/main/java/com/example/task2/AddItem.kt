package com.example.task2

import android.R
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.task2.databinding.ActivityAddItemBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddItem : AppCompatActivity() {

    private lateinit var binding:ActivityAddItemBinding
    lateinit var database:TaskDataBase
    var listSpinner:List<String> = ArrayList()
    private var filepath: Uri? = null
    var arrayC = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var category = ""





               database = TaskDataBase.getDatabase(this)
               database.taskdao().getallSpinnerData().observe(this, {

                   listSpinner = it


                   val adapter: ArrayAdapter<String> =  ArrayAdapter(this,R.layout.simple_spinner_item, listSpinner)
                   adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                   binding.spinnerAddItem.adapter = adapter

                   binding.spinnerAddItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                       override fun onItemSelected(
                           parent: AdapterView<*>?,
                           view: View?,
                           position: Int,
                           id: Long
                       ) {
                           category = listSpinner[position]
                           Log.d("@@@","$category")
                       }

                       override fun onNothingSelected(parent: AdapterView<*>?) {
                           TODO("Not yet implemented")
                       }

                   }
               })



        val selectImageIntent = registerForActivityResult(ActivityResultContracts.GetContent())
        { uri ->
            filepath = uri
            binding.addItemimageview.setImageURI(uri)
        }

        binding.addItemimageview.setOnClickListener {

            selectImageIntent.launch("image/*")
        }


        binding.btnAddItem.setOnClickListener {

            var name = binding.edaddItemName.text.toString()
            var des = binding.edaddItemDescription.text.toString()
            var mrp = binding.edaddItemMRP.text.toString()
            var dicount = binding.edaddItemDiscount.text.toString()

            GlobalScope.launch {

                database.taskdao().insertItemData(ItemModel(0,name,des,mrp.toInt(),dicount.toInt(),category,filepath.toString()))
            }
            Toast.makeText(this@AddItem, "Item Added", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@AddItem,MainActivity::class.java))
        }




    }
}