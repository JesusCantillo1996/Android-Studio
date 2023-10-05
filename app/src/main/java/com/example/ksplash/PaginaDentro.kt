package com.example.ksplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class PaginaDentro : AppCompatActivity() {
    private var rbVerSaldo:RadioButton?=null
    private var rbIngresar:RadioButton?=null
    private var rbRetirar:RadioButton?=null
    private var rbSalir:RadioButton?=null

    private var tvVerSaldo:TextView?=null

    private var txtIngresar:TextView?=null
    private var txtRetirar:TextView?=null

    private var saldo=1000000.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_dentro)
        rbVerSaldo=findViewById(R.id.rbVerSaldo)
        rbIngresar=findViewById(R.id.rbIngresar)
        rbRetirar=findViewById(R.id.rbRetirar)
        rbSalir=findViewById(R.id.rbSalir)

        tvVerSaldo=findViewById(R.id.tvVerSaldo)

        txtIngresar=findViewById(R.id.txtIngresar)
        txtRetirar=findViewById(R.id.txtRetirar)


    }
    fun accion(view: View){
        tvVerSaldo?.visibility=View.INVISIBLE
        txtIngresar?.visibility=View.INVISIBLE
        txtRetirar?.visibility=View.INVISIBLE
        if(rbVerSaldo?.isChecked()==true){
            tvVerSaldo?.visibility=View.VISIBLE
            tvVerSaldo?.text="Tu Saldo Es: $saldo de pesos "

        }

        if(rbIngresar?.isChecked()==true){
            txtIngresar?.visibility=View.VISIBLE

        }
        if(rbRetirar?.isChecked()==true) {
            txtRetirar?.visibility = View.VISIBLE
        }
        if (rbSalir?.isChecked()==true){
            tvVerSaldo?.visibility = View.VISIBLE
            tvVerSaldo?.text="Nos Vemos Pronto"
        }
    }
    fun btnOk(view: View){
        tvVerSaldo?.visibility=View.INVISIBLE
        txtIngresar?.visibility=View.INVISIBLE
        txtRetirar?.visibility=View.INVISIBLE
        if(rbVerSaldo?.isChecked()==true){
            tvVerSaldo?.text="Tu Saldo Es: $saldo de pesos "
        }
        if(rbIngresar?.isChecked()==true){
            var ingresar=txtIngresar?.text.toString().toDouble()
            saldo=saldo+ingresar
            tvVerSaldo?.text="Tu Saldo Es: $saldo de pesos "
            tvVerSaldo?.visibility=View.VISIBLE
            Toast.makeText(this, "Su operacion se ha realizado de manera exitosa", Toast.LENGTH_LONG ).show()
        }
        if(rbRetirar?.isChecked()==true) {
            var retirar=txtRetirar?.text.toString().toDouble()
            if (saldo-retirar < 0.0){
                tvVerSaldo?.visibility = View.VISIBLE
                tvVerSaldo?.text="Saldo Insuficiente"
            }else{
                saldo=saldo-retirar
                tvVerSaldo?.text="Tu Saldo Es: $saldo de pesos "
                tvVerSaldo?.visibility=View.VISIBLE
                Toast.makeText(this, "Su operacion se ha realizado de manera exitosa", Toast.LENGTH_LONG ).show()
            }
        }
        if (rbSalir?.isChecked()==true){
            tvVerSaldo?.visibility = View.VISIBLE
            tvVerSaldo?.text="Nos Vemos Pronto"
            finish()
            System.exit(0)
        }


    }


}