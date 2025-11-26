package com.example.postureapp.vista

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.postureapp.vista.MainActivity
import com.example.postureapp.vista.RegisterActivity
import com.example.postureapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  Abrir pantalla de registro
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        //  Botón Iniciar Sesión
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //  Leer los datos guardados al registrarse
            val prefs = getSharedPreferences("usuarios", MODE_PRIVATE)
            val savedEmail = prefs.getString("email", null)
            val savedPassword = prefs.getString("password", null)

            //  Validar credenciales
            if (email == savedEmail && password == savedPassword) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}