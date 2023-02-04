package com.example.todoapp.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Adapters.ComAdapter
import com.example.todoapp.DataBaseHandler
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.ToDoModel


class CompletedTasksFragment : Fragment() {


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_completed_tasks, container, false)


        var rev = v.findViewById<RecyclerView>(R.id.reCom)
        var arrayList:MutableList<ToDoModel> = ArrayList()

        rev.layoutManager = LinearLayoutManager(context)

        var dataBaseHandler = DataBaseHandler(context!!)

        arrayList = dataBaseHandler.getcompletedallData()

        Log.i("t","$arrayList")

        rev.adapter = ComAdapter(context!!,arrayList)











        return v
    }



}