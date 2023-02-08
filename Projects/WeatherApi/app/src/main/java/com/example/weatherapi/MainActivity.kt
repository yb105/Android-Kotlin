package com.example.weatherapi

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.weatherapi.Models.Weather
import com.example.weatherapi.Models.WeatherData
import com.example.weatherapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
     val apiId = "826448fdd6b73897fad1a91615d05983"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


            getData()


    }

    private fun getData() {

        var progress = ProgressDialog(this)
        progress.setMessage("wait till data is fetching")
        progress.show()

        weatherService.weatherService.getWeatherData("mumbai",apiId).enqueue(object : Callback<WeatherData?> {
            override fun onResponse(call: Call<WeatherData?>, response: Response<WeatherData?>) {
               val data = response.body()
                if(data!=null){
                   Log.d("hii",""+data)

                    var list: List<Weather>

                    list = data.weather
                    
                    binding.txt.text = list[0].description

                    progress.dismiss()
                }
            }

            override fun onFailure(call: Call<WeatherData?>, t: Throwable) {
                Log.d("hello","error loading data")
                progress.dismiss()
            }
        })
    }
}