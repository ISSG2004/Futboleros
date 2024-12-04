package com.example.juegopreguntas.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.juegopreguntas.R
import com.example.juegopreguntas.modelos.Partida

class AdapterRegistro(
    private var lista: MutableList<Partida>,
    private var borrar: (Int) -> Unit,
    private val editar: (Partida) -> Unit
) : RecyclerView.Adapter<ViewHolderRegistro>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRegistro {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.registro_partidas , parent, false)
        return ViewHolderRegistro(v)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: ViewHolderRegistro, position: Int) {
        holder.cargar(lista[position], borrar, editar)
    }
}
