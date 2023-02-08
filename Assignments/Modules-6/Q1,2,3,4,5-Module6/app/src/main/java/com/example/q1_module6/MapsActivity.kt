package com.example.q1_module6

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import com.example.q1_module6.databinding.ActivityMapsBinding


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.io.IOException
import java.util.Arrays

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var currentLocation:Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        //Place autocomplete
        //Places.initialize(applicationContext,"AIzaSyAu2dsFe-Og8bs5hunDV31iJ_PZXFS4UAM")

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

       binding.idSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
           override fun onQueryTextSubmit(query: String?): Boolean {
                var l = binding.idSearchView.query.toString()


               var addresslist: List<Address>? = null

               if (l != null || l.equals("")){

                 //  var fieldList :List<Place.Field> = Arrays.asList(Place.Field.ADDRESS,Place.Field.LAT_LNG,Place.Field.NAME)

                 //  var intent =  Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,fieldList).build(this@MapsActivity)

                 //  startActivityForResult(intent,100)

                   var geocoder = Geocoder(this@MapsActivity)

                   try {
                       addresslist = geocoder.getFromLocationName(l,1)

                   }catch (e:IOException){
                       e.printStackTrace()
                   }

                   var address = addresslist?.get(0)

                   val latLng = address?.let { LatLng(it.latitude, address.longitude) }

                   mMap.addMarker(MarkerOptions().position(latLng!!).title(l))

                   mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))


               }

               return false
           }

           override fun onQueryTextChange(newText: String?): Boolean {
               return false
           }
       })


        getCurrentLocationUser()
    }


    private fun getCurrentLocationUser() {


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED  && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),permissionCode)
            return

        }

        val getlocation = fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->

            if (location != null){

                currentLocation = location

                Toast.makeText(applicationContext, currentLocation.latitude.toString()+""+currentLocation.longitude.toString(), Toast.LENGTH_SHORT).show()

                val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)


            }

        }



    }

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK ){

            var place = Autocomplete.getPlaceFromIntent(data)
            binding.idSearchView.setQuery(place.address,true)
        }else if (resultCode == AutocompleteActivity.RESULT_ERROR){

            var status = Autocomplete.getStatusFromIntent(data)
            Toast.makeText(this@MapsActivity, status.statusMessage, Toast.LENGTH_LONG).show()

        }
    }*/


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){

            permissionCode -> if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                getCurrentLocationUser()
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap


        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("Current Location")

        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,7f))
        googleMap?.addMarker(markerOptions)


        binding.btnchangeMode.setOnClickListener {

            if (googleMap.mapType == GoogleMap.MAP_TYPE_NORMAL){

                googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }else if (googleMap.mapType == GoogleMap.MAP_TYPE_SATELLITE){

                googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }else{
                googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
        }



    }


}