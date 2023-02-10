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
import com.example.digitalsociety.Adapters.ViewComplaintAdapter
import com.example.digitalsociety.Model.ComplaintModel
import com.example.digitalsociety.Model.EventModel
import com.example.digitalsociety.R
import com.google.firebase.database.*


class ViewEventFragment : Fragment() {

    var eventList = ArrayList<EventModel>()
    lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_view_event, container, false)


        recycler = v.findViewById(R.id.revViewEvent)

        recycler.layoutManager = LinearLayoutManager(context)

        getallevents()
        return v
    }

    private fun getallevents() {
        var databasereference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Event")

        databasereference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()

                for (dataSnapshot: DataSnapshot in snapshot.children){

                    var user = dataSnapshot.getValue(EventModel::class.java)

                    Log.e("@@@@@@",""+user)

                    eventList.add(user!!)

                }

                recycler.adapter  = EventAdapter(context!!,eventList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    }


