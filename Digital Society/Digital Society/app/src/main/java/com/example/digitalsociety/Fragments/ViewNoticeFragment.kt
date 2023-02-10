package com.example.digitalsociety.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalsociety.Adapters.EventAdapter
import com.example.digitalsociety.Adapters.NoticeAdapter
import com.example.digitalsociety.Model.EventModel
import com.example.digitalsociety.Model.NoticeModel
import com.example.digitalsociety.R
import com.google.firebase.database.*

class ViewNoticeFragment : Fragment() {

    var noticelist = ArrayList<NoticeModel>()
    lateinit var recycler: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val v =inflater.inflate(R.layout.fragment_view_notice, container, false)


        recycler = v.findViewById(R.id.revViewNotice)

        recycler.layoutManager = LinearLayoutManager(context)

        getallnotice()

        return v
    }

    private fun getallnotice() {
        var databasereference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Notice")

        databasereference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                noticelist.clear()

                for (dataSnapshot: DataSnapshot in snapshot.children){

                    var user = dataSnapshot.getValue(NoticeModel::class.java)

                    Log.e("@@@@@@",""+user)

                    noticelist.add(user!!)

                }

                recycler.adapter  = NoticeAdapter(context!!,noticelist)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    }

