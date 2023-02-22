package com.example.task2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ItemModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,var description:String,var mrp:Int,var discount:Int,var category:String,var itemImage:String)
