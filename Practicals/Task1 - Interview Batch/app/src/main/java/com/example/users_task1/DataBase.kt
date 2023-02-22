package com.example.users_task1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1)
abstract class DataBase:RoomDatabase() {


    abstract fun taskdao():Dao


    companion object{

        private var INSTANCE:DataBase? = null

        fun getDatabase(context: Context):DataBase{
            if (INSTANCE == null){
                synchronized(this){

                    INSTANCE =  Room.databaseBuilder(context,DataBase::class.java,"AllDatabse").build()
                }
            }

            return INSTANCE!!
        }
    }
}