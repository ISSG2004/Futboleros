package com.example.juegopreguntas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.juegopreguntas.databinding.ActivityDetalleEquipoBinding
import com.example.juegopreguntas.modelos.Teams

class DetalleEquipoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleEquipoBinding
    private lateinit var equipo: Teams
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
        traerEquipo()
        configurarWebView()
        cargarEventos()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configurarWebView() {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        val url = equipo.strWebsite.trim()
        if (url.isNotEmpty() && !url.startsWith("http")) {
            binding.webView.loadUrl("https://$url")
        } else {
            binding.webView.loadUrl(url)
        }
    }

    private fun traerEquipo() {
        equipo = intent.getSerializableExtra("EQUIPO") as Teams
    }

    private fun cargarEventos() {
        binding.btnVolver.setOnClickListener{
            startActivity(Intent(this, BuscadorEquiposActivity::class.java))
        }

    }
}