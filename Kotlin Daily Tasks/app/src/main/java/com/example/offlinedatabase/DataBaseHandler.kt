package com.example.offlinedatabase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHandler(context: Context):SQLiteOpenHelper(context,DataBaseHandler.DB_NAME,null,DataBaseHandler.DB_VERSION)
{
    override fun onCreate(db: SQLiteDatabase?) {

        var create_table = "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME VARCHAR(20),$SUBJECT VARCHAR(20))";
        db?.execSQL(create_table)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    fun addStudentData(studentModel: StudentModel):Long{

        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put("$NAME",studentModel.name)
        cv.put("$SUBJECT",studentModel.subject)
        var id = db.insert(TABLE_NAME,null,cv)
        return id
    }

    @SuppressLint("Range")
    fun getallData():MutableList<StudentModel>
    {

        var mytsudenList : MutableList<StudentModel> = ArrayList()
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
        var s_name:String
        var s_subject:String

        if (cursor.count>0){

            if (cursor.moveToFirst()){


                do {
                    s_id = cursor.getInt(cursor.getColumnIndex(ID))
                    s_name = cursor.getString(cursor.getColumnIndex(NAME))
                    s_subject = cursor.getString(cursor.getColumnIndex(SUBJECT))

                    var s_data = StudentModel(id = s_id, name = s_name, subject = s_subject)
                    mytsudenList.add(s_data)

                }while (cursor.moveToNext())
            }
        }

            return mytsudenList
    }

    fun updateData(studentModel: StudentModel):Int {

        var mydb = writableDatabase
        var cv = ContentValues()

        cv.put(NAME,studentModel.name)
        cv.put(SUBJECT,studentModel.subject)

        var id = mydb.update(TABLE_NAME,cv, ID+" = "+studentModel.id,null)
        mydb.close()

        return id
    }

    fun deleteData(studentModel: StudentModel):Int{

        var mydb = writableDatabase
        var delete = mydb.delete(TABLE_NAME,ID+" = "+studentModel.id,null)
        mydb.close()

        return delete
    }

    companion object{

        var DB_NAME = "mydb_sqlite"
        var DB_VERSION = 1
        var TABLE_NAME = "student"
        var ID = "id"
        var NAME = "name"
        var SUBJECT = "subject"
    }

}