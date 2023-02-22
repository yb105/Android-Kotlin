package com.example.users_task1

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hello")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name: String?,
    var email:String?,
    var age:String?,
    var dob:String?,
    var contact:String?,
    var gender:String?)