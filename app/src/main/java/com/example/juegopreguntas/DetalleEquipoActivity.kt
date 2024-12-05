package com.example.juegopreguntas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.juegopreguntas.databinding.ActivityDetalleEquipoBinding

class DetalleEquipoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleEquipoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleEquipoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarEventos()
    }

    private fun cargarEventos() {
        binding.btnVolver.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        //pasar url que obtengo de la API
        binding.webView.loadUrl("https://www.google.com/")
    }
}