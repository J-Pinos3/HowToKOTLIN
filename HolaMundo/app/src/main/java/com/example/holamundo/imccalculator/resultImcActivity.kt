package com.example.holamundo.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.example.holamundo.R
import com.example.holamundo.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY
import org.w3c.dom.Text

class resultImcActivity : AppCompatActivity() {

    private lateinit var tvResultIMC: TextView
    private lateinit var tvIMCNumber: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnReCalc: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)

        var result:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initComponents()
        initUI(result)
        initListeners()

    }

    private fun initListeners() {
        btnReCalc.setOnClickListener {
            onBackPressed()
        }
    }


    private fun initUI(result: Double) {

        tvIMCNumber.text = result.toString()

        when(result){
            //PESO BAJO
            in 0.00..18.50 -> {
                tvResultIMC.text = getString(R.string.title_bajo_peso)
                tvResultIMC.setTextColor( ContextCompat.getColor(this, R.color.yellow_cake) )
                tvDescription.text = getString(R.string.descripcion_bajo_peso)
            }

            //PESO NORMAL
            in 18.51..24.99 -> {
                tvResultIMC.text = getString(R.string.title_normal)
                tvResultIMC.setTextColor( ContextCompat.getColor(this, R.color.green_cake) )
                tvDescription.text = getString(R.string.descripcion_normal)
            }

            //PESO OBESO
            in 25.00..29.99 -> {
                tvResultIMC.text  = getString(R.string.title_obeso)
                tvResultIMC.setTextColor( ContextCompat.getColor(this, R.color.orange_cake) )
                tvDescription.text = getString(R.string.descripcion_obeso)
            }

            //PESO OBESIDAD MÓRBIDA
            in 30.00..99.00 -> {
                tvResultIMC.text = getString(R.string.title_obesidad_morbida)
                tvResultIMC.setTextColor( ContextCompat.getColor(this, R.color.red_cake) )
                tvDescription.text = getString(R.string.descripcion_obesidad_morbida)
            }

            else -> {//ERROR
                tvResultIMC.text = getString(R.string.error)
                tvResultIMC.setTextColor( ContextCompat.getColor(this, R.color.red_cake) )
                tvIMCNumber.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }

        }
    }


    private fun initComponents() {

        tvResultIMC = findViewById(R.id.tvResultIMC)
        tvIMCNumber = findViewById(R.id.tvIMCNumber)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalc = findViewById(R.id.btnReCalc)
    }


}