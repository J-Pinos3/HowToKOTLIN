package com.example.testpeletas

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.testpeletas.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class buttons : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)

        var btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        btnIniciarSesion.setOnClickListener {
            Toast.makeText(this, "Botón de Iniciar Sesión Pulsado", Toast.LENGTH_SHORT).show()
        }


        var imageButton = findViewById<ImageButton>(R.id.imageButton)
        imageButton.setOnClickListener{
            Toast.makeText(this, "Botón de Imágen Pulsado", Toast.LENGTH_SHORT).show()
        }


        var cgCondiciones = findViewById<ChipGroup>(R.id.cgCondiciones)
        var chip: Chip

        for(i in 0 until cgCondiciones.childCount){
            chip = cgCondiciones.getChildAt(i) as Chip //getChildAt me devuelve un View, así que hago un cast a Chip
            chip.textAlignment = View.TEXT_ALIGNMENT_CENTER

            chip.setOnCloseIconClickListener {
                 cgCondiciones.removeView(it)
            }

            chip.setOnClickListener{
                var aux = it as Chip
                Toast.makeText(this, "${aux.text} Pulsado", Toast.LENGTH_SHORT).show()
            }
        }



        //AÑADIR UN CHIP A UN CHIPGROUP
        val chipNew = Chip(this)
        chipNew.text = "Nueva Opción"
        chipNew.chipIcon = ContextCompat.getDrawable(this, R.drawable.ic_information)
        chipNew.isChipIconVisible = true
        chipNew.isCloseIconVisible = true

        chipNew.isClickable = true
        chipNew.isCheckable = false

        chipNew.setOnClickListener {
            var aux = it as Chip
            Toast.makeText(this, "${it.text} Pulsado", Toast.LENGTH_SHORT).show()
        }

        cgCondiciones.addView(chipNew as View)
        chipNew.setOnCloseIconClickListener { cgCondiciones.removeView(chipNew as View) }



    }
}