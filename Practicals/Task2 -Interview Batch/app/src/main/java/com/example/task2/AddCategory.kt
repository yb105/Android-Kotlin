package com.example.task2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.task2.databinding.ActivityAddCategoryBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddCategory : AppCompatActivity() {

    private lateinit var binding:ActivityAddCategoryBinding
    private var filepath: Uri? = null
    lateinit var database:TaskDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
          database = TaskDataBase.getDatabase(this)

        val selectImageIntent = registerForActivityResult(ActivityResultContracts.GetContent())
        { uri ->
            filepath = uri
            binding.imageview.setImageURI(uri)
        }

        binding.imageview.setOnClickListener {

            selectImageIntent.launch("image/*")
        }


        binding.btnaddcategory.setOnClickListener {

            var name = binding.edcategoryname.text.toString()

            GlobalScope.launch {
                database.taskdao().insertData(CategoryModel(0,name,filepath.toString()))
                database.taskdao().insertSpinnerData(SpinnerModel(0,name))
            }
            Toast.makeText(this@AddCategory, "Category Added", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@AddCategory,MainActivity::class.java))


        }
    }
}