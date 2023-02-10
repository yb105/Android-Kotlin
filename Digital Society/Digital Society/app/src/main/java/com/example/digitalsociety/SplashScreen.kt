package com.example.digitalsociety

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.example.digitalsociety.ChooseLogInType

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {


     lateinit var auth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

       auth = FirebaseAuth.getInstance()

        var splashScreenImage = findViewById<ImageView>(R.id.imageView)
        var slideAnimation = AnimationUtils.loadAnimation(this,R.anim.slideanimation)
        splashScreenImage.startAnimation(slideAnimation)

        Handler().postDelayed({

            if(auth.currentUser != null)
            {
                var user: FirebaseUser? = auth.currentUser
                var userId:String = user!!.uid

                databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userId)

                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var postion = snapshot.child("postion").getValue()

                        if (postion.toString().lowercase() == "chairman"){
                            startActivity(Intent(this@SplashScreen, ChairmanDashboard::class.java))
                        }else if (postion.toString().lowercase() == "member"){
                            startActivity(Intent(this@SplashScreen, MembersDashboard::class.java))
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
            }else {
                val intent = Intent(this@SplashScreen, ChooseLogInType::class.java)
                startActivity(intent)
                finish()
            }


        }, 2500)




       }

    }
