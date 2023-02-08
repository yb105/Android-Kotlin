package com.example.firebasedatabase

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),1)

        var ed = findViewById<EditText>(R.id.ed_phone)
        var btn = findViewById<Button>(R.id.btncall)

        btn.setOnClickListener {
            var intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:"+ed.text.toString())
            startActivity(intent)
        }
    }
}