package com.example.juegopreguntas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.juegopreguntas.R

import com.example.juegopreguntas.modelos.Teams
import java.util.logging.Filter

class AdapterEquipos(var teams: List<Teams> = emptyList()) : RecyclerView.Adapter<EquiposViewHolder>() {

    // Crear un ViewHolder para cada elemento de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquiposViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tarjeta_equipo, parent, false)
        return EquiposViewHolder(itemView)
    }

    // Vincular los datos al ViewHolder
    override fun onBindViewHolder(holder: EquiposViewHolder, position: Int) {
        val team = teams[position]
        holder.cargarEquipos(team)
    }

    // Retornar el número total de elementos en la lista
    override fun getItemCount() = teams.size

    // Método para actualizar la lista de equipos
    fun actualizarEquipos(nuevosEquipos: List<Teams>) {
        teams = nuevosEquipos
        notifyDataSetChanged()
    }
    
    }

}

