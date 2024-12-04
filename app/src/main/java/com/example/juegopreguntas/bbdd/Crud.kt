package com.example.juegopreguntas.bbdd


import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.juegopreguntas.Aplicacion
import com.example.juegopreguntas.modelos.Partida


class Crud {
    fun create(partida: Partida): Long{
        val con = Aplicacion.llave.writableDatabase
        return try {
            con.insertWithOnConflict(
                Aplicacion.TABLA_PARTIDAS,
                null,
                partida.toContentValues(),
                SQLiteDatabase.CONFLICT_IGNORE
            )
        } catch (ex: Exception){
            ex.printStackTrace()
            -1L
        } finally {
            con.close()
        }
    }

    fun read(): MutableList<Partida> {
        val lista = mutableListOf<Partida>()
        val con = Aplicacion.llave.readableDatabase
        try {
            val cursor = con.query(
                Aplicacion.TABLA_PARTIDAS,
                arrayOf("id", "nombre", "fecha", "puntuacion"),
                null,
                null,
                null,
                null,
                null,
                null
            )
            while (cursor.moveToNext()) {
                val partida = Partida(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
                )
                lista.add(partida)
            }
        } catch (ex: Exception){
            ex.printStackTrace()
        } finally {
            con.close()

        }
        return lista
    }

    fun borrar(id: Int): Boolean {
        val con = Aplicacion.llave.writableDatabase
        val partidaBorrada = con.delete(Aplicacion.TABLA_PARTIDAS, "id=?", arrayOf(id.toString()))
        con.close()
        return partidaBorrada>0
    }

    fun actualizar(partida: Partida): Boolean {
        val con = Aplicacion.llave.writableDatabase
        val values = partida.toContentValues()
        var filasAfectadas = 0

        val q = " select id from ${Aplicacion.TABLA_PARTIDAS} where nombre = ? AND id <> ?"
        val cursor = con.rawQuery(q, arrayOf(partida.nombre, partida.id.toString()))
        val existeDuplicado  = cursor.moveToFirst()

        cursor.close()
        if (!existeDuplicado ) {
            filasAfectadas = con.update(Aplicacion.TABLA_PARTIDAS, values,"id = ?", arrayOf(partida.id.toString()))
        }
        con.close()
        return filasAfectadas > 0
    }



    private fun Partida.toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("nombre", this.nombre)
        values.put("fecha", this.fecha)
        values.put("puntuacion", this.puntuacion)
        return values
    }
}
