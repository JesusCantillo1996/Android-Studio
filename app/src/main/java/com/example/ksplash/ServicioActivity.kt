package com.example.ksplash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

class ServicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicio)
        var saldo = intent.getIntExtra("saldo", 0)

        val button = findViewById<Button>(R.id.pagar)
        val cantidad_pagar = findViewById<EditText>(R.id.cantidad_pagar)
        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)

        button.setOnClickListener {
            val cantidad = cantidad_pagar.text.toString()
            val selectedRadioId = radioGroup.checkedRadioButtonId

            if (cantidad.isEmpty() || selectedRadioId == -1) {
                Toast.makeText(this, "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                val cantidadInt = cantidad.toIntOrNull()

                if (cantidadInt != null && cantidadInt > 0) {

                    if (cantidadInt >= 5000) {
                        if (cantidadInt <= saldo) {
                            saldo -= cantidadInt // Resta la cantidad pagada del saldo
                            val intent = Intent()
                            intent.putExtra("nuevoSaldo", saldo)
                            setResult(Activity.RESULT_OK, intent)
                            Toast.makeText(this, "Servicio Pagado Exitosamente.", Toast.LENGTH_SHORT).show()
                            finish() // Cierra ServicioActivity
                        } else {
                            Toast.makeText(this, "No tienes suficiente saldo para pagar el servicio.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "La cantidad mínima de pago es de 5000.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Ingrese una cantidad válida de pago.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
