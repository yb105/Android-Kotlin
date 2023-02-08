package com.example.todoapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Adapters.MyAdapter
import com.example.todoapp.AddTask
import com.example.todoapp.DataBaseHandler
import com.example.todoapp.R
import com.example.todoapp.ToDoModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TasksFragment() : Fragment() {


    @SuppressLint("UseRequireInsteadOfGet", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v =  inflater.inflate(R.layout.fragment_tasks, container, false)

        var fab = v.findViewById<FloatingActionButton>(R.id.btnFab)
        fab?.setOnClickListener {
            var i  = Intent(activity,AddTask::class.java)
            activity?.startActivity(i)
        }

        var recyclerView = v.findViewById<RecyclerView>(R.id.revTask)
        recyclerView.layoutManager = LinearLayoutManager(context)

        var dataBaseHandler = DataBaseHandler(context!!)
        var arrayList:MutableList<ToDoModel> = ArrayList()

        arrayList = dataBaseHandler.getallData()

        recyclerView?.adapter = MyAdapter(context!!,arrayList)


             return v

    }



}