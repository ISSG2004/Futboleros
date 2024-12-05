package com.example.juegopreguntas.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit= Retrofit.Builder()
        .baseUrl("https://www.thesportsdb.com/api/v1/json/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiClient= retrofit.create(ApiInterfaz::class.java)
}