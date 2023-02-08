package com.example.loginapp_sqlite

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegisterFragment : Fragment() {


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val v = inflater.inflate(R.layout.fragment_register, container, false)

        val txtLogin = v.findViewById<TextView>(R.id.txtlogin)
        var edtusername =  v.findViewById<EditText>(R.id.txtuser)
        var edtemail =  v.findViewById<EditText>(R.id.txtemail)
        var edtpassword =  v.findViewById<EditText>(R.id.txtPass)
        val btnregister = v.findViewById<Button>(R.id.btnRegister)


        txtLogin.setOnClickListener {
             findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }



        btnregister.setOnClickListener {

               var result = Retro.retrofit.register(edtusername.text.toString(),edtemail.text.toString(),edtpassword.text.toString())
                     result.enqueue(object : Callback<UserModel?> {
                         override fun onResponse(
                             call: Call<UserModel?>,
                             response: Response<UserModel?>) {

                             if (response.isSuccessful){

                                 Log.d("mydata","Succesfully data Added")
                                 Toast.makeText(context, "Regiestered", Toast.LENGTH_SHORT).show()
                                 findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                             }

                         }

                         override fun onFailure(call: Call<UserModel?>, t: Throwable) {
                             Log.d("myerror","failed")
                         }
                     })

        }




        return v
    }


}