package com.example.stickynoteapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHandler(var context: Context):SQLiteOpenHelper(context,DB_NAME,null,VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {

        var create_table = "CREATE TABLE $TBL_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT, $TITLE VARCHAR(50), $DES VARCHAR(200), $TIME VARCHAR(10))"
        db?.execSQL(create_table)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addStickyData(stickyModel: StickyModel):Long{
        var mydb = this.writableDatabase
        var cv = ContentValues()

        cv.put(TITLE,stickyModel.title)
        cv.put(DES,stickyModel.des)
        cv.put(TIME,stickyModel.time)

        var success = mydb.insert(TBL_NAME,null,cv)

        mydb.close()
        return success

    }

    @SuppressLint("Range")
    fun getStickyData():ArrayList<StickyModel>{

        var stickylist:ArrayList<StickyModel> = ArrayList()
        var query = "SELECT * FROM $TBL_NAME"
        var mydb = this.readableDatabase

        var cursor:Cursor? = null

        try {
            cursor = mydb.rawQuery(query,null)
        }catch (e : java.lang.Exception){
            mydb.execSQL(query)
            return  ArrayList()
        }
        var id:Int
        var title:String
        var des : String
        var time : String

        if (cursor.count > 0){

            if (cursor.moveToFirst()){

                do {
                    id = cursor.getInt(cursor.getColumnIndex(ID))
                    title = cursor.getString(cursor.getColumnIndex(TITLE))
                    des = cursor.getString(cursor.getColumnIndex(DES))
                    time = cursor.getString(cursor.getColumnIndex(TIME))

                    val stickyModel = StickyModel(id = id, title = title, des = des, time = time)
                    stickylist.add(stickyModel)
                }while (cursor.moveToNext())
            }
        }

 return stickylist
    }

    fun updateStickyData(stickyModel: StickyModel):Int{

        var mydb = this.writableDatabase
        var cv = ContentValues()

        cv.put(TITLE,stickyModel.title)
        cv.put(DES,stickyModel.des)
        cv.put(TIME,stickyModel.time)

        var update = mydb.update(TBL_NAME,cv, ID+" = "+stickyModel.id,null)
        mydb.close()
        return update
    }

    fun deleteStickyData(stickyModel: StickyModel):Int{

        var mydb = writableDatabase
        var delete = mydb.delete(TBL_NAME,ID+" = "+stickyModel.id,null)
        mydb.close()

        return delete
    }
    companion object{

        var DB_NAME = "sticky"
        var VERSION = 1
        var TBL_NAME = "notes"
        private const val ID = "id"
        private const val TITLE = "title"
        private const val DES = "description"
        private const val TIME = "time"
    }
}