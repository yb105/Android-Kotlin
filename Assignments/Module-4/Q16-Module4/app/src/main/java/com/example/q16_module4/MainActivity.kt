package com.example.q16_module4

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var l:LinearLayout
    var red = 0
    var green = 0
    var blue = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var seek1 = findViewById<SeekBar>(R.id.seekbar1)
        var seek2 = findViewById<SeekBar>(R.id.seekbar2)
        var seek3 = findViewById<SeekBar>(R.id.seekbar3)
         l = findViewById<LinearLayout>(R.id.linear)

        changeBColor(seek1)
        changeBColor(seek2)
        changeBColor(seek3)
    }

    fun changeBColor(seekBar: SeekBar){

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                when(seekBar.id){

                    R.id.seekbar1 -> {
                        red = seekBar.progress
                        l.setBackgroundColor(Color.rgb(red,green, blue))
                       }
                    R.id.seekbar2 -> {
                        green = seekBar.progress
                        l.setBackgroundColor(Color.rgb(red,green, blue))}
                    R.id.seekbar3 -> {
                        blue = seekBar.progress
                        l.setBackgroundColor(Color.rgb(red,green, blue))}
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
            }

        })

    }
}