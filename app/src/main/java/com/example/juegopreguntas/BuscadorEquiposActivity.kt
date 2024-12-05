package com.example.juegopreguntas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.juegopreguntas.adapter.AdapterEquipos
import com.example.juegopreguntas.api.ApiClient
import com.example.juegopreguntas.databinding.ActivityBuscadorEquiposBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuscadorEquiposActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscadorEquiposBinding
    private val adapter: AdapterEquipos = AdapterEquipos()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityBuscadorEquiposBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun cargarEquipo() {
        lifecycleScope.launch(Dispatchers.IO) {
            val datos = ApiClient.apiClient.cargarEquipo("")
            val equipoDatos= datos.body()
            withContext(Dispatchers.Main){
                if(datos.isSuccessful){
                    adapter.lista=equipoDatos
                    adapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(this@BuscadorEquiposActivity, "No hay equipos disponibles", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}