package com.example.firebasedatabase

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi

class SendSms : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_sms)

        var ed = findViewById<EditText>(R.id.ednum)
        var edmsg = findViewById<EditText>(R.id.edmessage)
        var btn = findViewById<Button>(R.id.btnsms)

        requestPermissions(arrayOf(Manifest.permission.SEND_SMS),1)

        btn.setOnClickListener {

            var smsManager:SmsManager
            smsManager = SmsManager.getDefault()

            smsManager.sendTextMessage(ed.text.toString(),null,edmsg.text.toString(),null,null)
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show()

        }


    }
}