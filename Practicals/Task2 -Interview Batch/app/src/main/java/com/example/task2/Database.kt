package com.example.task2

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [CategoryModel::class,ItemModel::class,SpinnerModel::class], version = 2)
abstract class TaskDataBase:RoomDatabase() {

    abstract fun taskdao():Dao


    companion object{

        private var INSTANCE:TaskDataBase? = null

        fun getDatabase(context: Context):TaskDataBase{
            if (INSTANCE == null){
                synchronized(this){

                   INSTANCE =  Room.databaseBuilder(context,TaskDataBase::class.java,"AllDatabse").build()
                }
            }

            return INSTANCE!!
        }
    }
}