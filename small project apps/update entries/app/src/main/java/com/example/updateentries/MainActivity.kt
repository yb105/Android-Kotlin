package com.example.updateentries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        var arrayList = ArrayList<MyModel>()
        var filterList = ArrayList<MyModel>()

        var edUpdate = findViewById<EditText>(R.id.edUpdate)
        var edSearch = findViewById<EditText>(R.id.edSearch)
        var edEntry = findViewById<EditText>(R.id.edEntry)
        var btnUpdate = findViewById<Button>(R.id.btnUpdate)
        var btnSearch = findViewById<Button>(R.id.btnSearch)
        var btnEnter = findViewById<Button>(R.id.btnEnter)



        recyclerView.layoutManager = LinearLayoutManager(this)
        var adapter = MyAdapter(this@MainActivity, arrayList)
        recyclerView.adapter = adapter

        btnEnter.setOnClickListener {


            arrayList.add(MyModel(edEntry.text.toString(), R.drawable.ic_baseline_delete_24))
        }

        btnSearch.setOnClickListener {
            var st = edSearch.text.toString()



            edSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    TODO("Not yet implemented")
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (st != "") {

                        if (arrayList.contains(MyModel(st, R.drawable.ic_baseline_delete_24))) {

                            filterList.add(MyModel(st, R.drawable.ic_baseline_delete_24))

                            var adapter = MyAdapter(this@MainActivity, filterList)
                            recyclerView.adapter = adapter
                        }

                    } else {

                        var adapter = MyAdapter(this@MainActivity, arrayList)
                        recyclerView.adapter = adapter
                    }
                }


            })


        }
    }
}