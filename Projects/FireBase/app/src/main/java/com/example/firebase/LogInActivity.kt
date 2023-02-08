package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)



        auth = FirebaseAuth.getInstance()
        var edemail = findViewById<EditText>(R.id.edlemail)
        var edpassword = findViewById<EditText>(R.id.edlpassword)

        var btnl = findViewById<Button>(R.id.btnLogin)


        btnl.setOnClickListener {

            auth.signInWithEmailAndPassword(edemail.text.toString(),edpassword.text.toString()).addOnCompleteListener(this){

                if (it.isSuccessful){

                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()

                    var i   =Intent(this@LogInActivity,Profile_Activity::class.java)
                    finish()
                    startActivity(i)
                }
            }
        }
    }
}