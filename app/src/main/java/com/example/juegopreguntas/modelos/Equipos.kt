package com.example.juegopreguntas.modelos

import java.io.Serializable

data class Equipos(
    //reemplazar por los valores de la API
    val id:Int,
    val nombre: String,
    val imagen:String
):Serializable

