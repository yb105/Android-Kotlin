package com.example.digitalsociety

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.example.digitalsociety.Model.UserModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class ProfileActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth

    lateinit var edphone: TextInputEditText
    lateinit var edusername: TextInputEditText
    lateinit var edhouse: TextInputEditText
    lateinit var txtEditprofile :TextView
    lateinit var profile:ImageView
    lateinit var btnedit:Button

    private var filepath: Uri? = null
    val PICK_IMAGE_REQUEST:Int = 1001
    lateinit var storageReference: StorageReference


    val selectImageIntent = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri ->
        filepath = uri
        profile.setImageURI(uri)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        edphone = findViewById(R.id.edtphoneProfile)
        edhouse = findViewById(R.id.edthouseprofile)
        txtEditprofile = findViewById(R.id.editpicture)
        edusername = findViewById(R.id.edtUsernameprofile)
        btnedit = findViewById(R.id.btnedit)
        profile = findViewById(R.id.profile_Profile_prfile)

        storageReference = FirebaseStorage.getInstance().reference.child("Images")

        ActivityCompat.requestPermissions(
            this@ProfileActivity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )

        firebaseAuth = FirebaseAuth.getInstance()
        var userId = firebaseAuth.currentUser!!.uid

        databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(userId)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(UserModel::class.java)

                Log.e("@@", "" + snapshot)
                Log.e("@@@", "" + filepath.toString())

                edusername.setText(user!!.username)
                edphone.setText(user!!.phone)
                edhouse.setText(user!!.house)

                if (user.profile_pic == "") {
                    profile.setImageResource(R.drawable.chairman)
                } else {
                    Glide.with(this@ProfileActivity).load(user.profile_pic)
                        .placeholder(R.mipmap.ic_launcher).into(profile)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        txtEditprofile.setOnClickListener {

            selectImageIntent.launch("image/*")
        }




        btnedit.setOnClickListener {
            if (filepath != null) {


                storageReference = storageReference.child(userId)
                storageReference.putFile(filepath!!).addOnSuccessListener {

                    var hashMap: HashMap<String, Any> = HashMap()
                    hashMap.put("username", edusername.text.toString())
                    hashMap.put("house", edhouse.text.toString())
                    hashMap.put("phone", edphone.text.toString())
                    hashMap.put("profile_pic", filepath.toString())
                    databaseReference.updateChildren(hashMap as Map<String, Any>)
                        .addOnSuccessListener {

                            Toast.makeText(this@ProfileActivity, "Edited", Toast.LENGTH_SHORT)
                                .show()

                        }


                }.addOnFailureListener {


                    Toast.makeText(this@ProfileActivity, "Failed", Toast.LENGTH_SHORT).show()

                }
            }


        }
    }


}