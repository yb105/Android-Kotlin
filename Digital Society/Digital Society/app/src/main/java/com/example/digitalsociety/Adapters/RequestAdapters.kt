package com.example.digitalsociety.Adapters

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalsociety.Model.UserModel
import com.example.digitalsociety.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RequestAdapters(var context: Context,var arrayList: ArrayList<UserModel>):RecyclerView.Adapter<RequestAdapters.MyViewHolder>() {
    class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {

        var name = itemview.findViewById<TextView>(R.id.txtrevRequest)
        var constaintlayout = itemview.findViewById<CardView>(R.id.requestRevConstraint)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var v = LayoutInflater.from(parent.context).inflate(R.layout.viewrequestrevview,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = arrayList[position].username

        holder.constaintlayout.setOnClickListener {

            opendialogue(position)
        }
    }

    private fun opendialogue(p: Int) {

        var d = Dialog(context)
        d.setContentView(R.layout.requestdialogue)
        d.show()

        var username = d.findViewById<TextView>(R.id.txtrequestDialogueUsername)
        var phone = d.findViewById<TextView>(R.id.txtrequestDialoguePhone)
        var house = d.findViewById<TextView>(R.id.txtrequestDialogueHouse)
        var position = d.findViewById<TextView>(R.id.txtrequestDialoguePosition)

        username.text = arrayList[p].username
        phone.text = arrayList[p].phone
        house.text = arrayList[p].house
        position.text = arrayList[p].postion

        var btndelete = d.findViewById<Button>(R.id.btnRequestDialogueDelete)
        var btnapprove = d.findViewById<Button>(R.id.btnRequestDialogueApprove)


        btnapprove.setOnClickListener {
           var databaseReference:DatabaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(arrayList[p].userid)

            var hashMap:HashMap<String,Any> = HashMap()
            hashMap.put("status",true)

            databaseReference.updateChildren(hashMap as Map<String, Any>).addOnSuccessListener {

                Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show()
                d.dismiss()
            }.addOnFailureListener {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                d.dismiss()
            }

        }

        btndelete.setOnClickListener {
            var alert = AlertDialog.Builder(context)
            alert.setTitle("Delete")
            alert.setMessage("Are You Sure You Want To Delete This Word.")
            alert.setPositiveButton("Yes"){dialog,which->

                var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(arrayList[p].userid)
                databaseReference.removeValue().addOnSuccessListener {

                    Toast.makeText(context, "Request Deleted", Toast.LENGTH_SHORT).show()
                    d.dismiss()
                }.addOnFailureListener {

                    Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show()
                    d.dismiss()
                }
            }

            alert.setNegativeButton("No"){dialog,which ->


            }

            alert.show()
        }
    }
}