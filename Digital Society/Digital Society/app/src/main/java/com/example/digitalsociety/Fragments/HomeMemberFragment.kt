package com.example.digitalsociety.Fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.digitalsociety.Model.ComplaintModel
import com.example.digitalsociety.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class HomeMemberFragment : Fragment() {


lateinit var firebaseAuth: FirebaseAuth

lateinit var databaseReference: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home_member, container, false)

         firebaseAuth = FirebaseAuth.getInstance()
        var cardView1 = v.findViewById<CardView>(R.id.cardView1member)
        var cardView2 = v.findViewById<CardView>(R.id.cardView2member)
        var cardView3 = v.findViewById<CardView>(R.id.cardView3members)
        var cardView4 = v.findViewById<CardView>(R.id.cardView4member)



        cardView1.setOnClickListener {
            findNavController().navigate(R.id.action_homeMemberFragment_to_viewNoticeFragment)

        }

        cardView2.setOnClickListener {
            findNavController().navigate(R.id.action_homeMemberFragment_to_viewEventFragment)

        }

        cardView3.setOnClickListener {
            var d = Dialog(requireContext())
            d.setContentView(R.layout.addcomplaintdialogue)
            d.show()

            var edcomplainttitle = d.findViewById<EditText>(R.id.edcomplainttitle)
            var edcomplaint = d.findViewById<EditText>(R.id.edcomplain)
            var btncomplaint = d.findViewById<Button>(R.id.btncomplaint)

            btncomplaint.setOnClickListener {


                var userid = firebaseAuth.currentUser?.uid.toString()


                var complainttitle = edcomplainttitle.text.toString()
                var dcomplaint = edcomplaint.text.toString()

                databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userid)

                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                      var name = snapshot.child("username").getValue().toString()

                        var database = FirebaseDatabase.getInstance().getReference("Complaint")

                        var key = FirebaseDatabase.getInstance().getReference("Complaint").push().key.toString()

                        var hashmap:HashMap<String,String> = HashMap()
                        hashmap.put("userid",key)
                        hashmap.put("username",name)
                        hashmap.put("ctitle",edcomplainttitle.text.toString())
                        hashmap.put("complaint",edcomplaint.text.toString())
                        database.child(key).setValue(hashmap).addOnCompleteListener {

                            if (it.isSuccessful){

                                d.dismiss()
                                Toast.makeText(requireContext(), "Complaint Added", Toast.LENGTH_SHORT).show()

                            }

                        }.addOnFailureListener {
                            Toast.makeText(requireContext(), "Complaint Failed!", Toast.LENGTH_SHORT).show()
                            d.dismiss()
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })



            }


        }
        cardView4.setOnClickListener {
            findNavController().navigate(R.id.action_homeMemberFragment_to_viewAllMembers3)

        }

        return v
    }


}