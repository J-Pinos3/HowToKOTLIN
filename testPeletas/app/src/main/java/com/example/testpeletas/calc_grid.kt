package com.example.testpeletas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class calc_grid : AppCompatActivity() {

    var num1: Double = 0.0
    var num2: Double = 0.0
    var operador: String = ""
    lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc_grid)
        tvTotal = findViewById(R.id.tvTotal)
    }


    public fun onEqualsPressed(view: View){
        num2 = tvTotal.text.toString().toDouble()

        when(operador){
            "/" -> {
                tvTotal.text = "${num1/num2}"
                num1 /= num2
            }

            "x" -> {
                tvTotal.text = "${num1*num2}"
                num1 *= num2
            }


            "-" -> {
                tvTotal.text = "${num1-num2}"
                num1 -= num2
            }


            "+" -> {
                tvTotal.text = "${(num1+num2)}"
                num1 += num2
            }
        }
    } //FIN DE ON_EQUALS_PRESSED



    public fun onOperationSelected(view: View){
        when(view.tag.toString()){
            "C" -> {
                tvTotal.text = ""
                num1 = 0.0
                num2 = 0.0

            }

            "DEL" -> {
                var temp: String = tvTotal.text.toString()
                temp = temp.substring(0,temp.length-1)
                println(temp)
                tvTotal.text = temp

            }


            "DIVI" -> {
                num1 = tvTotal.text.toString().toDouble()
                operador = "/"
                tvTotal.text = ""
            }


            "MULTI" -> {
                num1 = tvTotal.text.toString().toDouble()
                operador = "x"
                tvTotal.text = ""
            }


            "RESTA" -> {
                num1 = tvTotal.text.toString().toDouble()
                operador = "-"
                tvTotal.text = ""
            }


            "SUMA" -> {
                num1 = tvTotal.text.toString().toDouble()
                operador = "+"
                tvTotal.text = ""
            }

        }

    }// FIN DE ON_OPERATION_SELECTED



    public fun onNumberPressed(view: View){

        if(tvTotal.text.toString() == "Total: "){
            tvTotal.text = ""
        }

        when( view.tag.toString() ){

            "0" -> {
                tvTotal.text = tvTotal.text.toString() + "0"
            }

            "1" -> {
                tvTotal.text = tvTotal.text.toString() + "1"
            }

            "2" -> {
                tvTotal.text = tvTotal.text.toString() + "2"
            }

            "3" -> {
                tvTotal.text = tvTotal.text.toString() + "3"
            }

            "4" -> {
                tvTotal.text = tvTotal.text.toString() + "4"
            }

            "5" -> {
                tvTotal.text = tvTotal.text.toString() + "5"
            }

            "6" -> {
                tvTotal.text = tvTotal.text.toString() + "6"
            }

            "7" -> {
                tvTotal.text = tvTotal.text.toString() + "7"
            }

            "8" -> {
                tvTotal.text = tvTotal.text.toString() + "8"
            }

            "9" -> {
                tvTotal.text = tvTotal.text.toString() + "9"
            }

            "POINT" -> {
                if ( !tvTotal.text.contains(".") ){
                    tvTotal.text = tvTotal.text.toString()+ "."
                }
            }

        }
    }// FIN DE ON_NUMBER_PRESSED



}