package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class DashBoard : AppCompatActivity() {

    var userList = ArrayList<UserModel>()
    lateinit var recycler:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)


        recycler = findViewById<RecyclerView>(R.id.revView)

        recycler.layoutManager = LinearLayoutManager(this)



       // userList.add(UserModel("Hello","https://www.shutterstock.com/image-photo/surreal-image-african-elephant-wearing-260nw-1365289022.jpg"))
getAllUsers()

    }

    fun getAllUsers(){

        var firebase:FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        var databasereference:DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databasereference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()

                for (dataSnapshot:DataSnapshot in snapshot.children){

                    var user = dataSnapshot.getValue(UserModel::class.java)

                    Log.e("@@@",""+user)

                    if(!user!!.userid.equals(firebase.uid)){

                        userList.add(user)
                    }
                }

                recycler.adapter  = MyAdapter(this@DashBoard,userList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}