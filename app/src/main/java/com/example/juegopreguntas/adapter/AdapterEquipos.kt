package com.example.juegopreguntas.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.juegopreguntas.R

import com.example.juegopreguntas.modelos.Teams
class AdapterEquipos(private var equiposOriginales: List<Teams> = emptyList()) : RecyclerView.Adapter<EquiposViewHolder>() {
    private var equiposMostrados: MutableList<Teams> = equiposOriginales.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquiposViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tarjeta_equipo, parent, false)
        return EquiposViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EquiposViewHolder, position: Int) {
        val team = equiposMostrados[position]
        holder.cargarEquipos(team)
    }

    override fun getItemCount() = equiposMostrados.size

    fun actualizarEquipos(nuevosEquipos: List<Teams>) {
        equiposOriginales = nuevosEquipos
        equiposMostrados = nuevosEquipos.toMutableList()
        notifyDataSetChanged()
    }

    fun filtrarEquipos(equipoBuscado: String) {
        equiposMostrados = if (equipoBuscado.isBlank()) {
            equiposOriginales.toMutableList()
        } else {
            equiposOriginales.filter { it.strTeam.contains(equipoBuscado, ignoreCase = true) }.toMutableList()
        }
        notifyDataSetChanged()
    }
}


