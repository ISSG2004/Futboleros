package com.example.juegopreguntas.modelos

import java.io.Serializable

data class Partida(
    val id: Long=0,
    var nombre: String,
    val fecha: String,
    val puntuacion: Int
):Serializable
