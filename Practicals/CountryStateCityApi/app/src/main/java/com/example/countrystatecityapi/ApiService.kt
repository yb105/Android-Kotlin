package com.example.countrystatecityapi

import com.example.countrystatecityapi.models.CityModel
import com.example.countrystatecityapi.models.CountriesModel
import com.example.countrystatecityapi.models.StateModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("countries/")
    fun getCountries(@HeaderMap headers: Map<String, String> ):Call<ArrayList<CountriesModel>>

    @GET("states/{c}")
    fun getStates(@HeaderMap headers: Map<String, String> ,@Path("c") c:String):Call<ArrayList<StateModel>>

    @GET("cities/{s}")
    fun getCities(@HeaderMap headers: Map<String, String>, @Path("s") s:String):Call<ArrayList<CityModel>>

}

object Retro{

    var retro = Retrofit.Builder().baseUrl("https://www.universal-tutorial.com/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(ApiService::class.java)




    fun getCountries():Map<String,String>{
        var hashMap:HashMap<String,String> = hashMapOf()
        hashMap.put( "Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJ0dEBnbWFpbC5jb20iLCJhcGlfdG9rZW4iOiJDQWZwOGFsazNPYnBRd1YwQTl5eFcyMzMzRmVCa1JTTlUyNFdFcW5SRDd2bktVNlFkbTU4aEJGcUZfcXBBYXR5OG5ZIn0sImV4cCI6MTY3NzIyMDQ2MH0.R2i9oydajywg9aStah7mh1jt_zwargoSOl3rYk4mV5g")
        hashMap.put( "Accept","application/json")
        return hashMap

    }
}