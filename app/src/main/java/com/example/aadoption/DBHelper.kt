package com.example.aadoption

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
         const val DATABASE_NAME = "pets.db"
         const val DATABASE_VERSION = 1
    }

     val createTableQuery = "CREATE TABLE IF NOT EXISTS animals (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, species TEXT, breed TEXT, age INTEGER)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTableQuery)
        Log.d("DBHelper", "Database created.")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades here
        // Typically, you would alter the table structure or do any necessary migration
        Log.d("DBHelper", "Upgrading database from version $oldVersion to $newVersion")
        // Example: You can drop the existing table and recreate it
        db.execSQL("DROP TABLE IF EXISTS animals")
        onCreate(db)
    }

    fun addAnimal(name: String, species: String, breed: String, age: Int) {
        val db = writableDatabase

        val values = ContentValues().apply {
            put("name", name)
            put("species", species)
            put("breed", breed)
            put("age", age)
        }

        val newRowId = db.insert("animals", null, values)

        db.close()

        Log.d("DBHelper", "New animal inserted with ID: $newRowId")
    }
}
