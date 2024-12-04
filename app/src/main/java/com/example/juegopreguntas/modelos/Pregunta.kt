package com.example.juegopreguntas.modelos

import java.io.Serializable

data class Pregunta(
    val enunciadoPregunta:String,
    val respuestaVerdadera:String,
    val respuestaFalsa:String,
    val argumentario:String

):Serializable
