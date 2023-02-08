package com.example.q13_module4

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var check = findViewById<CheckBox>(R.id.checkBox)
        var txt = findViewById<TextView>(R.id.txt)
        var im1 = findViewById<ImageView>(R.id.imageView)
       var im2 = findViewById<ImageView>(R.id.imageView2)
        var im3 = findViewById<ImageView>(R.id.imageView3)
       var im4 = findViewById<ImageView>(R.id.imageView4)




        check.setOnCheckedChangeListener{view,ischecked->
            if (ischecked){
                txt.visibility = View.VISIBLE
                im1.visibility =  View.VISIBLE
                im2.visibility =  View.VISIBLE
                im3.visibility =  View.VISIBLE
                im4.visibility =  View.VISIBLE

            }else{

                txt.visibility = View.GONE
                im1.visibility =  View.GONE
                im2.visibility =  View.GONE
                im3.visibility =  View.GONE
                im4.visibility = View.GONE
            }
        }
    }
}