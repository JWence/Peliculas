package UsuarioItems
import retrofit2.Call
import retrofit2.http.*

interface UsuarioService{
    @POST("author/add")

    fun registro(
        @Body user: UsuarioItem?
    ): Call<UsuarioItem?>
}
