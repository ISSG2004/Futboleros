package com.example.juegopreguntas

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.juegopreguntas.adapter.AdapterEquipos
import com.example.juegopreguntas.api.ApiClient
import com.example.juegopreguntas.databinding.ActivityBuscadorEquiposBinding
import com.example.juegopreguntas.modelos.Teams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuscadorEquiposActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscadorEquiposBinding
    private val adapter = AdapterEquipos() // Iniciar con lista vacía
    private var equipoBuscador = "" // Inicializar como string vacío o con el valor que necesites

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBuscadorEquiposBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cargarEquipo()
        cargarRecycler()
        cargarEventos()
    }

    private fun cargarEventos() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { // Acción cuando se envía la consulta
                equipoBuscador = query ?: ""
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean { // Acción cuando cambia el texto
                equipoBuscador = newText ?: ""
                adapter.getFilter(equipoBuscador)
                return false
            }
        })
    }

    private fun cargarRecycler() {
        binding.recyclerEquipos.adapter = adapter
    }

    private fun cargarEquipo() {
        lifecycleScope.launch(Dispatchers.IO) {
            val datos = ApiClient.apiClient.cargarEquipos(equipoBuscador)
            val listaEquipos = datos.body()?.teams ?: emptyList<Teams>()

            withContext(Dispatchers.Main) {
                if (datos.isSuccessful) {
                    // Actualizar la lista de equipos con la nueva respuesta
                    adapter.actualizarEquipos(listaEquipos)
                } else {
                    Toast.makeText(this@BuscadorEquiposActivity, "No hay equipos disponibles", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}