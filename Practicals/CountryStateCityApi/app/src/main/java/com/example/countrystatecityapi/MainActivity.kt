package com.example.countrystatecityapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.countrystatecityapi.models.CityModel
import com.example.countrystatecityapi.models.CountriesModel
import com.example.countrystatecityapi.models.StateModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var spinnerC: Spinner
    lateinit var spinnerS: Spinner
    lateinit var spinnerCity: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerC = findViewById<Spinner>(R.id.sCountry)
       spinnerS = findViewById<Spinner>(R.id.sState)
        spinnerCity = findViewById<Spinner>(R.id.sCity)


        getAllCOuntries()

    }

    private fun getAllStates(country: String) {
        var result = Retro.retro.getStates(Retro.getCountries(), country)
            .enqueue(object : Callback<ArrayList<StateModel>?> {
                override fun onResponse(
                    call: Call<ArrayList<StateModel>?>,
                    response: Response<ArrayList<StateModel>?>
                ) {
                    var list = ArrayList<String>()

                    for (i in response.body()!!) {

                        list.add(i.state_name)
                    }

                    var arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_spinner_item, list
                    )
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerS.adapter = arrayAdapter

                    spinnerS.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {

                            var state = list[position]
                            Toast.makeText(this@MainActivity, "$state", Toast.LENGTH_SHORT).show()
                            getAllCities(state)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }


                }

                override fun onFailure(call: Call<ArrayList<StateModel>?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun getAllCities(state: String) {
        var result = Retro.retro.getCities(Retro.getCountries(),state).enqueue(object : Callback<ArrayList<CityModel>?>{
            override fun onResponse(
                call: Call<ArrayList<CityModel>?>,
                response: Response<ArrayList<CityModel>?>
            ) {
                var list :ArrayList<String> = arrayListOf()

                for (i in response.body()!!){
                    list.add(i.city_name)
                }

                var arrayAdapter:ArrayAdapter<String> = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_item,list)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCity.adapter = arrayAdapter

                spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        var city = list[position]
                        Toast.makeText(this@MainActivity, "$city", Toast.LENGTH_SHORT).show()

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }


                }

            }

            override fun onFailure(call: Call<ArrayList<CityModel>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed!", Toast.LENGTH_SHORT).show()
            }

        })

    }


    private fun getAllCOuntries() {

        var result = Retro.retro.getCountries(Retro.getCountries())
            .enqueue(object : Callback<ArrayList<CountriesModel>?> {
                override fun onResponse(
                    call: Call<ArrayList<CountriesModel>?>,
                    response: Response<ArrayList<CountriesModel>?>
                ) {

                    var list = ArrayList<String>()

                    for (i in response.body()!!) {

                        list.add(i.country_name)
                    }


                    var arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_spinner_item, list
                    )
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerC.adapter = arrayAdapter

                    spinnerC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {

                            var country = list[position]
                            Toast.makeText(this@MainActivity, "$country", Toast.LENGTH_SHORT).show()
                            getAllStates(country)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
                }

                override fun onFailure(call: Call<ArrayList<CountriesModel>?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }


}