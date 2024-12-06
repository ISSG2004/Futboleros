package com.example.juegopreguntas.adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.juegopreguntas.databinding.TarjetaEquipoBinding
import com.example.juegopreguntas.modelos.Teams
import com.squareup.picasso.Picasso

class EquiposViewHolder(v: View): RecyclerView.ViewHolder(v) {
    val binding = TarjetaEquipoBinding.bind(v)

    // Método para cargar el logo del equipo utilizando Picasso
    fun cargarEquipos(
            team: Teams,
            irWeb: (Teams) -> Unit
    ) {
        binding.lbNombreEquipo.text = team.strTeam
        binding.lbPais.text = team.strCountry
        // Cargar la imagen del logo del equipo con Picasso
        Picasso.get().load(team.strBadge).into(binding.ivEscudo)
        binding.btnWeb.setOnClickListener {
            irWeb(team)
        }
    }
}

