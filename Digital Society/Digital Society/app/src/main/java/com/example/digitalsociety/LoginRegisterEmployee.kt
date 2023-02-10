package com.example.digitalsociety

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class LoginRegisterEmployee : AppCompatActivity() {

    lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register_employee)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        var navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController



    }
}