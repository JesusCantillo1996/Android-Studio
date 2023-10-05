package com.example.ksplash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class HistorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val historialAcciones = intent.getStringArrayListExtra("historialAcciones")
        val listView: ListView = findViewById(R.id.listView)

        if (historialAcciones != null) {
            val adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                historialAcciones
            )
            listView.adapter = adapter
        }
        val btnVolverAlMenu: Button = findViewById(R.id.btnVolver)
        btnVolverAlMenu.setOnClickListener {
            // Crear un Intent para volver a MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}
