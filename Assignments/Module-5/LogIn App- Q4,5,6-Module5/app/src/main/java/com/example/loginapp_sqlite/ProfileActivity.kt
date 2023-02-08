package com.example.loginapp_sqlite

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
     var uri :String = ""
    lateinit var txtprofilename:TextView
    lateinit var txtBirthday:TextView
    lateinit var txtmobilenumber:TextView

    var user:String = ""
    var id:Int = 0


    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri = it.toString()
        imageView.setImageURI(it)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        var btneditProfile = findViewById<Button>(R.id.btneditprofile)
        var txtchange = findViewById<TextView>(R.id.txtchange)
        imageView = findViewById(R.id.imageView)
         txtprofilename = findViewById<TextView>(R.id.txtprofilename)
         txtBirthday = findViewById<TextView>(R.id.txtBirthday)
        txtmobilenumber = findViewById<TextView>(R.id.txtmobilenumber)


        id = intent.getIntExtra("idd",0)
       user = intent.getStringExtra("userr").toString()
        txtprofilename.text = user
        loaddata()






        btneditProfile.setOnClickListener {

                editprofile()
        }

        txtchange.setOnClickListener {
            contract.launch("image/*")
        }
    }

    fun editprofile(){

        var d = Dialog(this)
        d.setContentView(R.layout.editprofilelayout)
        d.show()

        var eddob = d.findViewById<EditText>(R.id.eddob)
        var edmob = d.findViewById<EditText>(R.id.edmob)
        var btnprofile = d.findViewById<Button>(R.id.btnAddedit)





        btnprofile.setOnClickListener {

            var result = Retro.retrofit.profile(id,user,eddob.text.toString(),edmob.text.toString(),uri)
            result.enqueue(object : Callback<ProfileModel?> {
                override fun onResponse(
                    call: Call<ProfileModel?>,
                    response: Response<ProfileModel?>
                ) {
                    Toast.makeText(applicationContext, "Profile Edited", Toast.LENGTH_SHORT).show()
                    d.dismiss()

                    txtprofilename.text = user
                    txtmobilenumber.text =edmob.text.toString()
                    txtBirthday.text =  eddob.text.toString()
                    imageView.setImageURI(uri.toUri())


                }

                override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                    d.dismiss()

                }
            })
        }
    }


    fun loaddata(){


        var re = Retro.retrofit.getprofile(id)
        re.enqueue(object : Callback<List<ProfileModel>?> {
            override fun onResponse(
                call: Call<List<ProfileModel>?>,
                response: Response<List<ProfileModel>?>
            ) {
                var responseBody = response.body()

                for(data in responseBody!!){
                    Log.d("@@@@@","$data")
                    txtprofilename.text = data.name
                    txtmobilenumber.text = data.mob
                    txtBirthday.text = data.dob
                    Glide.with(applicationContext).load(data.uri).into(imageView)



                }
            }

            override fun onFailure(call: Call<List<ProfileModel>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}