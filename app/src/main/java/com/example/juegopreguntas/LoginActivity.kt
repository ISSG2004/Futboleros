package com.example.juegopreguntas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.juegopreguntas.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private var email=""
    private var password=""
    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val datos = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val cuenta = datos.getResult(ApiException::class.java)
                if (cuenta != null) {
                    val credenciales = GoogleAuthProvider.getCredential(cuenta.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credenciales)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                irMain()
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                }
            } catch (e: ApiException) {
                Log.d("ERROR DE API:>>>>", e.message.toString())
            }
        }
        if (it.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "El usuario canceló", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth= Firebase.auth
        crearEventos()
    }

    private fun crearEventos() {
        binding.btnLogin.setOnClickListener{
            login()
        }
        binding.btnRegistrarse.setOnClickListener{
            registrarse()
        }
        binding.btnGoogle.setOnClickListener{
            loginGoogle()
        }
    }

    private fun loginGoogle() {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_client_id))
            .requestEmail()
            .build()
        val googleClient = GoogleSignIn.getClient(this, googleConf)
        googleClient.signOut()
        responseLauncher.launch(googleClient.signInIntent)
    }

    private fun registrarse() {
        if (!datosCorrectos())return
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    login()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.message.toString() , Toast.LENGTH_SHORT).show()
            }
    }

    private fun datosCorrectos(): Boolean {
        email=binding.etMail.text.toString().trim()
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etMail.error="Se esperaba una dirección de email correcta"
            return false
        }
        password=binding.etPswd.text.toString().trim()
        if (password.length<5){
            binding.etPswd.error="La contraseña debe tener 5 caraceteres como minimo"
            return false
        }
        return true
    }

    private fun login() {
        if(!datosCorrectos())return
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    irMain()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,it.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }
    override fun onStart() {
        super.onStart()
        val usuario=auth.currentUser
        if (usuario!=null)
            irMain()
    }
    private fun irMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}