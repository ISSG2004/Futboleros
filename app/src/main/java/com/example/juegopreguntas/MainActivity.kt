package com.example.juegopreguntas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.juegopreguntas.bbdd.LeerPreguntas
import com.example.juegopreguntas.modelos.Pregunta
import com.example.juegopreguntas.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var listaPreguntas=mutableListOf<Pregunta>()
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth= Firebase.auth
        crearSharedPrefPuntuacion()
        crearEventos()
        }

    private fun crearSharedPrefPuntuacion() {//creamos una sahred preference para almacenar el numero de respuestas correctas
        val sharedPreferences=getSharedPreferences("PUNTUACION", MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putInt("Respuestas_verdaderas",0)
        editor.apply()
    }

    private fun crearEventos() {
        val intent = Intent(this, PreguntaActivity::class.java)
        intent.putExtra("PREGUNTAS" , ArrayList(generarPreguntasJuego()))
        binding.btnInicioJuego.setOnClickListener {
            startActivity(intent) // Iniciar la nueva actividad
            finish()
        }
        binding.btnCerrar.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.btnRegistro.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
            finish()
        }
    }

    private fun generarPreguntasJuego():MutableList<Pregunta>{
        listaPreguntas=LeerPreguntas().read()
        var lista10Preguntas= mutableListOf<Pregunta>()
        var listaIndicesPreguntas=mutableSetOf<Int>()
        while (listaIndicesPreguntas.size < 10) {
            listaIndicesPreguntas.add(generarNumeroAleatorio())
        }
        val indicesPreguntasList = listaIndicesPreguntas.toList()
        for (i in 0 until 10) {
            val preguntaIndex = indicesPreguntasList[i]
            // Asegurarte de que el índice esté dentro del rango de listaPreguntas
            if (preguntaIndex >= 0 && preguntaIndex < listaPreguntas.size) {
                lista10Preguntas.add(listaPreguntas[preguntaIndex])

            }
        }
        return lista10Preguntas
    }

    fun generarNumeroAleatorio(): Int {
        return Random.nextInt(50)
    }

}
