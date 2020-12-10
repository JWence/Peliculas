package PeliculasItems

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculas.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_movie_item.view.*

class PeliculasListAdapter (val items:MutableList<ItemData>, val context:Context, val onClickEdit:(item: ItemData) -> Unit, val onClickDelete:(item: ItemData) -> Unit):RecyclerView.Adapter<PeliculasListAdapter.TodoHolder>(){

    //var todoDbController: Db.Controller? = null
    class TodoHolder(val itemTemplate:View):RecyclerView.ViewHolder(itemTemplate) {
        fun render(item: ItemData, context: Context, onClickEdit:(item: ItemData) -> Unit, onClickDelete:(item: ItemData) -> Unit) {
            /* Datos del template */
            itemTemplate.vUsuario.text = item.titulo
            //itemTemplate.vTodoMessage.text = item.message
            //itemTemplate.vTodoMessage.text = item.sinopsis
            itemTemplate.vComentario.text = item.fecha_estrno
            Picasso.get().load(item.portada).into(itemTemplate.vTodoImage)
            /* Button Listeners */
            itemTemplate.vTodoImage.setOnClickListener {

                onClickEdit(item)
            }
            itemTemplate.vUsuario.setOnClickListener {

                onClickEdit(item)
            }
           /* itemTemplate.vTodoDone.setOnClickListener {
                onClickDelete(item)
                //Pruebas
                println("=============================")
                println("Done")
                println(item)
                println(item.id)
                println("=============================")


            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TodoHolder(
            layoutInflater.inflate(
                R.layout.activity_main_movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.render(items[position], context, onClickEdit, onClickDelete)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}