package com.example.task2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spinner")
data class SpinnerModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var cName:String)
