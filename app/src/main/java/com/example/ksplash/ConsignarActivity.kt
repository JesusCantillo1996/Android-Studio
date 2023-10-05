package com.example.ksplash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ConsignarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consignar)

        val etMontoConsignar: EditText = findViewById(R.id.etMontoConsignar)
        val etNumeroDestino: EditText = findViewById(R.id.etNumeroDestino)
        val btnConfirmarConsignacion: Button = findViewById(R.id.btnConfirmarConsignacion)

        val saldo = intent.getIntExtra("saldo", 0) // Obtiene el saldo de MenuActivity

        btnConfirmarConsignacion.setOnClickListener {
            val montoConsignar = etMontoConsignar.text.toString().toIntOrNull() ?: 0
            val numeroDestino = etNumeroDestino.text.toString()

            if (montoConsignar > 0) {
                if (numeroDestino.length == 10) {
                    if (saldo >= montoConsignar) { // Verifica si el saldo es suficiente
                        val intent = Intent()
                        intent.putExtra("montoConsignado", montoConsignar)
                        setResult(Activity.RESULT_OK, intent)
                        Toast.makeText(this, "Envio exitoso", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "No tienes saldo suficiente para enviar esa cantidad.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "El número de destino debe tener 10 cifras.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un monto válido.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


