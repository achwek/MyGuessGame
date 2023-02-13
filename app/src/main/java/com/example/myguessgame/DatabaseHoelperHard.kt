package com.example.myguessgame

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHoelperHard (context : Context): SQLiteOpenHelper(context,DATABASE_NAME,null,1 ){
    companion object{
       val DATABASE_NAME ="GameHard.db"
        val TABEL_NAME="gamer_table_Hard"
        val COL_ID ="ID"
        val COL_FIRSTNAME="firstName"
        val COL_SCORE ="score"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TABEL_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+"firstName TEXT, score TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS"+ TABEL_NAME)

    }
    fun insertData(firstName : String, score:String){
        val db= this.writableDatabase
        val contentValue= ContentValues()
        contentValue.put(COL_FIRSTNAME,firstName)
        contentValue.put(COL_SCORE,score)
        db.insert(TABEL_NAME,null,contentValue)
    }
   val allData: Cursor
   get(){
       val db = this.writableDatabase
       val res = db.rawQuery("select * from "+ TABEL_NAME+" Order by score DESC Limit 10", null)
       return res
   }



}
