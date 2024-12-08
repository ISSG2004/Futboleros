package com.example.juegopreguntas

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.example.juegopreguntas.databinding.ActivityMapaBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity(), OnMapReadyCallback {
    private val locationPermissionRequest = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permisos ->
        if (
            permisos[Manifest.permission.ACCESS_FINE_LOCATION] == true

            ||
            permisos[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ){
            gestionarLocalizacion()
        }else{
            Toast.makeText(this,"El usuario denegó los permisos de localizacion", Toast.LENGTH_SHORT).show()
        }
    }
    private lateinit var mapa: GoogleMap
    private lateinit var binding: ActivityMapaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarMapa()
        cargarEventos()
    }


    override fun onRestart() {
        super.onRestart()
        gestionarLocalizacion()
    }
    override fun onMapReady(p0: GoogleMap) {
        mapa = p0//llamar al mapa
        mapa.uiSettings.isZoomControlsEnabled = true//activar controles de zoom
        mapa.uiSettings.isZoomGesturesEnabled = true//activar gestos de zoom
        // Spotify Camp Nou
        ponerMarcador(LatLng(41.3809, 2.1228))
        // Santiago Bernabéu
        ponerMarcador(LatLng(40.4531, -3.6883))
        // Cívitas Metropolitano
        ponerMarcador(LatLng(40.4362, -3.5995))
        //Benito Villamarín
        ponerMarcador(LatLng(37.3565, -5.9810))
        // Estadio Olímpico de la Cartuja
        ponerMarcador(LatLng(37.4194, -6.0039))
        // San Mamés
        ponerMarcador(LatLng(43.2641, -2.9497))
        // Mestalla
        ponerMarcador(LatLng(39.4749, -0.3583))
        // Ramón Sánchez-Pizjuán
        ponerMarcador(LatLng(37.3841, -5.9701))
        // Reale Arena
        ponerMarcador(LatLng(43.3017, -1.9734))
        // La Romareda
        ponerMarcador(LatLng(41.6340, -0.8947))
        gestionarLocalizacion()
    }

    private fun cargarEventos() {
        binding.btnVolver.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    private fun ponerMarcador(coordenadas: LatLng) {
        val marker= MarkerOptions().position(coordenadas).title("")
        mapa.addMarker(marker)//añadimos la marca al mapa
    }


    private fun iniciarMapa() {
        val fragment = SupportMapFragment()
        fragment.getMapAsync(this)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fm_mapa, fragment)
        }
    }
    private fun gestionarLocalizacion() {
        if (!::mapa.isInitialized) return //comprobar que el mapa esta inicializado
        if (//revisar que el permiso de localizacion precisa este activado
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            &&
            //revisar que el permiso de localizacion no precisa este activado
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mapa.isMyLocationEnabled = true
            mapa.uiSettings.isMyLocationButtonEnabled = true
        } else {
            pedirPermisos()
        }

    }

    private fun pedirPermisos() {
        if (
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            ||
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            mostrarExplicacion()
        } else {
            escogerPermisos()
        }
    }

    private fun escogerPermisos() {
        locationPermissionRequest.launch(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
        )
    }

    private fun mostrarExplicacion() {
        AlertDialog.Builder(this)
            .setTitle("Permiso requerido")
            .setMessage("Por favor, habilita los permisos en la configuración de la aplicación.")
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Aceptar") { dialog, _ ->
                startActivity(Intent(Settings.ACTION_APPLICATION_SETTINGS))
                dialog.dismiss()
            }
            .create()
            .dismiss()
    }

}