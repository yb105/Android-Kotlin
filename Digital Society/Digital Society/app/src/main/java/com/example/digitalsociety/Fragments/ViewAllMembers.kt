package com.example.digitalsociety.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalsociety.Adapters.AllMemberAdapter
import com.example.digitalsociety.Model.UserModel
import com.example.digitalsociety.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ViewAllMembers : Fragment() {

    var userList = ArrayList<UserModel>()
    lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val v = inflater.inflate(R.layout.fragment_view_all_members, container, false)

        recycler = v.findViewById(R.id.revViewAllMembers)

        recycler.layoutManager = LinearLayoutManager(context)

        getalluser()
        return v
    }

    private fun getalluser() {
        var firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        var databasereference: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserData")

        databasereference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()

                for (dataSnapshot: DataSnapshot in snapshot.children){

                    var user = dataSnapshot.getValue(UserModel::class.java)

                    Log.e("@@@",""+user)

                    if(!user!!.userid.equals(firebase.uid)){

                        userList.add(user)
                    }
                }

                recycler.adapter  = AllMemberAdapter(context!!,userList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}