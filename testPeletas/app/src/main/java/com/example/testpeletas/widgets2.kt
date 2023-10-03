package com.example.testpeletas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.ListView
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.SearchView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.testpeletas.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.util.Calendar

class widgets2 : AppCompatActivity() {

    private lateinit var activityContext: Context

    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //          Activity<Nombre de la actividad con la que quiero trabajar>Binding
        //binding = Activity<nombre>Binding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_widgets2)

        activityContext = this

        var cvEjemplo = findViewById<CalendarView>(R.id.cvEjemplo)
        var tvFecha = findViewById<TextView>(R.id.tvFecha)
        
        cvEjemplo.setOnDateChangeListener { cv, year, month, day ->
            //se pone month +1 xq es como un arreglo, los índices, elementos van desde el  0
            var date = "$year/${month+1}/$day"
            tvFecha.text = "Fecha seleccionada: "+date

            //con el binding referencio así al objeto del xml
            //binding.<objeto>.text...etc
            //y ya no necesitaría crearlo con findviewbyid... etc
            //una activity no debe referenciar con binding a otra activity
        }

        //una vez seleccionada una fecha inicial, dependiendo la situación,
        //la siguiente fecha debería ser la misma o mayorv
        var calendar = Calendar.getInstance()
        calendar.set(2026,5,8)
        cvEjemplo.date = calendar.timeInMillis

        //hacemos que el calendario inicie en otro día de la semana
        var dia = cvEjemplo.firstDayOfWeek
        cvEjemplo.firstDayOfWeek = (dia+1)// % 7



        var ratingBarr = findViewById<RatingBar>(R.id.ratingBar)
        var tvValoracion = findViewById<TextView>(R.id.tvValoracion)
        ratingBarr.rating = 3f
        ratingBarr.setOnRatingBarChangeListener { ratingBar, rating, _ ->

            tvValoracion.text = "${rating}/${ratingBarr.numStars}"
        }



        var pbDeterminado = findViewById<ProgressBar>(R.id.pbDeterminado)
        var pbSecondary = findViewById<ProgressBar>(R.id.pbSecondary)
        pbDeterminado.max = 300
        pbDeterminado.progress = 15
        pbSecondary.max=300
        pbSecondary.progress = 0
        pbSecondary.secondaryProgress= 0



        var npEjemplo = findViewById<NumberPicker>(R.id.npEjemplo)
        npEjemplo.minValue = 1
        npEjemplo.maxValue = 60
        npEjemplo.value = 15
        npEjemplo.wrapSelectorWheel = true
        //00 01 02 03 04 ... 09 10
        npEjemplo.setFormatter { i -> String.format("%02d", i) }
        var tvNumberPicker = findViewById<TextView>(R.id.tvNumberPicker)

        npEjemplo.setOnValueChangedListener { numberPicker, oldValue, newValue ->
            tvNumberPicker.text = "Number Picker: Antes${oldValue} - Ahora${newValue}"
        }


        //el avance se pondrá en una corrutina para que el cambio(avance sea visible para el usuario)

        var sbNormal = findViewById<SeekBar>(R.id.sbNormal)
        sbNormal.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    Toast.makeText(activityContext, "Valor modificado por el usuario", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        GlobalScope.launch {
            progressManager(pbDeterminado)
            progressManager(pbSecondary)
            seekbarManager(sbNormal)
        }





    }//ON CREATE

    private fun seekbarManager(sb: SeekBar){
        while(true){
            sleep(100L)
            sb.incrementProgressBy(5)
        }
    }


    private fun progressManager(pb: ProgressBar){
        while(pb.progress < pb.max){
            sleep(100L)
            //pb.progress += 5
            pb.incrementProgressBy(5)//se llena muy rápido, a la velocidad del procesador

            if(pb.id == R.id.pbSecondary){
                pb.incrementSecondaryProgressBy(10)
            }
        }
    }

}