package com.example.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @FormUrlEncoded
    @POST("insert_student.php")
    fun insert_student(@Field("name") name:String,
                       @Field("subject") subject:String,
                       @Field("city") city:String):Call<StudentData>

    @GET("get_student.php")

    fun getallemp():Call<List<StudentData>>


    @GET("get_specific_student.php")

    fun getspecific(@Query("name") name:String):Call<List<StudentData>>


    @FormUrlEncoded
    @POST("update_student.php")
    fun update_student(@Field("name") name:String,
                       @Field("city") city:String):Call<StudentData>

}