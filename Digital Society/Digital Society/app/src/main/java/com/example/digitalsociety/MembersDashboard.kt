package com.example.digitalsociety

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import de.hdodenhof.circleimageview.CircleImageView

class MembersDashboard : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members_dashboard)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()
        var userId =firebaseAuth.currentUser!!.uid

        var imlogout = findViewById<CircleImageView>(R.id.profile_member_Logout)
        var improfile = findViewById<CircleImageView>(R.id.profile_memberDashboard)
        var name = findViewById<TextView>(R.id.txtmembername)
        var posti = findViewById<TextView>(R.id.txtchairmanpostion)

        databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userId)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var url = snapshot.child("profile_pic").getValue()
                var nam = snapshot.child("username").getValue()
                var pos = snapshot.child("postion").getValue()

                name.text = nam.toString()
                posti.text = pos.toString()
                Glide.with(this@MembersDashboard).load(url).placeholder(R.drawable.chairman).into(improfile)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })






        imlogout.setOnClickListener{

            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "LogOut Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@MembersDashboard,LoginRegisterEmployee::class.java))
        }

        improfile.setOnClickListener {
            startActivity(Intent(this@MembersDashboard,ProfileActivity::class.java))
        }




    }

}



