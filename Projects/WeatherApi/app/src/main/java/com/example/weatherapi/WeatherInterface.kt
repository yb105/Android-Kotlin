package com.example.weatherapi

import com.example.weatherapi.Models.WeatherData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



const val baseUrl = "https://api.openweathermap.org"

interface WeatherInterface {

    @GET("/data/2.5/weather")

    fun getWeatherData(@Query("q") q:String,
    @Query("appid") appid:String):Call<WeatherData>


}

object weatherService{

    val weatherService : WeatherInterface

    init {
        var retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        weatherService = retrofit.create(WeatherInterface::class.java)
    }
}