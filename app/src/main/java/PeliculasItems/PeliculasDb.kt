package PeliculasItems

import android.content.Context
import helpers.DataDbHelper

class PeliculasDb {
    abstract class TodoTable {
        companion object {
            val TABLE_NAME = "todos"
            val ID = "id"
            val TITULO = "titulo"
            val SINOPSIS = "sinopsis"
            val FECHA_ESTRENO = "fecha_estreno"
            val PORTADA = "portada"
        }
    }
    class Controller(context: Context) {
        private val database:DataDbHelper

        init {
            database = DataDbHelper(context)
        }

        fun insert(item:ItemData) {
            database.values.put(TodoTable.TITULO, item.titulo)
            database.values.put(TodoTable.SINOPSIS, item.sinopsis)
            database.values.put(TodoTable.FECHA_ESTRENO, item.fecha_estrno)
            database.values.put(TodoTable.PORTADA, item.portada)
            database.db.insert(
                TodoTable.TABLE_NAME,
                null,
                database.values
            )
        }
        // Update
        fun update(item:ItemData) {
            var aux = item.id

            database.values.put(TodoTable.TITULO, item.titulo)
            database.values.put(TodoTable.SINOPSIS, item.sinopsis)
            database.values.put(TodoTable.FECHA_ESTRENO, item.fecha_estrno)
            database.values.put(TodoTable.PORTADA, item.portada)
            database.db.update(
                TodoTable.TABLE_NAME, database.values, "${TodoTable.ID} = ${aux}", null)
        }

        fun delete(item:ItemData) {
            var aux = item.id
            database.db.delete(
                TodoTable.TABLE_NAME, "${TodoTable.ID} = ${aux}", null)
        }




        fun getTodos():MutableList<ItemData>{
            var todoItems:MutableList<ItemData> = ArrayList()
            val cols = arrayOf(
                TodoTable.ID,
                TodoTable.TITULO,
                TodoTable.SINOPSIS,
                TodoTable.FECHA_ESTRENO,
                TodoTable.PORTADA
            )
            val query = database.db.query(TodoTable.TABLE_NAME, cols, null, null, null, null, null)
            if(query.moveToFirst()){
                do {
                    todoItems.add(
                        ItemData(
                            query.getString(0),
                            query.getString(1),
                            query.getString(2),
                            query.getString(3),
                            query.getString(4)
                            )
                    )
                }while(query.moveToNext())
            }
            return todoItems
        }
    }
}


