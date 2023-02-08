package com.example.retrofit_api

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.retrofit_api.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAPIData()

        binding.btnMeme.setOnClickListener {

            getAPIData()
        }
    }

    private fun getAPIData() {

        var progress = ProgressDialog(this)
        progress.setMessage("wait till data is fetching")
        progress.show()


        RetrofitRefrenece.apiInterface.getData().enqueue(object : Callback<responseDataClass?> {
            override fun onResponse(
                call: Call<responseDataClass?>,
                response: Response<responseDataClass?>
            ) {

                binding.txttitle.text = response.body()?.title
                binding.txtauthor.text = response.body()?.author

                Glide.with(this@MainActivity).load(response.body()?.url).into(binding.imgMeme)
                progress.dismiss()
            }

            override fun onFailure(call: Call<responseDataClass?>, t: Throwable) {
              progress.dismiss()
            }
        })
    }
}