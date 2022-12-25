package com.example.navigation_drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity()
{
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mytoolbar = findViewById<Toolbar>(R.id.my_toolbar)

        setSupportActionBar(mytoolbar)

        var navView = findViewById<NavigationView>(R.id.nav_view)
        var mydrawerLayout = findViewById<DrawerLayout>(R.id.nav_drawer_layout)

        var navController = findNavController(R.id.nav_host_fragment_main)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,R.id.settingFragment,R.id.contactsFragment),mydrawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)

        navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean
    {
        var navController = findNavController(R.id.nav_host_fragment_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}