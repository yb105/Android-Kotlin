package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuestionScreen : AppCompatActivity(), View.OnClickListener {

    var selectedTV = 0
    var name = ""

    lateinit var txtquestion: TextView
    lateinit var txtoption1: TextView
    lateinit var txtoption2: TextView
    lateinit var txtoption3: TextView
    lateinit var txtoption4: TextView
    lateinit var txtprogress: TextView
    lateinit var progressbar: ProgressBar
    lateinit var  btnsubmit:Button


    var questionPosition = 0
    var score = 0
    var loop = 0

    lateinit var getdata: ArrayList<DataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_screen)



        getdata = ArrayList()
        txtquestion = findViewById(R.id.txtquestion)
        txtoption1 = findViewById(R.id.txt1)
        txtoption2 = findViewById(R.id.txt2)
        txtoption3 = findViewById(R.id.txt3)
        txtoption4 = findViewById(R.id.txt4)
        txtprogress = findViewById(R.id.txtprogress)
        progressbar = findViewById(R.id.progressBar)
        btnsubmit = findViewById<Button>(R.id.btnNext)

        btnsubmit.setOnClickListener(this)
        txtoption1.setOnClickListener(this)
        txtoption2.setOnClickListener(this)
        txtoption3.setOnClickListener(this)
        txtoption4.setOnClickListener(this)


        settingQuestionAndOption()
        unhighlightall()

        name = intent.getStringExtra(Constants.name).toString()


    }


    fun settingQuestionAndOption() {

        getdata = Constants.getquestion()
        if (questionPosition <= getdata.size){

            txtquestion.text = getdata[questionPosition].question
            txtoption1.text = getdata[questionPosition].option1
            txtoption2.text = getdata[questionPosition].option2
            txtoption3.text = getdata[questionPosition].option3
            txtoption4.text = getdata[questionPosition].option4
            txtprogress.text = "${questionPosition}/${getdata.size}"
            progressbar.progress = questionPosition
            btnsubmit.text = "Submit"
        }

    }


    fun unhighlightall() {

          selectedTV = 0
        val option = ArrayList<TextView>()

        option.add(txtoption1)
        option.add(txtoption2)
        option.add(txtoption3)
        option.add(txtoption4)

        for (i in option) {
            i.background = ContextCompat.getDrawable(this, R.drawable.editextborder)
            i.setTextColor(Color.parseColor("#675858"))
            i.setTypeface(i.typeface, Typeface.NORMAL)

        }


    }


    fun selectedTextView(tv: TextView, pos: Int) {

        if (selectedTV==0){

            unhighlightall()
            tv.background = ContextCompat.getDrawable(this, R.drawable.afterclicked)
            tv.setTextColor(Color.parseColor("#070707"))
            tv.setTypeface(tv.typeface, Typeface.BOLD)


            selectedTV = pos
        }


    }


    fun correctAnswer(answer: Int) {

        if (answer == getdata[questionPosition].answer) {

            when (answer) {

                1 -> txtoption1.setBackgroundColor(Color.GREEN)
                2 -> txtoption2.setBackgroundColor(Color.GREEN)
                3 -> txtoption3.setBackgroundColor(Color.GREEN)
                4 -> txtoption4.setBackgroundColor(Color.GREEN)

            }

            score++

        } else {

            when (answer) {

                1 -> {

                    txtoption1.setBackgroundColor(Color.RED)
                }
                2 -> {
                    txtoption2.setBackgroundColor(Color.RED)
                }
                3 -> {
                    txtoption3.setBackgroundColor(Color.RED)
                }
                4 -> {
                    txtoption4.setBackgroundColor(Color.RED)
                }
            }

            when (getdata[questionPosition].answer) {

                1 -> txtoption1.setBackgroundColor(Color.GREEN)
                2 -> txtoption2.setBackgroundColor(Color.GREEN)
                3 -> txtoption3.setBackgroundColor(Color.GREEN)
                4 -> txtoption4.setBackgroundColor(Color.GREEN)

            }
        }


        questionPosition++
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.txt1 -> selectedTextView(txtoption1, 1)
            R.id.txt2 -> selectedTextView(txtoption2, 2)
            R.id.txt3 -> selectedTextView(txtoption3, 3)
            R.id.txt4 -> selectedTextView(txtoption4, 4)

            R.id.btnNext -> {

                if ( btnsubmit.text == "Finish"){
                    var i  = Intent(this,EndActivity::class.java)
                    i.putExtra(Constants.score,score)
                    i.putExtra(Constants.name2,name)
                    finish()
                    startActivity(i)
                }

                    if (selectedTV > 0) {

                        if (btnsubmit.text == "Next"){
                            unhighlightall()
                            settingQuestionAndOption()
                            btnsubmit.text = "Submit"
                        }else{

                            btnsubmit.text = "Next"
                            correctAnswer(selectedTV)


                            if (questionPosition==getdata.size){

                                btnsubmit.text = "Finish"
                            }

                        }

                    } else{

                        Toast.makeText(this, "Please Select Option", Toast.LENGTH_SHORT).show()

                    }



                }



            }
        }
    }
