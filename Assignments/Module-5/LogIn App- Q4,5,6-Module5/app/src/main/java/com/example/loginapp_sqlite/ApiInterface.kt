package com.example.loginapp_sqlite

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("register.php")
    fun register( @Field("username") username:String,
                        @Field("email") email:String,
                        @Field("password") password:String): Call<UserModel>

    @FormUrlEncoded
    @POST("login.php")
    fun login(@Field("email") email:String,
                  @Field("password") password:String): Call<UserModel>


    @FormUrlEncoded
    @POST("project_details.php")
    fun project_details(@Field("project_name") project_name:String,
              @Field("project_des") project_des:String): Call<ProjectModel>

    @GET("get_project_details.php")
    fun getallproject():Call<List<ProjectModel>>


    @FormUrlEncoded
    @POST("update_project_details.php")
    fun update_student(@Field("project_name") project_name:String,
                       @Field("project_des") project_des:String):Call<ProjectModel>


    @FormUrlEncoded
    @POST("profile.php")
    fun profile(@Field("id") id:Int,
        @Field("name") name:String,
                  @Field("dob") dob:String,
                  @Field("mob") mob:String,
    @Field("uri") uri:String): Call<ProfileModel>

    @GET("get_id.php")
    fun getID(@Query("email") email:String):Call<List<UserModel>>


    @GET("get_profile.php")
    fun getprofile(@Query("id") id:Int):Call<List<ProfileModel>>

    @FormUrlEncoded
    @POST("delete_project.php")
    fun delete_project(@Field("id") id:Int):Call<ProjectModel>







}

object Retro{

    var retrofit = Retrofit.Builder()
        .baseUrl("https://yashpal10521.000webhostapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
        .create(ApiInterface::class.java)
}


