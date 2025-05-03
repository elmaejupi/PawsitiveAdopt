package com.example.aadoption

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "adoption_database.db"
        const val TABLE_USERS = "users"
        const val COL_ID = "id"
        const val COL_USERNAME = "username"
        const val COL_PASSWORD = "password"
        const val DATABASE_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_USERS ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_USERNAME TEXT, $COL_PASSWORD TEXT)"
        db?.execSQL(createTableQuery)
        Log.d("DatabaseHelper", "Database created.")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Implement database schema changes here if needed
    }

    // funksionet
    fun insertUser(email: String, username: String, password: String): Long {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_USERNAME, username)
        contentValues.put(COL_PASSWORD, password)

        val result = db.insert(TABLE_USERS, null, contentValues)

        if (result != -1L) {
            Log.d("DatabaseHelper", "User inserted successfully.")
        } else {
            Log.e("DatabaseHelper", "Error inserting user.")
        }

        return result
    }

    fun getUserCount(username: String, password: String): Int {
        val db = readableDatabase
        val columns = arrayOf(COL_ID)
        val selection = "$COL_USERNAME = ? AND $COL_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor: Cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null)
        val count = cursor.count
        cursor.close()
        return count
    }
}
