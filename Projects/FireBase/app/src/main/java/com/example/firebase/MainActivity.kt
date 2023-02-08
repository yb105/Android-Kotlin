package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var auth:FirebaseAuth
    lateinit var databaseReference:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = FirebaseAuth.getInstance()
        var edusername = findViewById<EditText>(R.id.edusername)
        var edemail = findViewById<EditText>(R.id.edemail)
        var edpassword = findViewById<EditText>(R.id.edpassword)

        var btnR = findViewById<Button>(R.id.btnRegister)


        btnR.setOnClickListener {

            auth.createUserWithEmailAndPassword(edemail.text.toString(),edpassword.text.toString()).addOnCompleteListener(this){

                if (it.isSuccessful){

                    Log.d("SSS","Success")

                    var user: FirebaseUser? = auth.currentUser
                    var userId:String = user!!.uid

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                    var hashmap:HashMap<String,String> = HashMap()
                    hashmap.put("userid",userId)
                    hashmap.put("username",edusername.text.toString())
                    hashmap.put("profile_pic","")

                    databaseReference.setValue(hashmap).addOnCompleteListener(this){

                        if (it.isSuccessful){

                            Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()

                            var i = Intent(MainActivity@this,LogInActivity::class.java)
                            finish()
                            startActivity(i)
                        }
                    }
                }else{

                    Log.d("SS","Failed")
                }
            }
        }

    }
    }
