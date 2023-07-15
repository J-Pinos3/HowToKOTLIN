package com.example.holamundo.imccalculator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.holamundo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat
import kotlin.math.pow

class ImcCalculatorActivity : AppCompatActivity() {


    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 70
    private var currentAge: Int = 20
    private var currentHeight: Int = 120

    private lateinit var viewMale: CardView   //R.id.cardMale
    private lateinit var viewFemale: CardView
    private lateinit var tvHeigh: TextView
    private lateinit var rangeSlideHeigh: RangeSlider
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView

    private lateinit var tvAge: TextView
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnCalculate: Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        initComponents()
        initListeners()
        initUI()
    }




    private fun initComponents(){
        viewMale = findViewById(R.id.cardMale)
        viewFemale = findViewById(R.id.cardFemale)
        tvHeigh = findViewById(R.id.tvHeigh)
        rangeSlideHeigh = findViewById(R.id.rangeSlideHeigh)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)

        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnSubstractAge = findViewById(R.id.btnSubstractAge)
        tvAge = findViewById(R.id.tvAge)

        btnCalculate = findViewById(R.id.btnCalculate)
    }


    private fun initListeners(){
        viewMale.setOnClickListener {

            changeGender()
            setGenderColor()
        }

        viewFemale.setOnClickListener {

            changeGender()
            setGenderColor()
        }

        rangeSlideHeigh.addOnChangeListener { _, value, _ ->

            //tvHeigh.setText(value.toString())     MI VERSIÓN
            currentHeight = value.toInt()
            tvHeigh.text =  "${currentHeight} cm"

        }


        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }

        btnSubstractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }

        btnPlusAge.setOnClickListener(){
            currentAge += 1
            setAge()
        }


        btnSubstractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }

        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigatetoResult(result)
        }
    }


    private fun navigatetoResult(result: Double) {
        val intent = Intent(this, resultImcActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        var imc = currentWeight.toDouble() / (currentHeight/100.0 * currentHeight/100.0)
        imc = df.format(imc).toDouble()
        Log.i("jose","El imc es: $imc")
        return imc
    }


    private fun setWeight(){
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge(){
        tvAge.text = currentAge.toString()
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }


    private fun setGenderColor(  ){
        viewMale.setCardBackgroundColor( getBackgroundColor(isMaleSelected) )
        viewFemale.setCardBackgroundColor( getBackgroundColor(isFemaleSelected) )
    }

    private fun getBackgroundColor( isSelectedComponent: Boolean ): Int{
        val colorReference = if( isSelectedComponent ){

            R.color.background_component_selected

        }else{
            R.color.background_component

        }

        return ContextCompat.getColor(this, colorReference)
    }


    private fun initUI(){
        setGenderColor()
        setWeight()
        setAge()
    }

}