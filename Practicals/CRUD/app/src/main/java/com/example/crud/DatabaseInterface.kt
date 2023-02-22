package com.example.crud

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface DatabaseInterface {

    @FormUrlEncoded
    @POST("insert_user.php")
    fun insert_user(@Field("name") name:String,
                       @Field("company") company:String,
                       @Field("postion") postion:String): Call<DataUser>


    @GET("get_user.php")
    fun getalluser():Call<List<DataUser>>

    @FormUrlEncoded
    @POST("update_user.php")
    fun update_user(@Field("name") name:String,
                    @Field("postion") postion:String):Call<DataUser>

    @FormUrlEncoded
    @POST("delete_user.php")
    fun delete_user(@Field("id") id:Int,
    ):Call<DataUser>


}

object Retro{

    var retrofit = Retrofit.Builder()
        .baseUrl("https://yashpal10521.000webhostapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
        .create(DatabaseInterface::class.java)
}
