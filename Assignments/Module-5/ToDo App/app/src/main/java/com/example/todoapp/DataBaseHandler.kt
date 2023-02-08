package com.example.todoapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper





class DataBaseHandler(context: Context):SQLiteOpenHelper(context,DataBaseHandler.DB_NAME,null,DataBaseHandler.DB_VERSION)
{
    override fun onCreate(db: SQLiteDatabase?) {

        var create_table = "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT, $TASKS VARCHAR(200))";
        var create_table2 = "CREATE TABLE $TABLE2($ID2 INTEGER PRIMARY KEY AUTOINCREMENT, $TASKS2 VARCHAR(200))";
        db?.execSQL(create_table)
        db?.execSQL(create_table2)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE2)

    }


    fun addStudentData(toDoModel: ToDoModel):Long{

        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put("$TASKS",toDoModel.task)
        var id = db.insert(TABLE_NAME,null,cv)
        return id
    }

    @SuppressLint("Range")
    fun getallData():MutableList<ToDoModel>
    {

        var mytsudenList : MutableList<ToDoModel> = ArrayList()
        var sel_qry = "select * from student"
        var cursor: Cursor?
        var mydb = this.readableDatabase

        try{
            cursor = mydb.rawQuery(sel_qry,null)
        }catch (e:java.lang.Exception){

            mydb.execSQL(sel_qry)
            return ArrayList()
        }

        var s_id: Int
       var t:String

        if (cursor.count>0){

            if (cursor.moveToFirst()){


                do {
                    s_id = cursor.getInt(cursor.getColumnIndex(ID))
                    t = cursor.getString(cursor.getColumnIndex(TASKS))


                    var s_data = ToDoModel(idd = s_id, task = t )
                    mytsudenList.add(s_data)

                }while (cursor.moveToNext())
            }
        }

        return mytsudenList
    }

    fun updateData(toDoModel: ToDoModel):Int {

        var mydb = writableDatabase
        var cv = ContentValues()

        cv.put(TASKS,toDoModel.task)

        var id = mydb.update(TABLE_NAME,cv, ID+" = "+toDoModel.idd,null)
        mydb.close()

        return id
    }

    fun deleteData(toDoModel: ToDoModel):Int{

        var mydb = writableDatabase
        var delete = mydb.delete(TABLE_NAME,ID+" = "+toDoModel.idd,null)
        mydb.close()

        return delete
    }


    fun addCompletedData(toDoModel: ToDoModel):Long{

        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put("$TASKS2",toDoModel.task)
        var id = db.insert(TABLE2,null,cv)
        return id
    }

    @SuppressLint("Range")
    fun getcompletedallData():MutableList<ToDoModel>
    {

        var mytsudenList : MutableList<ToDoModel> = ArrayList()
        var sel_qry = "select * from data"
        var cursor: Cursor?
        var mydb = this.readableDatabase

        try{
            cursor = mydb.rawQuery(sel_qry,null)
        }catch (e:java.lang.Exception){

            mydb.execSQL(sel_qry)
            return ArrayList()
        }

        var s_id: Int
        var t:String

        if (cursor.count>0){

            if (cursor.moveToFirst()){


                do {
                    s_id = cursor.getInt(cursor.getColumnIndex(ID2))
                    t = cursor.getString(cursor.getColumnIndex(TASKS2))


                    var s_data = ToDoModel(idd = s_id, task = t )
                    mytsudenList.add(s_data)

                }while (cursor.moveToNext())
            }
        }

        return mytsudenList
    }

    fun deleteCompletedData(toDoModel: ToDoModel):Int{

        var mydb = writableDatabase
        var delete = mydb.delete(TABLE2,ID2+" = "+toDoModel.idd,null)
        mydb.close()

        return delete
    }


    companion object{

        var DB_NAME = "mydb_sqlite"
        var DB_VERSION = 3
        var TABLE_NAME = "student"
        var ID = "id"
        var TASKS = "task"


        var TASKS2 = "task2"
        var TABLE2 = "data"
        var ID2 = "id2"
    }

}