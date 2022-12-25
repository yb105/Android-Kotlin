package com.example.jaybhavani

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    var final:Int = 0
    var choice = "y"

    var itemMap = mapOf<String,Int>("bhel" to 30, "vadapav" to 20, "dabeli" to 30, "kachori" to 30)
    var newMap = mutableMapOf<String, Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnselect = findViewById<Button>(R.id.btnselect)




        btnselect.setOnClickListener {

           dialogue()



        }



    }

    private fun dialogue(){

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialogue_layout,null)
        val  edtOrder = dialogLayout.findViewById<EditText>(R.id.edtOrder)
        val edtQty = dialogLayout.findViewById<EditText>(R.id.edtQty)




        with(builder){

            var txt = findViewById<TextView>(R.id.txtfinal)

            setTitle("Enter Your Order")
            setPositiveButton("Ok"){dialog, which->


                var order:String = ""
                var qty =0


                order = edtOrder.text.toString()
                qty = edtQty.text.toString().toInt()

                if (itemMap.containsKey(order)){

                    newMap.put(order,qty)
                }else{
                    Toast.makeText(this@MainActivity, "Wrong Item", Toast.LENGTH_SHORT).show()
                }

                for(i in newMap.keys){

                    final = newMap.get(i)!! * itemMap.get(i)!!

                    txt.setText("Order: $i   Qty: ${newMap.get(i)}  Total: $final ")

                }
            }
            setView(dialogLayout)
            show()



        }
    }
}