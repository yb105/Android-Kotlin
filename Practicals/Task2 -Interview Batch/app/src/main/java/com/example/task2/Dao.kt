package com.example.task2

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@androidx.room.Dao
interface Dao {

    @Insert
    suspend fun insertData(data: CategoryModel)

    @Insert
    suspend fun insertItemData(itemModel: ItemModel)

    @Insert
    suspend fun insertSpinnerData(spinnerModel: SpinnerModel)

    @Delete
    suspend fun deleteData(data: CategoryModel)

    @Update
    suspend fun updateData(data: CategoryModel)

    @Query("SELECT * FROM category")
    fun getallData(): LiveData<List<CategoryModel>>

    @Query("SELECT cName FROM spinner")
    fun getallSpinnerData(): LiveData<List<String>>

    @Query("SELECT * FROM item")
    fun getallItemData(): LiveData<List<ItemModel>>
}