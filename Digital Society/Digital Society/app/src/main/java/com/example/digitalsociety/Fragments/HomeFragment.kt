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
import com.example.digitalsociety.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class HomeFragment : Fragment() {

    lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

          val v = inflater.inflate(R.layout.fragment_home2, container, false)
        var cardView1 = v.findViewById<CardView>(R.id.cardView1)
        var cardView2 = v.findViewById<CardView>(R.id.cardView2)
        var cardView3 = v.findViewById<CardView>(R.id.cardView3)
        var cardView4 = v.findViewById<CardView>(R.id.cardView4)
        var cardView5 = v.findViewById<CardView>(R.id.cardView5)


        cardView1.setOnClickListener {
            var d = Dialog(requireContext())
            d.setContentView(R.layout.addnoticefragmentdialogue)
            d.show()

            var ednoticettitle = d.findViewById<EditText>(R.id.ednoticetitle)
            var ednoticedes = d.findViewById<EditText>(R.id.ednotice)
            var btnaddnotice = d.findViewById<Button>(R.id.btnnotice)

            btnaddnotice.setOnClickListener {


                var eventT = ednoticettitle.text.toString()
                var eventD = ednoticedes.text.toString()

                var database = FirebaseDatabase.getInstance().getReference("Notice")

                var key = FirebaseDatabase.getInstance().getReference("Notice").push().key.toString()

                var hashmap:HashMap<String,String> = HashMap()
                hashmap.put("userid",key)
                hashmap.put("ntitle",eventT)
                hashmap.put("notice",eventD)
                database.child(key).setValue(hashmap).addOnCompleteListener {

                    if (it.isSuccessful){

                        d.dismiss()
                        Toast.makeText(requireContext(), "Notice Added", Toast.LENGTH_SHORT).show()

                    }

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "Notice Failed!", Toast.LENGTH_SHORT).show()
                    d.dismiss()
                }

            }




        }

        cardView2.setOnClickListener {
            var d = Dialog(requireContext())
            d.setContentView(R.layout.addeventfragmentdialogue)
            d.show()

            var edeventttitle = d.findViewById<EditText>(R.id.edaddeventtitle)
            var edeventdes = d.findViewById<EditText>(R.id.edaddeventdes)
            var btnaddevent = d.findViewById<Button>(R.id.btnaddevent)

            btnaddevent.setOnClickListener {


                var eventT = edeventttitle.text.toString()
                var eventD = edeventdes.text.toString()

                var database = FirebaseDatabase.getInstance().getReference("Event")

                var key = FirebaseDatabase.getInstance().getReference("Event").push().key.toString()

                var hashmap:HashMap<String,String> = HashMap()
                hashmap.put("userid",key)
                hashmap.put("etitle",eventT)
                hashmap.put("event",eventD)
                database.child(key).setValue(hashmap).addOnCompleteListener {

                    if (it.isSuccessful){

                        d.dismiss()
                        Toast.makeText(requireContext(), "Event Added", Toast.LENGTH_SHORT).show()

                    }

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "Event Failed!", Toast.LENGTH_SHORT).show()
                    d.dismiss()
                }

            }





        }

        cardView3.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_viewComplaints)

        }
        cardView4.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_viewAllMembers)

        }
        cardView5.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_memberRequest)

        }

        return v
    }


}