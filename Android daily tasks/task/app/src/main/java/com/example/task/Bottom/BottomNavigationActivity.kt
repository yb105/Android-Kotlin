package com.example.task.Bottom

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.task.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        var bottom = findViewById<BottomNavigationView>(R.id.bottomnavigation)

        loadFragment(HomeFragment())

        bottom.setOnItemSelectedListener {

            when(it.itemId){
             R.id.btn_home -> loadFragment(HomeFragment())
             R.id.btn_setting -> loadFragment(SettingFragment())
             R.id.btn_profile -> loadFragment(ProfileFragment())

            }
            return@setOnItemSelectedListener true
        }

    }

    fun loadFragment(fragment : Fragment){

        var transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.l_bottom,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}