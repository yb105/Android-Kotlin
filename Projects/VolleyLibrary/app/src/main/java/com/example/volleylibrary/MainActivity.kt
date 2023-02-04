package com.example.volleylibrary

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.volleylibrary.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val url = "https://meme-api.com/gimme"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnMeme.setOnClickListener {

            loadMeMe()
        }



        loadMeMe()
    }

    private fun loadMeMe() {

        var progress = ProgressDialog(this)
        progress.setMessage("wait till data is fetching")
        progress.show()

        val queue = Volley.newRequestQueue(this)



        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var responseObject = JSONObject(response)

                binding.txttitle.text = responseObject.getString("title")
                binding.txtauthor.text = responseObject.getString("author")

                Glide.with(this).load(responseObject.get("url")).into(binding.imgMeme)
                progress.dismiss()


            },
            Response.ErrorListener {
                progress.dismiss()
            })


        queue.add(stringRequest)
    }
}