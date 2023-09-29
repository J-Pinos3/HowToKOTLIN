package com.example.testpeletas

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.testpeletas.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

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



        //var rgVacaciones = findViewById<RadioGroup>(R.id.rgVacaciones)
        var rgVacaciones = findViewById<View>(R.id.rgVacaciones) as RadioGroup

        //checkeo el primer hijo, por tanto ese se marca y no el que está en el xml
        var rb = rgVacaciones.getChildAt(0) as RadioButton
        rgVacaciones.check(rb.id)



        var cbSeguro = findViewById<CheckBox>(R.id.cbSeguro)
        cbSeguro.isEnabled = true
        cbSeguro.isChecked = true



        var tgNormal = findViewById<ToggleButton>(R.id.tgNormal)
        tgNormal.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                Toast.makeText(this, "Toggle Button Activado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Toggle Button Desactivado", Toast.LENGTH_SHORT).show()
            }
        }



        var switchNormal = findViewById<Switch>(R.id.switchNormal)
        switchNormal.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                Toast.makeText(this, "Switch Activado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Switch  Desactivado", Toast.LENGTH_SHORT).show()
            }
        }

        var floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            Toast.makeText(this, "Floating Button Activado", Toast.LENGTH_SHORT).show()
        }

    }//FIN DE ONCREATE




    public fun onRadioButtonClicked(view: View){
        //El atributo onClick envía un View, la función debe recibirlo
        if(view is RadioButton){

            var ischecked = view.isChecked
            when(view.id){
                R.id.rbPlaya -> {
                    if(ischecked){
                        Toast.makeText(this, "Usted viajará a la ${view.text}", Toast.LENGTH_SHORT).show()
                    }
                }

                R.id.rbMontana -> {
                    if(ischecked){
                        Toast.makeText(this, "Usted viajará a la ${view.text}", Toast.LENGTH_SHORT).show()
                    }
                }
                /*
                R.id.rbSelva -> {
                    if(ischecked){
                        Toast.makeText(this, "Usted viajará a la ${view.text}", Toast.LENGTH_SHORT).show()
                    }
                }
                */



            }//when

        }//if
    }//FIN DEL MÉTODO ONRADIOBUTTONCLICKED SOLO PARA RADIOBUTTON



    public fun onCheckBoxClicked(view: View){
        //El atributo onClick envía un View, la función debe recibirlo
        if(view is CheckBox){

            var ischecked = view.isChecked
            when(view.getId()){
                R.id.cbSeguro -> {
                    if(ischecked){
                        Toast.makeText(this, "Usted ha activado un: ${view.text}", Toast.LENGTH_SHORT).show()
                    }
                }

                R.id.cbCancelable -> {
                    if(ischecked){
                        Toast.makeText(this, "Usted ha decidido: ${view.text}", Toast.LENGTH_SHORT).show()
                    }
                }

            }//when

        }//if
    }//FIN DEL MÉTODO onCheckBoxClicked SOLO PARA checkbox

}