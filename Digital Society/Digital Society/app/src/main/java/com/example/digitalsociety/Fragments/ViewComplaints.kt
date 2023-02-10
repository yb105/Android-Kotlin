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
import com.example.digitalsociety.Adapters.ViewComplaintAdapter
import com.example.digitalsociety.Model.ComplaintModel
import com.example.digitalsociety.Model.UserModel
import com.example.digitalsociety.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class ViewComplaints : Fragment() {


    var userList = ArrayList<ComplaintModel>()
    lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val v = inflater.inflate(R.layout.fragment_view_complaints, container, false)

        recycler = v.findViewById(R.id.revViewComplaints)

        recycler.layoutManager = LinearLayoutManager(context)

        getallcomplaint()
        return v
    }

    private fun getallcomplaint() {

        var databasereference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Complaint")

        databasereference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()

                for (dataSnapshot: DataSnapshot in snapshot.children){

                    var user = dataSnapshot.getValue(ComplaintModel::class.java)

                    Log.e("@@@@@@",""+user)

                        userList.add(user!!)

                }

                recycler.adapter  = ViewComplaintAdapter(context!!,userList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}