package com.example.task.bottom2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.task.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Bottom_exmaple_2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_exmaple2)



        var navController = this.findNavController(R.id.main_host_fragment)

        var navView : BottomNavigationView= findViewById(R.id.bottomnavigation2)

        navView.setupWithNavController(navController)




    }
}