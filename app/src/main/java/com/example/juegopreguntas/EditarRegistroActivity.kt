package com.example.juegopreguntas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.juegopreguntas.bbdd.Crud
import com.example.juegopreguntas.databinding.ActivityEditarRegistroBinding
import com.example.juegopreguntas.modelos.Partida

class EditarRegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarRegistroBinding
    private lateinit var partida :Partida
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        traerDatos()
        cargarDatos()
        cargarEventos()
    }
    private fun cargarEventos() {
        binding.btnSalir.setOnClickListener {
            finish()
        }
        binding.btnGuardar.setOnClickListener {
            if (binding.etNombreEdit.text.toString().isNotEmpty()){
                partida.nombre=binding.etNombreEdit.text.toString()
                Crud().actualizar(partida)
                startActivity(Intent(this, RegistroActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show()
            }

        }

    }
    fun cargarDatos(){
        binding.etNombreEdit.setText(partida.nombre)
        binding.tvFechaJugada.text=partida.fecha
        binding.tvPuntos.text=partida.puntuacion.toString()
    }
    private fun traerDatos(){
        partida=intent.getSerializableExtra("PARTIDA") as Partida
    }
}
