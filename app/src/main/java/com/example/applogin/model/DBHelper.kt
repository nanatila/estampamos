package com.example.applogin.model

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.fragment.app.FragmentActivity

class DBHelper(context: FragmentActivity?):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "info"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE " + Tables.information["TABLE_NAME"]  + " (" +
                Tables.information["_id"] + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Tables.information["_nombre"] + " TEXT NOT NULL, " +
                Tables.information["_direccion"] + " TEXT NOT NULL, " +
                Tables.information["_telefono"] + " TEXT NOT NULL, " +
                Tables.information["_correo"] + " TEXT NOT NULL )"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert(name:String, address:String, phone:String, email:String){
        var data = ContentValues()
        data.put(Tables.information["_nombre"], name)
        data.put(Tables.information["_direccion"], address)
        data.put(Tables.information["_telefono"], phone)
        data.put(Tables.information["_correo"], email)
        var db = this.writableDatabase

        db.insert(Tables.information["TABLE_NAME"], null, data)
        db.close()
    }

    fun edit(Id:Int, name:String, address:String, phone:String, email:String,){
        var args = arrayOf(Id.toString())
        var data = ContentValues()
        data.put(Tables.information["_nombre"], name)
        data.put(Tables.information["_direccion"], address)
        data.put(Tables.information["_telefono"], phone)
        data.put(Tables.information["_correo"], email)
        var db = this.writableDatabase

        db.update(Tables.information["TABLE_NAME"], data, Tables.information["_id"]+"=?", args)
        db.close()
    }
}