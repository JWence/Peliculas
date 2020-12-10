package helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataDbHelper (context:Context):SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    val db:SQLiteDatabase
    val values: ContentValues

    companion object {
        private val DATABASE_NAME = "todo_app"
        private val  DATABASE_VERSION = 1
    }

    init {
        db = writableDatabase
        values = ContentValues()
    }

    override fun onCreate(database: SQLiteDatabase?) {
        database!!.execSQL("CREATE TABLE ${Db.TodoTable.TABLE_NAME} " +
                "(" +
                "${Db.TodoTable.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${Db.TodoTable.TITULO} TEXT NOT NULL, " +
                "${Db.TodoTable.SINOPSIS} TEXT NOT NULL, " +
                "${Db.TodoTable.FECHA_ESTRENO} TEXT NOT NULL, " +
                "${Db.TodoTable.PORTADA} TEXT NOT NULL" +
                ")")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}

