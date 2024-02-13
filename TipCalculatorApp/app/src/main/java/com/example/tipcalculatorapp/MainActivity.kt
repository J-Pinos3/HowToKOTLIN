package com.example.tipcalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import java.lang.NumberFormatException
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    companion object{
        private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance()
        private val percentFormat: NumberFormat = NumberFormat.getPercentInstance()
    }



    private var billAmount: Double = 0.0
    private var percent: Double = 0.15

    private lateinit var amountTextView: TextView
    private lateinit var percentTextView: TextView
    private lateinit var tipTextView: TextView
    private lateinit var totalTextView: TextView

    private lateinit var amountEditText: EditText
    private lateinit var percentSeekBar: SeekBar

    private var seekBarListener = object: OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            percent = progress / 100.0
            calculate()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {  }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {  }

    }


    private var amountEditTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            try {
                billAmount = s.toString().toDouble() / 100.0
                amountTextView.text = currencyFormat.format(billAmount)

            }catch (e: NumberFormatException){
                amountTextView.text = ""
                billAmount = 0.0
            }

            calculate()
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        tipTextView.text = currencyFormat.format(0)
        totalTextView.text = currencyFormat.format(0)


        amountEditText.addTextChangedListener(amountEditTextWatcher)
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener)

    }//ON CREATE


    private fun calculate(){
        percentTextView.text = percentFormat.format(percent)

        var tip = billAmount * percent
        var total: Double = billAmount + tip

        tipTextView.text = currencyFormat.format(tip)
        totalTextView.text = currencyFormat.format(total)

    }



    private fun initUI() {
        amountTextView = findViewById(R.id.amountTextView)
        percentTextView = findViewById(R.id.percentTextView)
        tipTextView = findViewById(R.id.tipTextView)
        totalTextView = findViewById(R.id.totalTextView)

        amountEditText = findViewById(R.id.amountEditText)
        percentSeekBar = findViewById(R.id.percentSeekBar)
    }


}