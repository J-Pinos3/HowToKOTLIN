package com.example.holamundo.estrucdecision


fun main(){

    var peso: Double = 4.65
    val nombrePlaneta: String = "Urano"

    if( nombrePlaneta.lowercase() == "mercurio" ){
        val merc: Double = 0.4155
        peso *= merc

    }else if( nombrePlaneta.lowercase() == "venus" ){
        val venus: Double = 0.4155
        peso *= venus

    }else if( nombrePlaneta.lowercase() == "tierra" ){
        val tie: Double = 1.0
        peso *= tie

    }else if( nombrePlaneta.lowercase() == "luna" ){
        val luna: Double = 0.166
        peso *= luna

    }else if( nombrePlaneta.lowercase() == "marte" ){
        val mar: Double = 0.357
        peso *= mar

    }else if( nombrePlaneta.lowercase() == "jupiter" ){
        val jupi: Double = 2.5374
        peso *= jupi

    }else if( nombrePlaneta.lowercase() == "saturno" ){
        val sat: Double = 1.0677
        peso *= sat

    }else if( nombrePlaneta.lowercase() == "urano" ){
        val ura: Double = 0.8947
        peso *= ura

    }else if( nombrePlaneta.lowercase() == "neptuno" ){
        val nep: Double = 1.1794
        peso *= nep

    }else if( nombrePlaneta.lowercase() == "pluton" ){
        val plu: Double = 0.0899
        peso *= plu

    }


    println("| Su peso en otro planeta es |\n")
    println("$nombrePlaneta    ->    $peso")




}