package com.example.taskmanagement

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHandler(var context: Context): SQLiteOpenHelper(context,DBNAME,null,VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
       var create_table = "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT,$NAME VARCHAR(20)," +
               "$DES VARCHAR(200),$PRIORITY VARCHAR(100),$TIMEANDDATE VARCHAR(20))"

        db?.execSQL(create_table)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addTask(myModel: MyModel):Long{

        val db = this.writableDatabase
        var cv = ContentValues()

        cv.put(NAME,myModel.name)
        cv.put(DES,myModel.des)
        cv.put(PRIORITY,myModel.priority)
        cv.put(TIMEANDDATE,myModel.timeanddate)

        var success = db.insert(TABLE_NAME,null,cv)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getTaskData():ArrayList<MyModel>{

        var arrayList:ArrayList<MyModel> = ArrayList()
        var query = "SELECT * FROM $TABLE_NAME"
        var db = this.readableDatabase

        var cursor:Cursor? = null

        try {
            cursor = db.rawQuery(query,null)
        }catch (e:java.lang.Exception){
            db.execSQL(query)
            return ArrayList()
        }

        var id:Int
        var name:String
        var des:String
        var priority:String
        var timeanddate:String

        if (cursor.moveToFirst()){

            do {
                id = cursor.getInt(cursor.getColumnIndex(ID))
                name = cursor.getString(cursor.getColumnIndex(NAME))
                des = cursor.getString(cursor.getColumnIndex(DES))
                priority = cursor.getString(cursor.getColumnIndex(PRIORITY))
                timeanddate = cursor.getString(cursor.getColumnIndex(TIMEANDDATE))

                var data = MyModel(id = id, name = name, des = des, priority = priority, timeanddate = timeanddate)
                arrayList.add(data)


            }while (cursor.moveToNext())
        }

    return arrayList

    }

    fun updateData(myModel: MyModel):Int{

        var db = this.writableDatabase
        var cv = ContentValues()

        cv.put(NAME,myModel.name)
        cv.put(DES,myModel.des)
        cv.put(PRIORITY,myModel.priority)

        var update = db.update(TABLE_NAME,cv, ID+" = "+myModel.id,null)
        db.close()
        return update
    }


    fun deleteData(myModel: MyModel):Int{

        var db = this.writableDatabase

        var delete = db.delete(TABLE_NAME, ID+" = "+myModel.id,null)
        db.close()
        return delete
    }
    companion object{

        private const val DBNAME = "tasks"
        private const val VERSION = 1
        private const val TABLE_NAME = "tasktable"
        private const val ID = "id"
        private const val NAME = "name"
        private const val DES = "des"
        private const val PRIORITY = "priority"
        private const val TIMEANDDATE = "timeanddate"

    }
}