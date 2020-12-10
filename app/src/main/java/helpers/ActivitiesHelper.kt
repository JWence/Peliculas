package helpers

import PeliculasItems.ItemData
import android.content.Context
import android.content.Intent
import com.example.peliculas.DetallesPeliculas

class ActivitiesHelper {
    val OPEN_ADD_TODO_RID = 0
    val OPEN_EDIT_TODO_RID = 1
    fun openAddTodo(ctx:Context):Intent{
        val intent = Intent(ctx, DetallesPeliculas::class.java).apply {
            putExtra("TYPE", "ADD")
        }
        return intent
    }
    fun openEditTodo(ctx: Context, itemData: ItemData):Intent{
        val intent = Intent(ctx, DetallesPeliculas::class.java).apply {
            putExtra("TYPE", "EDIT")
            putExtra("ID", itemData.id)
            putExtra("TITLE", itemData.titulo)
            putExtra("SINOPSIS", itemData.sinopsis)
            putExtra("ESTRENO", itemData.fecha_estrno)
            putExtra("IMAGE_URI", itemData.portada)
        }
        return intent
    }
}