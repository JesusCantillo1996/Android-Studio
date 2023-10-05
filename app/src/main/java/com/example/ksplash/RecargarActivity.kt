package com.example.ksplash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecargarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recargar)

        val etMontoRecarga: EditText = findViewById(R.id.etMontoRecarga)
        val btnConfirmarRecarga: Button = findViewById(R.id.btnConfirmarRecarga)

        val limiteRecarga = 1000000 // Cambia esto al límite máximo deseado
        val minimoRecarga = 10000 // Cambia esto al límite mínimo deseado

        btnConfirmarRecarga.setOnClickListener {
            val montoRecargadoText = etMontoRecarga.text.toString()
            if (montoRecargadoText.isNotBlank()) {
                val montoRecargado = montoRecargadoText.toInt()
                if (montoRecargado in minimoRecarga..limiteRecarga) {
                    val intent = Intent()
                    intent.putExtra("montoRecargado", montoRecargado)
                    setResult(Activity.RESULT_OK, intent)
                    finish()

                    Toast.makeText(this, "Recarga exitosa", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "El monto de recarga debe estar entre $minimoRecarga y $limiteRecarga", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingresa un monto válido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


