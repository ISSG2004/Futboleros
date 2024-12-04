package com.example.juegopreguntas.bbdd

import android.annotation.SuppressLint
import com.example.juegopreguntas.Aplicacion
import com.example.juegopreguntas.modelos.Pregunta

class LeerPreguntas(){
    @SuppressLint("Range")
    fun read():MutableList<Pregunta>{ //Metodo para generarme la lista con todas las preguntas de la bbdd
        val con=Aplicacion.llave.readableDatabase
        var lista=mutableListOf<Pregunta>()
        try {
        // Obtener los cursores para las 4 tablas
            val cursorPregunta = con.query(
                Aplicacion.TABLA_PREGUNTA,
                arrayOf("id", "texto"),
                null, null, null, null, null
            )

            val cursorRVerdadera = con.query(
                Aplicacion.TABLA_RESPUESTA_VERDADERA,
                arrayOf("id", "texto", "pregunta_id"),
                null, null, null, null, null
            )

            val cursorRFalsa = con.query(
                Aplicacion.TABLA_RESPUESTA_FALSA,
                arrayOf("id", "texto", "pregunta_id"),
                null, null, null, null, null
            )

            val cursorArgumentario = con.query(
                Aplicacion.TABLA_ARGUMENTARIO,
                arrayOf("id", "texto", "pregunta_id"),
                null, null, null, null, null
            )

            // Listas para almacenar las respuestas y argumentarios
            val respuestasVerdaderasList = mutableListOf<Pair<Int, String>>() // id, texto
            val respuestasFalsasList = mutableListOf<Pair<Int, String>>() // id, texto
            val argumentariosList = mutableListOf<Pair<Int, String>>() // id, texto

            // Procesar las respuestas verdaderas
            if (cursorRVerdadera.moveToFirst()) {
                do {
                    val id = cursorRVerdadera.getInt(cursorRVerdadera.getColumnIndex("id"))
                    val texto = cursorRVerdadera.getString(cursorRVerdadera.getColumnIndex("texto"))
                    val preguntaId = cursorRVerdadera.getInt(cursorRVerdadera.getColumnIndex("pregunta_id"))
                    respuestasVerdaderasList.add(Pair(preguntaId, texto))
                } while (cursorRVerdadera.moveToNext())
            }
            cursorRVerdadera.close()

            // Procesar las respuestas falsas
            if (cursorRFalsa.moveToFirst()) {
                do {
                    val id = cursorRFalsa.getInt(cursorRFalsa.getColumnIndex("id"))
                    val texto = cursorRFalsa.getString(cursorRFalsa.getColumnIndex("texto"))
                    val preguntaId = cursorRFalsa.getInt(cursorRFalsa.getColumnIndex("pregunta_id"))
                    respuestasFalsasList.add(Pair(preguntaId, texto))
                } while (cursorRFalsa.moveToNext())
            }
            cursorRFalsa.close()

            // Procesar los argumentarios
            if (cursorArgumentario.moveToFirst()) {
                do {
                    val id = cursorArgumentario.getInt(cursorArgumentario.getColumnIndex("id"))
                    val texto = cursorArgumentario.getString(cursorArgumentario.getColumnIndex("texto"))
                    val preguntaId = cursorArgumentario.getInt(cursorArgumentario.getColumnIndex("pregunta_id"))
                    argumentariosList.add(Pair(preguntaId, texto))
                } while (cursorArgumentario.moveToNext())
            }
            cursorArgumentario.close()

            // Procesar las preguntas
            if (cursorPregunta.moveToFirst()) {
                do {
                    val preguntaId = cursorPregunta.getInt(cursorPregunta.getColumnIndex("id"))
                    val preguntaTexto = cursorPregunta.getString(cursorPregunta.getColumnIndex("texto"))

                    // Buscar la respuesta verdadera y falsa para esta pregunta
                    val respuestaVerdadera = respuestasVerdaderasList.firstOrNull { it.first == preguntaId }?.second ?: ""
                    val respuestaFalsa = respuestasFalsasList.firstOrNull { it.first == preguntaId }?.second ?: ""

                    // Buscar el argumentario para esta pregunta
                    val argumentario = argumentariosList.firstOrNull { it.first == preguntaId }?.second ?: ""

                    // Crear la pregunta completa y añadirla a la lista
                    val preguntaCompleta = Pregunta(
                        enunciadoPregunta = preguntaTexto,
                        respuestaVerdadera = respuestaVerdadera,
                        respuestaFalsa = respuestaFalsa,
                        argumentario = argumentario
                    )

                    lista.add(preguntaCompleta)

                } while (cursorPregunta.moveToNext())
            }
            cursorPregunta.close()
        }   catch (ex: Exception){
            ex.printStackTrace()
        }   finally {
            con.close()
        }

        return lista
    }


}