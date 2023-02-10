package com.example.offline

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHandler (var context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        var create_table = "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT, $TITLE VARCHAR(20), $DESCRIPTION VARCHAR(100) )"
        db?.execSQL(create_table)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    companion object{

        private const val VERSION = 1
        private const val DATABASE_NAME = "NoteDatabase"
        private const val TABLE_NAME = "notes"
        private const val ID = "id"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
    }

   fun addNotesToDatabase(noteModel: NoteModel):Long{

       var db = this.writableDatabase

       var cv = ContentValues()

       cv.put(TITLE, noteModel.title)
       cv.put(DESCRIPTION, noteModel.description)

       val succes = db.insert(TABLE_NAME,null,cv)

       db.close()

       return succes
   }

    @SuppressLint("Range")
    fun viewDataBase(): ArrayList<NoteModel>{

        val empList: ArrayList<NoteModel> = ArrayList()
        val query = "SELECT * FROM $TABLE_NAME"
        var db = this.readableDatabase

        var cursor:Cursor? = null

        try {
            cursor = db.rawQuery(query,null)
        }catch (e: java.lang.Exception){

            db.execSQL(query)
            return ArrayList()
        }

        var id: Int
        var title: String
        var des: String

        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(ID))
                title = cursor.getString(cursor.getColumnIndex(TITLE))
                des = cursor.getString(cursor.getColumnIndex(DESCRIPTION))

                val note = NoteModel(id = id, title = title, description = des)

                empList.add(note)


            }while (cursor.moveToNext())


        }
        return empList




    }

}