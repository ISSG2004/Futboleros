package com.example.juegopreguntas.api

import com.example.juegopreguntas.modelos.TeamsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaz {
    @GET("searchteams.php")
    suspend fun cargarEquipos(@Query("t") equipo: String) : Response<TeamsResponse>
}