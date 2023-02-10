package com.example.digitalsociety.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.digitalsociety.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Register : Fragment() {

    lateinit var edemail:TextInputEditText
    lateinit var edusername:TextInputEditText
    lateinit var edpassword:TextInputEditText
    lateinit var edphone:TextInputEditText
    lateinit var edehouse:TextInputEditText
    lateinit var edpostion:TextInputEditText
    lateinit var txtlogin:TextView
    lateinit var btnRegister: Button
    lateinit var progressbar:ProgressBar


    lateinit var auth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.hide()
        var v = inflater.inflate(R.layout.fragment_register, container, false)

        auth = FirebaseAuth.getInstance()

         txtlogin  = v.findViewById(R.id.txtlogin)
        edemail = v.findViewById(R.id.editextEmail)
        edusername = v.findViewById(R.id.editTextUser)
        edpassword = v.findViewById(R.id.editTextUserPassword)
        edphone = v.findViewById(R.id.editTextUserPhone)
        edehouse = v.findViewById(R.id.editTextUserAddress)
        edpostion = v.findViewById(R.id.editTextUserJobPosition)
        btnRegister = v.findViewById(R.id.btnRegister)
        progressbar = v.findViewById(R.id.progressBar2)


        txtlogin.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        }

        btnRegister.setOnClickListener {
            progressbar.visibility = View.VISIBLE

      auth.createUserWithEmailAndPassword(edemail.text.toString(),edpassword.text.toString()).addOnCompleteListener {

          if (it.isSuccessful){

              Log.d("SSS","Success")

              var user: FirebaseUser? = auth.currentUser
              var userId:String = user!!.uid

              databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userId)

              var hashmap:HashMap<String,Any> = HashMap()
              hashmap.put("userid",userId)
              hashmap.put("username",edusername.text.toString())
              hashmap.put("phone",edphone.text.toString())
              hashmap.put("house",edehouse.text.toString())
              hashmap.put("postion",edpostion.text.toString())
              hashmap.put("status",false)
              hashmap.put("profile_pic","")

              databaseReference.setValue(hashmap).addOnCompleteListener{

                  if (it.isSuccessful){

                      Toast.makeText(context, "Successfully Registered", Toast.LENGTH_SHORT).show()
                      progressbar.visibility = View.GONE

                      findNavController().navigate(R.id.action_register_to_login)

                  }
              }
          }else{
              progressbar.visibility = View.GONE

              Toast.makeText(context, "Registered Failed!", Toast.LENGTH_SHORT).show()
          }
      }



        }
        return v
    }

}