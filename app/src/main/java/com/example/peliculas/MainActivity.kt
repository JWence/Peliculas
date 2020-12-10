package com.example.peliculas

import PeliculasItems.ItemData
import PeliculasItems.PeliculasListAdapter
import PeliculasItems.PeliculasService
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import helpers.ActivitiesHelper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import Rest.RestEngine


class MainActivity : AppCompatActivity() {
    var todoItems:MutableList<ItemData> = ArrayList()
    var adapter:PeliculasListAdapter? = null
    var todoDbController:Db.Controller? = null

    var listPeliculas:MutableList<ItemData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = "Peliculas"
        actionBar.setDisplayHomeAsUpEnabled(true)
        todoDbController = Db.Controller(this)
        this.setTitle("Lista de tareas")
        vAdd.setOnClickListener {
            startActivityForResult(ActivitiesHelper().openAddTodo(this), ActivitiesHelper().OPEN_ADD_TODO_RID)
        }
        /* Obtener información*/
        getTodos()
        initTodoRecycler()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    /*
    override fun onRestart() {
        super.onRestart()
        finish()
        startActivity(intent)
    }*/

    private fun callPeliculas(token: String){
        val peliculasService = RestEngine.getRestEngine().create(PeliculasService::class.java)
        val result = peliculasService.listMovies(token)

        result.enqueue(object: Callback<List<ItemData>> {
            override fun onResponse(
                call: Call<List<ItemData>>, response: Response<List<ItemData>>
            ) {
                if(response.isSuccessful) {
                    for ((id, titulo, fecha_estrno, sinopsis, portada) in response.body()!!) {
                        listPeliculas.add( ItemData (id,titulo,fecha_estrno, sinopsis, portada))
                    }
                    println(response.body()?.get(0)?.titulo)
                    initTodoRecycler()
                }
            }

            override fun onFailure(call: Call<List<ItemData>>, t: Throwable) {
                println("Hubo un problema")
            }
        })
    }



    fun clickEdit(item:ItemData) {
        startActivityForResult(ActivitiesHelper().openEditTodo(this, item), ActivitiesHelper().OPEN_EDIT_TODO_RID)
    }

    fun clickDelete(item:ItemData) {
        todoDbController!!.delete(item)
        getTodos()
        initTodoRecycler()

    }


    /* SetUp fuctions */
    fun getTodos(){

        /* todoDbController!!.insert(
            TodoItemData( "4", "Avengers", "Los héroes más poderosos de la Tierra deben unirse y aprender a luchar en equipo si quieren evitar que el malicioso Loki y su ejército alienígena esclavicen a la humanidad.","25 abril 2012", "https://image.tmdb.org/t/p/w94_and_h141_bestv2/tyKKKSvjEgDVQ6vtm8vzL5Zx06c.jpg")
        )*/
        /*
        todoDbController!!.insert(
            ItemData ("1", "El lobo de Wall Street","Película basada en hechos reales del corredor de bolsa neoyorquino Jordan Belfort (Leonardo DiCaprio). A mediados de los años 80, Belfort era un joven honrado que perseguía el sueño americano, pero pronto en la agencia de valores aprendió que lo más importante no era hacer ganar a sus clientes, sino ser ambicioso y ganar una buena comisión. Su enorme éxito y fortuna le valió el apodo de “El lobo de Wall Street”. Dinero, poder, mujeres, drogas. Las tentaciones abundaban y el temor a la ley era irrelevante. Jordan y su manada de lobos consideraban que la discreción era una cualidad anticuada; nunca se conformaban con lo que tenían.", "2013-12-25","https://image.tmdb.org/t/p/w220_and_h330_face/jTlIYjvS16XOpsfvYCTmtEHV10K.jpg")
        )
         */

        todoItems = todoDbController!!.getTodos()
    }

    fun initTodoRecycler() {
        adapter = PeliculasListAdapter(todoItems, this, ::clickEdit, ::clickDelete)
        vRecyclerTodos.layoutManager = LinearLayoutManager(this)
        vRecyclerTodos.adapter = adapter
    }
}
