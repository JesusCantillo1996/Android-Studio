package com.example.ksplash


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MenuActivity : AppCompatActivity(){
    private lateinit var sharedPreferences: SharedPreferences
    private var saldo = 0
    private val historialAcciones = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        cargarSaldoDesdeSharedPreferences()
        cargarHistorialDesdeSharedPreferences()

        val btnConsignar: Button = findViewById(R.id.btnConsignar)
        btnConsignar.setOnClickListener {
            val intent: Intent = Intent(this, ConsignarActivity::class.java)
            intent.putExtra("saldo", saldo)
            startActivityForResult(intent, 2)
        }

        val btnRecargar: Button = findViewById(R.id.btnRecargar)
        btnRecargar.setOnClickListener {
            val intent: Intent = Intent(this, RecargarActivity::class.java)
            startActivityForResult(intent, 1)
        }

        val btnServicios: Button = findViewById(R.id.btnServicios)
        btnServicios.setOnClickListener {
            val intent = Intent(this, ServicioActivity::class.java)
            intent.putExtra("saldo", saldo)
            startActivityForResult(intent, 4)
        }

        val btnSalir: Button = findViewById(R.id.btnSalir)
        btnSalir.setOnClickListener {
            // Restaura el saldo y el historial al presionar "Salir"
            saldo = 0
            historialAcciones.clear()
            guardarSaldoEnSharedPreferences()
            guardarHistorialEnSharedPreferences()

            finishAffinity()
            Toast.makeText(this, "Nos Vemos Pronto", Toast.LENGTH_SHORT).show()
            finish()
        }

        val btnRetirar: Button = findViewById(R.id.btnRetirar)
        btnRetirar.setOnClickListener {
            val intent: Intent = Intent(this, RetirarActivity::class.java)
            intent.putExtra("saldo", saldo)
            startActivityForResult(intent, 3)
        }

        val btnHistorial: Button = findViewById(R.id.btnHistorial)
        btnHistorial.setOnClickListener {
            val intent = Intent(this, HistorialActivity::class.java)
            intent.putStringArrayListExtra("historialAcciones", historialAcciones)
            startActivity(intent)
        }

        val rbDisponible: RadioButton = findViewById(R.id.rbDisponoble)
        val tvDisponible: TextView = findViewById(R.id.tvDisponible)
        var isChecked = false

        rbDisponible.setOnClickListener {
            isChecked = !isChecked

            if (isChecked) {
                tvDisponible.text = "Tu Saldo Es: $$saldo de pesos"
                tvDisponible.visibility = View.VISIBLE
            } else {
                tvDisponible.visibility = View.INVISIBLE
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val montoRecargado = data?.getIntExtra("montoRecargado", 0) ?: 0
            saldo += montoRecargado
            registrarAccion("Recarga de saldo: $$montoRecargado")

        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            val montoConsignado = data?.getIntExtra("montoConsignado", 0) ?: 0
            saldo -= montoConsignado
            registrarAccion("Consignación: $$montoConsignado")

        } else if (requestCode == 4 && resultCode == RESULT_OK) {
            val nuevoSaldo = data?.getIntExtra("nuevoSaldo", 0) ?: 0
            saldo = nuevoSaldo
            registrarAccion("Pago de servicio: $$nuevoSaldo")

        } else if (requestCode == 3 && resultCode == RESULT_OK) {
            val montoRetirado = data?.getIntExtra("montoRetirado", 0) ?: 0
            saldo -= montoRetirado
            registrarAccion("Retiro: $$montoRetirado")

        }
    }

    override fun onStop() {
        super.onStop()
        guardarSaldoEnSharedPreferences()
        guardarHistorialEnSharedPreferences()
    }

    private fun actualizarSaldo() {
        val tvDisponible: TextView = findViewById(R.id.tvDisponible)
        tvDisponible.text = "Tu Saldo Es: $$saldo de pesos"
        tvDisponible.visibility = View.VISIBLE
    }

    private fun registrarAccion(accion: String) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val fechaHora = dateFormat.format(Date())
        historialAcciones.add("$fechaHora - $accion")
    }

    private fun cargarSaldoDesdeSharedPreferences() {
        saldo = sharedPreferences.getInt("saldo", 0)

    }

    private fun guardarSaldoEnSharedPreferences() {
        val editor = sharedPreferences.edit()
        editor.putInt("saldo", saldo)
        editor.apply()
    }

    private fun cargarHistorialDesdeSharedPreferences() {
        historialAcciones.clear()
        val historial = sharedPreferences.getStringSet("historialAcciones", HashSet()) ?: HashSet()
        historialAcciones.addAll(historial)
    }

    private fun guardarHistorialEnSharedPreferences() {
        val editor = sharedPreferences.edit()
        editor.putStringSet("historialAcciones", HashSet(historialAcciones))
        editor.apply()
    }
}





