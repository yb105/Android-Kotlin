package com.example.users_task1

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@androidx.room.Dao
interface Dao {

    @Insert
    suspend fun insertData(data: UserData)

    @Delete
    suspend fun deleteData(data: UserData)

    @Update
    suspend fun updateData(data: UserData)

    @Query("SELECT * FROM Hello")
    fun getallData(): LiveData<List<UserData>>
}