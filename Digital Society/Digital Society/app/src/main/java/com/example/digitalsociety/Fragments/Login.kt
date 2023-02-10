package com.example.digitalsociety.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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
import com.example.digitalsociety.ChairmanDashboard
import com.example.digitalsociety.LoginRegisterEmployee
import com.example.digitalsociety.MembersDashboard
import com.example.digitalsociety.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class Login : Fragment() {

    lateinit var txtRegister:TextView
    lateinit  var textInputEmail:TextInputEditText
    lateinit  var textInputPassword:TextInputEditText
    lateinit var btnLogin:Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    lateinit var progressBar: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.hide()
        var v = inflater.inflate(R.layout.fragment_login, container, false)



      txtRegister = v.findViewById<TextView>(R.id.txtRegister)
        textInputEmail = v.findViewById<TextInputEditText>(R.id.emailLogin)
        textInputPassword = v.findViewById<TextInputEditText>(R.id.passwordLogin)
        progressBar = v.findViewById(R.id.progressBar4)
        btnLogin = v.findViewById<Button>(R.id.btnLogin)

        firebaseAuth = FirebaseAuth.getInstance()


        butonLClickEvents()



        return v
    }

    @SuppressLint("SuspiciousIndentation")
    private fun butonLClickEvents() {
        txtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }


        btnLogin.setOnClickListener {



         var email = textInputEmail.text.toString()
         var password = textInputPassword.text.toString()


            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {


                var user: FirebaseUser? = firebaseAuth.currentUser
                var userId:String = user!!.uid

                databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userId)

                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var status = snapshot.child("status").getValue()
                        var postion = snapshot.child("postion").getValue()

                        if (postion.toString().lowercase() == "chairman") {

                            var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userId)

                            var hashMap:HashMap<String,Any> = HashMap()
                            hashMap.put("status",true)


                            databaseReference.updateChildren(hashMap as Map<String, Any>).addOnSuccessListener {

                                Toast.makeText(context, "LogIn Succesfull", Toast.LENGTH_SHORT).show()
                                progressBar.visibility = View.GONE
                                startActivity(Intent(context, ChairmanDashboard::class.java))

                            }.addOnFailureListener {
                                Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show()

                            }



                        }else if(postion.toString().lowercase() == "member"){
                            if (status == true){

                                Toast.makeText(context, "LogIn Succesfull", Toast.LENGTH_SHORT).show()
                                progressBar.visibility = View.GONE
                                startActivity(Intent(context, MembersDashboard::class.java))
                            }else{
                                progressBar.visibility = View.GONE
                                Toast.makeText(context, " Your request didn't got approved", Toast.LENGTH_LONG).show()
                                FirebaseAuth.getInstance().signOut()
                                textInputEmail.text?.clear()
                                textInputPassword.text?.clear()
                            }

                            }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

            }.addOnFailureListener {
                Toast.makeText(context, "LogIn Failed!", Toast.LENGTH_SHORT).show()
            }


        }
    }


}