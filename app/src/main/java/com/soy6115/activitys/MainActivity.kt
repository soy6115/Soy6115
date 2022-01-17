package com.soy6115.activitys

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.soy6115.R
import com.soy6115.databinding.ActivityMainBinding
import com.soy6115.json.Vida
import com.soy6115.utilidad.Constantes
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    // necesito agregar la dependencia
    // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    private val scope = MainScope()
    private var vida : Vida? = null

    override fun onDestroy() {
        super.onDestroy()
        // esto la hacemos para parar la rutina
        scope.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animacionTexto()

        val swCarga = cargarJson()
        if (swCarga) {
            activarBotones()
        } else {
            Toast.makeText(this, "Disculpa las molestias, pero ahora mismo tenemos un " +
                    "problema con la carga de información. Estamos trabajando en ello.",
                    Toast.LENGTH_LONG).show()
        }

    }



    private fun activarBotones() {
        val btnFormacion = binding.ibFormacion
        btnFormacion.setOnClickListener {
            irA(Constantes.FORMACION)
        }

        val btnExperiencia = binding.ibExperiencia
        btnExperiencia.setOnClickListener {
            irA(Constantes.EXPERIENCIA)
        }

        val btnApps = binding.ibApps
        btnApps.setOnClickListener {
            irA(Constantes.APPS)
        }

        val btnDisenio = binding.ibDisenio
        btnDisenio.setOnClickListener {
            irA(Constantes.DISENIO)
        }

        val btnSobreMi = binding.ibSobreMi
        btnSobreMi.setOnClickListener {
            irA(Constantes.SOBRE_MI)
        }

        val btnOtrosDatos = binding.ibOtrosDatos
        btnOtrosDatos.setOnClickListener {
            irA(Constantes.OTROS_DATOS)
        }

        val btnGitHub = binding.ibGitHub
        btnGitHub.setOnClickListener {
            abrir(Constantes.GITHUB)
        }

        val btnLinkedin = binding.ibLinkedin
        btnLinkedin.setOnClickListener {
            abrir(Constantes.LINKEDIN)
        }

        val btnMail = binding.ibMail
        btnMail.setOnClickListener {
            contactarXMail()
        }

        val btnCV = binding.ibCV
        btnCV.setOnClickListener {
            mostrarCV()
        }

    }



    fun irA(codigo: Int) {
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

        //  ----------------------- ANIMACIONES ENTRE ACTIVIDADES ----------------------------------
        //Sacado de este video
        //https://www.youtube.com/watch?v=0s6x3Sn4eYo
        //https://gist.github.com/codinginflow/a2b08fb50b0971923176a4e0c062971a

        //overridePendingTransition(R.anim.slide_in_der, R.anim.slide_out_der)


    }

    private fun abrir(codigo: Int) {
        val url = if (codigo == Constantes.GITHUB)
            "https://github.com/soy6115"
        else
            "https://es.linkedin.com/in/gustavo-peinado-turienzo-95b593220/"

        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun contactarXMail(){
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
            "mailto", "g.peinadoturienzo@gmail.com", null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacto APP 6115")
        startActivity(Intent.createChooser(emailIntent, "Prueba"))
    }

    private fun mostrarCV() {
        val url = "https://github.com/soy6115/cv/blob/main/cvGustavoPeinado.pdf"
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        //intent.setDataAndType(uri, "application/pdf")
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Disculpa las molestias, no hemos podido abrir el curriculum", Toast.LENGTH_LONG).show()
        }

    }

    private fun cargarJson() : Boolean{
        // necesito agregar la dependencia
        // implementation 'com.beust:klaxon:5.5'
        vida = Klaxon().parse<Vida>(resources.openRawResource(R.raw.vida))
        if (vida!=null)
            return true
        return false
    }


    private fun animacionTexto() {
        scope.launch {
            // si esto lo hciiera en una funcion fuera, seria private suspend fun
            val cajaTexto1 = binding.tvH1p1
            val cajaTexto2 = binding.tvH1p2
            val texto1 = resources.getString(R.string.tv_H1p1)
            val texto2 = resources.getString(R.string.tv_H1p2)

            for (i in 1..texto1.length) {
                delay(100)
                cajaTexto1.text = texto1.subSequence(0,i)
            }
            for (i in 1..texto2.length) {
                delay(100)
                cajaTexto2.text = texto2.subSequence(0,i)
            }
        }
    }

}