package com.example.juegopreguntas.api

import com.example.juegopreguntas.modelos.Equipos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaz {
    @GET("api/v1/json/3/searchteams.php")
    suspend fun cargarEquipo(@Query("t") equipo: String) : Response<Equipos>
}