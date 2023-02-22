package com.example.task2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var categoryName:String,
    var categoryProfile:String)
