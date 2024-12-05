package com.example.juegopreguntas

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
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
    private var equipoBuscador = "arsenal" // Inicializar como string vacío o con el valor que necesites

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
        cargarRecycler()
        cargarEventos()
    }

    private fun cargarEventos() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                equipoBuscador = newText ?: ""
                cargarEquipo() // Consultar equipos desde la API
                return true
            }
        })
    }

    private fun cargarEquipo() {
        lifecycleScope.launch(Dispatchers.IO) {
            val datos = ApiClient.apiClient.cargarEquipos(equipoBuscador) // Enviar búsqueda dinámica a la API
            val listaEquipos = datos.body()?.teams ?: emptyList()

            withContext(Dispatchers.Main) {
                if (equipoBuscador.isEmpty()){

                }else{
                    if (datos.isSuccessful) {
                        adapter.actualizarEquipos(listaEquipos)
                    } else {
                        adapter.actualizarEquipos(emptyList())
                        Toast.makeText(this@BuscadorEquiposActivity, "No hay equipos disponibles", Toast.LENGTH_SHORT).show()
                    }
                }
                }

        }
    }


    private fun cargarRecycler() {
        val layoutManager= GridLayoutManager(this, 1)
        binding.recyclerEquipos.layoutManager=layoutManager
        binding.recyclerEquipos.adapter=adapter
    }

}