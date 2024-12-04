package com.example.juegopreguntas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.juegopreguntas.modelos.Pregunta
import com.example.juegopreguntas.R
import com.example.juegopreguntas.databinding.ActivityArgumentarioBinding
import java.io.Serializable

class ArgumentarioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArgumentarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityArgumentarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración de insets para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        traerDatos() // Cargar datos de la pregunta actual
        siguientePregunta() // Configurar botón de acción
    }

    private fun traerDatos() {
        val argumentario = intent.getStringExtra("ARGUMENTARIO") // Cargar el texto del argumentario
        val respuestaVerdadera = intent.getStringExtra("RESPUESTAVERDADERA") // Respuesta actual
        val listaPregunta = intent.getSerializableExtra("LISTAPREGUNTAS") as? ArrayList<Pregunta>
        // Determinar si la respuesta es verdadera o falsa y acumular en la lista
        if (respuestaVerdadera.isNullOrBlank()) {
            actualizarSharedPref()
            binding.tvResultado.text = R.string.lb_verdadera.toString()
            binding.imageView.setImageResource(R.drawable.img_acierto)

        } else {
            binding.tvResultado.text = R.string.lb_falsa.toString()
            binding.imageView.setImageResource(R.drawable.img_fallo)
        }

        // Mostrar el texto del argumentario actual
        binding.tvArgumentario.text = argumentario
    }

    private fun siguientePregunta() {
        // Intent para finalizar el juego
        val intentFinalizar = Intent(this, ResultadoActivity::class.java)
        // Intent para ir a la siguiente pregunta
        val intentPregunta = Intent(this, PreguntaActivity::class.java)
        val preguntasRestantes = borrarPregunta() // Reducir la lista de preguntas

        // Pasar resultados acumulados y preguntas restantes a las actividades correspondientes
        intentPregunta.putExtra("PREGUNTAS", ArrayList(preguntasRestantes))

        if (preguntasRestantes.isNotEmpty()) {
            // Si quedan preguntas, configurar el botón para avanzar
            binding.btnSiguientePregunta.setOnClickListener {
                startActivity(intentPregunta)
                finish()
            }
        } else {
            binding.btnSiguientePregunta.text=R.string.btn_finalizar_juego.toString()// Cambiar el texto del botón
            // Si no quedan preguntas, finalizar el juego
            binding.btnSiguientePregunta.setOnClickListener {
                startActivity(intentFinalizar)
                finish()
            }
        }
    }
    //metodo en el que accedemos a la sharedPreference e incrementamos el numero de respuestas correctas
    private fun actualizarSharedPref() {
        val sharedPreferences = getSharedPreferences("PUNTUACION", MODE_PRIVATE)
        val puntuacionActual = sharedPreferences.getInt("Respuestas_verdaderas", 0)
        val editor = sharedPreferences.edit()
        editor.putInt("Respuestas_verdaderas", puntuacionActual + 1)
        editor.apply()
    }


    // Reducir la lista de preguntas eliminando la primera
    private fun borrarPregunta(): ArrayList<Pregunta> {
        val listaPreguntas = intent.getSerializableExtra("LISTAPREGUNTAS") as? ArrayList<Pregunta>
        listaPreguntas?.removeAt(0) // Eliminar la primera pregunta
        return listaPreguntas ?: arrayListOf() // Retornar la lista actualizada
    }
}
