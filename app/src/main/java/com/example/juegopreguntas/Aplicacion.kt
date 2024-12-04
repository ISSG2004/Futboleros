package com.example.juegopreguntas

import android.app.Application
import android.content.Context
import com.example.juegopreguntas.bbdd.BBDDPreguntas

class Aplicacion :Application(){
    companion object{
        const val VERSION=2
        const val DB="Base_1"
        const val TABLA_PREGUNTA = "pregunta"
        const val TABLA_RESPUESTA_VERDADERA = "respuesta_verdadera"
        const val TABLA_RESPUESTA_FALSA = "respuesta_falsa"
        const val TABLA_ARGUMENTARIO = "argumentario"
        const val TABLA_PARTIDAS = "partidas"
        lateinit var contexto: Context
        lateinit var llave: BBDDPreguntas
    }
    override fun onCreate(){
        super.onCreate()
        contexto = applicationContext
        llave = BBDDPreguntas()

    }

}