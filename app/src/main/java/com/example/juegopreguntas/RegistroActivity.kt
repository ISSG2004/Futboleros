package com.example.juegopreguntas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.juegopreguntas.adapter.AdapterRegistro
import com.example.juegopreguntas.bbdd.Crud
import com.example.juegopreguntas.databinding.ActivityRegistroBinding
import com.example.juegopreguntas.modelos.Partida

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    var lista = mutableListOf<Partida>()
    private val adapter: AdapterRegistro = AdapterRegistro(lista, { posicion -> borrar(posicion) }, { partida -> editar(partida) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarRecycler()
        cargarEventos()
    }

    private fun cargarEventos() {
        binding.btnVolver.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun cargarRecycler() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerPartidas.layoutManager = layoutManager
        lista.clear()
        lista.addAll(traerRegistros())
        adapter.notifyDataSetChanged() // Notifica al adaptador del cambio
        binding.recyclerPartidas.adapter = adapter
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun borrar(posicion: Int) {
        val idPartida = lista[posicion].id
        if (Crud().borrar(idPartida)) {
            lista.removeAt(posicion)
            adapter.notifyItemRemoved(posicion)
            Toast.makeText(this, "Registro eliminado con éxito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error al eliminar el registro", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun editar(partida: Partida) {
        val posicion = lista.indexOf(partida)
        if (posicion != -1) {
            lista[posicion] = partida
            Crud().actualizar(partida)
            adapter.notifyItemChanged(posicion)
        }
    }
    private fun traerRegistros(): MutableList<Partida> {
        return Crud().read()
    }
    override fun onRestart() {
        super.onRestart()
        cargarRecycler()
    }

}
