package com.example.ksplash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val btn: Button = findViewById(R.id.button2)
        btn.setOnClickListener {
            val phoneNumberEditText: EditText = findViewById(R.id.phoneNumberEditText)
            val phoneNumber: String = phoneNumberEditText.text.toString()

            val passwordEditText: EditText = findViewById(R.id.passwordEditText)
            val password: String = passwordEditText.text.toString()

            if (phoneNumber.isNotEmpty() && phoneNumber.length == 10 && phoneNumber.startsWith("3") && password.isNotEmpty() && password.length == 4) {
                val intent: Intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("phoneNumber", phoneNumber)
                intent.putExtra("password", password) // Puedes pasar la contraseña a la siguiente actividad si es necesario
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else {
                Toast.makeText(this, "Ingrese un número telefónico válido que comience con '3' y una contraseña de 4 dígitos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
