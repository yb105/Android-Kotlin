package com.example.q9_module4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tbl = findViewById<TableLayout>(R.id.tableLayout)

        val textview = TextView(this)
        textview.setText("Hello")
        textview.layoutParams = TableLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        textview.gravity = Gravity.CENTER_HORIZONTAL

        tbl.addView(textview)


    }
}