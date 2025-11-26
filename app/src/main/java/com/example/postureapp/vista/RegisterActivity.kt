package com.example.postureapp.vista

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.postureapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  Botón de registro
        binding.btnRegistrar.setOnClickListener {
            val nombre = binding.Nombre.text.toString().trim()
            val email = binding.Email.text.toString().trim()
            val password = binding.Password.text.toString().trim()

            // Validar campos vacíos
            if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
            // Validar formato de email
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
            }
            // Validar longitud de contraseña
            else if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            }
            else {
                // Guardar datos localmente (SharedPreferences)
                val prefs = getSharedPreferences("usuarios", MODE_PRIVATE)
                prefs.edit()
                    .putString("nombre", nombre)
                    .putString("email", email)
                    .putString("password", password)
                    .apply()

                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()

                // 🔹 Redirigir al LoginActivity en lugar del Main
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // 🔹 Si ya tiene cuenta (volver al login)
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}