package com.example.juegopreguntas.bbdd

import android.annotation.SuppressLint
import com.example.juegopreguntas.Aplicacion
import com.example.juegopreguntas.modelos.Pregunta

class LeerPreguntas {

    @SuppressLint("Range")
    fun read(): MutableList<Pregunta> { // Método para generar la lista con todas las preguntas de la BBDD
        val con = Aplicacion.llave.readableDatabase
        val lista = mutableListOf<Pregunta>()

        try {
            // Consulta SQL con JOIN para combinar las tablas
            val query = """
                SELECT p.id AS pregunta_id, p.texto AS pregunta_texto,
                       rv.texto AS respuesta_verdadera,
                       rf.texto AS respuesta_falsa,
                       a.texto AS argumentario
                FROM ${Aplicacion.TABLA_PREGUNTA} p
                LEFT JOIN ${Aplicacion.TABLA_RESPUESTA_VERDADERA} rv ON p.id = rv.pregunta_id
                LEFT JOIN ${Aplicacion.TABLA_RESPUESTA_FALSA} rf ON p.id = rf.pregunta_id
                LEFT JOIN ${Aplicacion.TABLA_ARGUMENTARIO} a ON p.id = a.pregunta_id
            """.trimIndent()

            val cursor = con.rawQuery(query, null)

            if (cursor.moveToFirst()) {
                do {
                    // Obtener los datos de cada columna
                    val preguntaId = cursor.getInt(cursor.getColumnIndex("pregunta_id"))
                    val preguntaTexto = cursor.getString(cursor.getColumnIndex("pregunta_texto"))
                    val respuestaVerdadera = cursor.getString(cursor.getColumnIndex("respuesta_verdadera")) ?: ""
                    val respuestaFalsa = cursor.getString(cursor.getColumnIndex("respuesta_falsa")) ?: ""
                    val argumentario = cursor.getString(cursor.getColumnIndex("argumentario")) ?: ""

                    // Crear el objeto Pregunta
                    val preguntaCompleta = Pregunta(
                        enunciadoPregunta = preguntaTexto,
                        respuestaVerdadera = respuestaVerdadera,
                        respuestaFalsa = respuestaFalsa,
                        argumentario = argumentario
                    )

                    // Añadir la pregunta a la lista
                    lista.add(preguntaCompleta)

                } while (cursor.moveToNext())
            }

            cursor.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            con.close()
        }

        return lista
    }
}
