package com.example.roomdatabase.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAO {

    @Insert
    suspend fun insertData(data: Data)

    @Delete
    suspend fun deleteData(data: Data)

   @Query("SELECT * FROM person")
    fun getallData():LiveData<List<Data>>

}