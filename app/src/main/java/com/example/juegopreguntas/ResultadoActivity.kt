package com.example.juegopreguntas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.juegopreguntas.bbdd.Crud
import com.example.juegopreguntas.databinding.ActivityResultadoBinding
import com.example.juegopreguntas.modelos.Partida
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ResultadoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        devolverVerdaderas()
        cargarEventos()
    }

    private fun crearRegistro(puntuacion: Int) {
        val partida = Partida(0,"Partida-"+obtenerMilisegundosDesdeEpoch(),obtenerFechaActual(),puntuacion)
        val result=Crud().create(partida)
    }

    private fun cargarEventos() {
        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)//volvemos al main activity
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("StringFormatMatches")
    private fun devolverVerdaderas() {
        val sharedPreferences = getSharedPreferences("PUNTUACION", MODE_PRIVATE)
        val puntuacionActual = sharedPreferences.getInt("Respuestas_verdaderas", 0)
        crearRegistro(puntuacionActual)
        binding.tvResultado.text = getString(R.string.lb_resultado_final, puntuacionActual)
        if (puntuacionActual<5){
            binding.ivResultado.setImageResource(R.drawable.img_menos5)
        }else{
            binding.ivResultado.setImageResource(R.drawable.img_mas5)
        }

    }
    fun obtenerFechaActual(): String {
        val fechaActual = LocalDate.now() // Obtiene la fecha actual
        val formato = DateTimeFormatter.ofPattern("ddMMyyyy") // Define el formato
        return fechaActual.format(formato) // Devuelve la fecha formateada
    }
    fun obtenerMilisegundosDesdeEpoch(): Long {
        return System.currentTimeMillis() // Obtiene el tiempo en milisegundos desde el 1 de enero de 1970
    }

}