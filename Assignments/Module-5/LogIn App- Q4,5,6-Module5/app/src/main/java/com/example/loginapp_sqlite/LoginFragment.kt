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
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_login, container, false)


        var txtCreate = v.findViewById<TextView>(R.id.txtCreateAccount)
        var edemail = v.findViewById<EditText>(R.id.txtloginemail)
        var edpass = v.findViewById<EditText>(R.id.txtpasswordlogin)
        var btnlogin = v.findViewById<Button>(R.id.btnLogin)

        var idd:Int = 0
        var username = ""


        txtCreate.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        btnlogin.setOnClickListener {

         var result = Retro.retrofit.login(edemail.text.toString(),edpass.text.toString())
            result.enqueue(object : Callback<UserModel?> {
                override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
                    Log.d("mylogin","Succesfully data Added")
                    Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()

                    var res = Retro.retrofit.getID(edemail.text.toString())
                    res.enqueue(object : Callback<List<UserModel>?> {
                        override fun onResponse(
                            call: Call<List<UserModel>?>,
                            response: Response<List<UserModel>?>
                        ) {
                            var responseBody = response.body()


                            for(data in responseBody!!){
                                Log.d("@@@@@","$data")
                                idd = data.id
                                username = data.username
                                var i = Intent(context,HomeActivity::class.java)
                                i.putExtra("id",idd)
                                i.putExtra("user",username)
                                startActivity(i)
                            }
                        }

                        override fun onFailure(call: Call<List<UserModel>?>, t: Throwable) {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    })



                }

                override fun onFailure(call: Call<UserModel?>, t: Throwable) {
                    Toast.makeText(context, "Failedd", Toast.LENGTH_SHORT).show()
                }
            })

        }
        return v
    }
}