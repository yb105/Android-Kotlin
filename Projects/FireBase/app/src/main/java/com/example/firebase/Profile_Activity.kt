package com.example.firebase

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.io.IOException
import android.Manifest
import android.app.ProgressDialog
import android.os.Build
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.UUID

class Profile_Activity : AppCompatActivity() {

    lateinit var firebaseUser:FirebaseUser
    lateinit var databaseReference: DatabaseReference

    private var filepath: Uri? = null
    val PICK_IMAGE_REQUEST:Int = 1001
    private lateinit var imageView: ImageView
    lateinit var edusername:EditText

    lateinit var storageReference: StorageReference

    lateinit var progressbar :ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        storageReference = FirebaseStorage.getInstance().reference.child("Images")

        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)

        ActivityCompat.requestPermissions(this@Profile_Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)



        imageView = findViewById(R.id.imageView)
         edusername = findViewById<EditText>(R.id.edittextuser)
        var txtusername = findViewById<TextView>(R.id.txtusername)
         progressbar = findViewById<ProgressBar>(R.id.progressbar_slider)
        var btn = findViewById<Button>(R.id.updateprofile)


        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(UserModel::class.java)

                Log.e("@@",""+snapshot)
                Log.e("@@@",""+filepath.toString())

                txtusername.text= user!!.username.toString()
                edusername.setText(user!!.username.toString())
                Glide.with(this@Profile_Activity).load(user.profile_pic).placeholder(R.mipmap.ic_launcher).into(imageView)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })



        btn.setOnClickListener {
           uploadImage()
           progressbar.visibility = View.VISIBLE


        }

        imageView.setOnClickListener {
            loadImage()
        }


    }

    fun loadImage(){

        var intent:Intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"select image"),PICK_IMAGE_REQUEST)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode != null){

            filepath = data!!.data

            try {
                var bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepath)
                imageView.setImageBitmap(bitmap)
            }catch (e:IOException){

            }
        }
    }

    fun uploadImage(){

        if(filepath!=null){


            storageReference = storageReference.child( UUID.randomUUID().toString())
            storageReference.putFile(filepath!!).addOnSuccessListener {

                    var hashMap:HashMap<String,String> = HashMap()
                   hashMap.put("username",edusername.text.toString())
                hashMap.put("profile_pic",filepath.toString())
                   databaseReference.updateChildren(hashMap as Map<String,Any>)

                    Toast.makeText(this@Profile_Activity, "Uploaded", Toast.LENGTH_SHORT).show()
                    progressbar.visibility = View.GONE


            }.addOnFailureListener{


                    Toast.makeText(this@Profile_Activity, "Failed", Toast.LENGTH_SHORT).show()
                    progressbar.visibility = View.GONE

            }
        }
    }
}