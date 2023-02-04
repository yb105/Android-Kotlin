package com.example.task

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.webkit.WebView
import android.webkit.WebViewClient

class WebView : AppCompatActivity() {



  lateinit var webview:WebView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


        webview = findViewById<WebView>(R.id.web_view_display)


            webview.webViewClient = WebViewClient()
            webview.loadUrl("https://www.youtube.com/")

            webview.settings.javaScriptEnabled = true
            webview.settings.setSupportZoom(true)


    }

    override fun onBackPressed() {
        if (webview.canGoBack()){
            webview.goBack()
        }else{
            super.onBackPressed()
        }
    }
}