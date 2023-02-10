package com.example.digitalsociety.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalsociety.Adapters.RequestAdapters
import com.example.digitalsociety.Model.UserModel
import com.example.digitalsociety.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class MemberRequest : Fragment() {

    var userList = ArrayList<UserModel>()
    var ulist =  ArrayList<UserModel>()
    lateinit var recycler: RecyclerView
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val v = inflater.inflate(R.layout.fragment_member_request, container, false)

        recycler = v.findViewById(R.id.requestRecyclerView)

        recycler.layoutManager = LinearLayoutManager(context)

        getallRequest()


        return v
    }

    private fun getallRequest() {
        var firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        var databasereference: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserData")

        databasereference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()

                for (dataSnapshot: DataSnapshot in snapshot.children){

                    var user = dataSnapshot.getValue(UserModel::class.java)

                    if(!user!!.userid.equals(firebase.uid)){

                        userList.add(user)

                       ulist = userList.filter {
                            it.status == false } as ArrayList<UserModel>
                    }
                }


                recycler.adapter  = RequestAdapters(context!!,ulist)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}