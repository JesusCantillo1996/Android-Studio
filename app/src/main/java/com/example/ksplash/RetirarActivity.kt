package com.example.ksplash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class RetirarActivity : AppCompatActivity() {
    private lateinit var etMontoRetiro: EditText
    private lateinit var btnGenerarNumero: Button
    private lateinit var tvNumeroAleatorio: TextView
    private lateinit var btnConfirmarRetiro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retirar)

        etMontoRetiro = findViewById(R.id.etMontoRetiro)
        btnGenerarNumero = findViewById(R.id.btnGenerarNumero)
        tvNumeroAleatorio = findViewById(R.id.tvNumeroAleatorio)
        btnConfirmarRetiro = findViewById(R.id.btnConfirmarRetiro)

        val saldo = intent.getIntExtra("saldo", 0)

        btnGenerarNumero.setOnClickListener {
            val numeroAleatorio = Random.nextInt(100000, 1000000)
            tvNumeroAleatorio.text = "Número de confirmación: $numeroAleatorio"
            tvNumeroAleatorio.visibility = View.VISIBLE
            btnConfirmarRetiro.visibility = View.VISIBLE
        }

        btnConfirmarRetiro.setOnClickListener {
            val montoRetiro = etMontoRetiro.text.toString().toDoubleOrNull() ?: 0.0
            if (montoRetiro > 0 && montoRetiro <= saldo) {
                val numeroConfirmacion =
                    tvNumeroAleatorio.text.toString().split(":")[1].trim().toIntOrNull()
                if (numeroConfirmacion != null) {
                    val intent = Intent()
                    intent.putExtra("montoRetirado", montoRetiro.toInt())
                    setResult(Activity.RESULT_OK, intent)
                    Toast.makeText(this, "Retiro exitoso", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Número de confirmación incorrecto.", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Saldo insuficiente.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


