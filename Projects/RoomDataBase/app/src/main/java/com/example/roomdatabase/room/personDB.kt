package com.example.roomdatabase.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1)
abstract class personDB :RoomDatabase(){

    abstract fun dao():DAO
}