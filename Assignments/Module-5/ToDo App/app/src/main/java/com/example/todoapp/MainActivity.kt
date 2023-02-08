package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.Adapters.FragmentAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tabview = findViewById<TabLayout>(R.id.tablayout)
        var viewpager = findViewById<ViewPager2>(R.id.viewPager)



        viewpager.adapter = FragmentAdapter(supportFragmentManager,lifecycle)

        TabLayoutMediator(tabview,viewpager){tab,position ->
            when(position){
            0-> tab.text = "Upcoming Tasks"
                1-> tab.text = "Completed Tasks"
            }
        }.attach()
    }
}