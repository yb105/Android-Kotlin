package com.example.roomdatabase.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Data(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,
    var age:Int
)
