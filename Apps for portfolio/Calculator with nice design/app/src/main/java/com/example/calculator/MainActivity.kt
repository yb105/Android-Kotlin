package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity(), View.OnClickListener  {

       lateinit var txtSolution:TextView
       lateinit var txttwonum:TextView
       var solution = 0
    var num1:Int =0
    var num2:Int = 0
    var symbol = ""
    var text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtSolution = findViewById<TextView>(R.id.txtSolution)
        txttwonum = findViewById<TextView>(R.id.txtTwoNum)


        var btnclear = findViewById<Button>(R.id.tvClear)
        var btnpercentage = findViewById<Button>(R.id.tvpercentage)
        var btndivide = findViewById<Button>(R.id.tvdivide)
        var btnmultiply = findViewById<Button>(R.id.tvmultiplication)
        var btnadd = findViewById<Button>(R.id.tvplus)
        var btnminus = findViewById<Button>(R.id.tvminus)
        var btnequal = findViewById<Button>(R.id.tvequal)
        var btnbackspace = findViewById<Button>(R.id.tvempty)
        var btndot = findViewById<Button>(R.id.tvdot)
        var btnone = findViewById<Button>(R.id.tvone)
        var btntwo = findViewById<Button>(R.id.tvtwo)
        var btnthree = findViewById<Button>(R.id.tvthree)
        var btnfour = findViewById<Button>(R.id.tvfour)
        var btnfive = findViewById<Button>(R.id.tvfive)
        var btnsix = findViewById<Button>(R.id.tvsix)
        var btnseven = findViewById<Button>(R.id.tvseven)
        var btneight = findViewById<Button>(R.id.tveight)
        var btnnine = findViewById<Button>(R.id.tvnine)
        var btnzero = findViewById<Button>(R.id.tvzero)


        btnclear.setOnClickListener(this)
        btnadd.setOnClickListener(this)
        btnbackspace.setOnClickListener(this)
        btndot.setOnClickListener(this)
        btnpercentage.setOnClickListener(this)
        btndivide.setOnClickListener(this)
        btnminus.setOnClickListener(this)
        btnmultiply.setOnClickListener(this)
        btnzero.setOnClickListener(this)
        btnone.setOnClickListener(this)
        btntwo.setOnClickListener(this)
        btnthree.setOnClickListener(this)
        btnfour.setOnClickListener(this)
        btnfive.setOnClickListener(this)
        btnsix.setOnClickListener(this)
        btnseven.setOnClickListener(this)
        btneight.setOnClickListener(this)
        btnnine.setOnClickListener(this)
        btnequal.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        when(view!!.id){

            R.id.tvClear -> {txtSolution.setText("0")
            txttwonum.setText("")}

            R.id.tvpercentage -> {symbol = "%"
            text = text + "%"
                txttwonum.setText(text)}
            R.id.tvdivide -> {symbol = "÷"
                text = text + "÷"
                txttwonum.setText(text)}
            R.id.tvmultiplication ->{symbol = "*"
                text = text + "*"
                txttwonum.setText(text)}
            R.id.tvplus -> {symbol = "+"
                text = text + "+"
                txttwonum.setText(text)}
            R.id.tvminus -> {symbol = "-"
                text = text + "-"
                txttwonum.setText(text)}

            R.id.tvzero -> {
                if(num1 == 0){
                num1 = 0
                    text = num1.toString()
                    txttwonum.setText(text)

            }else{
                num2 = 0
                    text = text + num2.toString()
                    txttwonum.setText(text)
            } }

            R.id.tvone -> {if(num1 == 0){
                num1 = 1
                text = num1.toString()
                txttwonum.setText(text)
            }else{
                num2 = 1
                text = text + num2.toString()
                txttwonum.setText(text)
            } }

            R.id.tvtwo -> {if(num1 == 0){
                num1 = 2
                text = num1.toString()
                txttwonum.setText(text)

            }else{
                num2 = 2
                text = text + num2.toString()
                txttwonum.setText(text)
            } }

            R.id.tvthree -> {if(num1 == 0){
                num1 = 3
                text = num1.toString()
                txttwonum.setText(text)

            }else{
                num2 = 3
                text = text + num2.toString()
                txttwonum.setText(text)
            } }

            R.id.tvfour -> {if(num1 == 0){
                num1 = 4
                text = num1.toString()
                txttwonum.setText(text)
            }else{
                num2 = 4
                text = text + num2.toString()
                txttwonum.setText(text)
            } }

            R.id.tvfive -> {if(num1 == 0){
                num1 = 5
                text = num1.toString()
                txttwonum.setText(text)
            }else{
                num2 = 5
                text = text + num2.toString()
                txttwonum.setText(text)
            } }

            R.id.tvsix -> {if(num1 == 0){
                num1 = 6
                text = num1.toString()
                txttwonum.setText(text)
            }else{
                num2 = 6
                text = text + num2.toString()
                txttwonum.setText(text)
            } }

            R.id.tvseven -> {if(num1 == 0){
                num1 = 7
                text = num1.toString()
                txttwonum.setText(text)
            }else{
                num2 = 7
                text = text + num2.toString()
                txttwonum.setText(text)
            } }

            R.id.tveight -> {if(num1 == 0){
                num1 = 8
                text = num1.toString()
                txttwonum.setText(text)
            }else{
                num2 = 8
                text = text + num2.toString()
                txttwonum.setText(text)
            } }

            R.id.tvnine -> {if(num1 == 0){
                num1 = 9
                text = num1.toString()
                txttwonum.setText(text)
            }else{
                num2 = 9
                text = text + num2.toString()
                txttwonum.setText(text)
            } }

            R.id.tvequal -> {
                when(symbol){

                "+" -> {solution= num1 + num2

                }
                 "-" ->{solution= num1 - num2

                 }
                    "*" -> {solution= num1 * num2

                    }
                    "/" ->{solution= num1 / num2

                    }
                    "%" -> {solution= num1 % num2

                    }

                }
                txtSolution.setText(solution.toString())
                num1 = 0
                num2 = 0
            }
        R.id.tvempty -> {
            if(num1 != 0 && num2 ==0){

            }
        }
        }
        }
    }


