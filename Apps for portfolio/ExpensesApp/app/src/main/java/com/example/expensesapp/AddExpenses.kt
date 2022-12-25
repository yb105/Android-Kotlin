package com.example.expensesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddExpenses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expenses)

        var eddetail = findViewById<EditText>(R.id.eddetail)
        var edamount = findViewById<EditText>(R.id.eddamount)
        var btndone = findViewById<Button>(R.id.btnDone)
    }
}