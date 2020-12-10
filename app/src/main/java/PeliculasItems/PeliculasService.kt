package PeliculasItems

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PeliculasService  {
    @GET("peliculas/")
    fun listMovies(@Header("Authorization") authorization:String):Call<List<ItemData>>

    @GET("peliculas/{peliculaId}")
    fun movieDetails(@Header("Authorization") authorization:String, @Path("peliculaId") peliculId:Int):Call<ItemData>
}