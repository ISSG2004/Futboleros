package com.example.juegopreguntas.adapter


import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.juegopreguntas.EditarRegistroActivity
import com.example.juegopreguntas.databinding.RegistroPartidasBinding
import com.example.juegopreguntas.modelos.Partida

class ViewHolderRegistro(v: View) : RecyclerView.ViewHolder(v) {
    val binding = RegistroPartidasBinding.bind(v)
    fun cargar(
        partida: Partida,
        borrar: (Int) -> Unit,
        editar: (Partida) -> Unit
    ) {
        binding.tvNombrePartida2.text = partida.nombre
        binding.tvFechaJugada.text = partida.fecha
        binding.tvPuntos.text = partida.puntuacion.toString()

        binding.btnBorrar.setOnClickListener {
            borrar(adapterPosition)
        }

        binding.btnEditar.setOnClickListener {
            var intent = Intent(binding.root.context, EditarRegistroActivity::class.java)
            intent.putExtra("PARTIDA", partida)
            binding.root.context.startActivity(intent)
        }
    }
}
