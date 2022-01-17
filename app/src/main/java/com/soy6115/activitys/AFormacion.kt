package com.soy6115.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.soy6115.R
import com.soy6115.databinding.ActivityExperienciaBinding
import com.soy6115.databinding.ActivityFormacionBinding
import com.soy6115.utilidad.Constantes

class AFormacion : AppCompatActivity() {


    private lateinit var binding: ActivityFormacionBinding
    private var swMenuDesplegado = false

    private lateinit var menuDesplegable : LinearLayout
    private lateinit var btnDesplegable : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializarVariables()
        activarBotones()
    }

    private fun inicializarVariables() {
        menuDesplegable = binding.llMenu
        btnDesplegable = binding.ibMenuDesplegable
    }

    private fun activarBotones() {
        btnDesplegable.setOnClickListener {
            controlMenu()
        }

        val liApps = binding.tvBTNApps
        liApps.setOnClickListener {
            irA(Constantes.APPS)
        }

        val liDisenio = binding.tvBTNDisenio
        liDisenio.setOnClickListener {
            irA(Constantes.DISENIO)
        }

        val liOtrosDatos = binding.tvBTNOtrosDatos
        liOtrosDatos.setOnClickListener {
            irA(Constantes.OTROS_DATOS)
        }

        val liSobreMi = binding.tvBTNSobreMi
        liSobreMi.setOnClickListener {
            irA(Constantes.SOBRE_MI)
        }

        val liExperiencia = binding.tvBTNExperiencia
        liExperiencia.setOnClickListener {
            irA(Constantes.EXPERIENCIA)
        }

    }

    private fun controlMenu() {
        if (swMenuDesplegado) {
            btnDesplegable.background =
                ResourcesCompat.getDrawable(resources, R.drawable.btn_menu_abre, null)
            menuDesplegable.visibility = View.GONE
        } else {
            btnDesplegable.background =
                ResourcesCompat.getDrawable(resources, R.drawable.btn_menu_cerrar, null)
            menuDesplegable.visibility = View.VISIBLE
        }
        swMenuDesplegado = !swMenuDesplegado
    }

    private fun irA(codigo: Int) {
        val intent = when (codigo) {
            Constantes.EXPERIENCIA -> Intent(this, AExperiencia::class.java)
            Constantes.FORMACION -> Intent(this, AFormacion::class.java)
            Constantes.APPS -> Intent(this, AApps::class.java)
            Constantes.DISENIO -> Intent(this, ADisenio::class.java)
            Constantes.OTROS_DATOS -> Intent(this, AOtrosDatos::class.java)
            Constantes.SOBRE_MI -> Intent(this, ASobreMi::class.java)
            else -> Intent(this, AExperiencia::class.java)
        }
        startActivity(intent)
    }

}