package com.example.weatherapi.Models

data class WeatherData(
    val weather: List<Weather>,
    val main:Main,
    val name:String
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Main(
    val temp:Double,

)
