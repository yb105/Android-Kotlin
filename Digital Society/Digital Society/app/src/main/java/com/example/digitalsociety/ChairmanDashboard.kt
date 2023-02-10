package com.example.digitalsociety

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import de.hdodenhof.circleimageview.CircleImageView

class ChairmanDashboard : AppCompatActivity() {
 lateinit var navController: NavController
 lateinit var databaseReference: DatabaseReference
 lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chairman_dashboard)

        supportActionBar?.hide()

        var hostFragment = supportFragmentManager.findFragmentById(R.id.fragmentchairmandashboard) as NavHostFragment
          navController = hostFragment.navController

        firebaseAuth = FirebaseAuth.getInstance()
        var userId =firebaseAuth.currentUser!!.uid

        var imlogout = findViewById<CircleImageView>(R.id.profile_Logout)
        var improfile = findViewById<CircleImageView>(R.id.profile_ChairmanDashboard)
        var name = findViewById<TextView>(R.id.txtchairmanname)
        var posti = findViewById<TextView>(R.id.txtchairmanpostion)

        databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userId)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               var url = snapshot.child("profile_pic").getValue()
                var nam = snapshot.child("username").getValue()
                var pos = snapshot.child("postion").getValue()

                name.text = nam.toString()
                posti.text = pos.toString()
                Glide.with(this@ChairmanDashboard).load(url).placeholder(R.drawable.chairman).into(improfile)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })






        imlogout.setOnClickListener{

            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "LogOut Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@ChairmanDashboard,LoginRegisterEmployee::class.java))
        }

        improfile.setOnClickListener {
        startActivity(Intent(this@ChairmanDashboard,ProfileActivity::class.java))
        }




    }


}