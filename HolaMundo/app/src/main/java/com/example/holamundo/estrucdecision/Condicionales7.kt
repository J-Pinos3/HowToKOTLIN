package com.example.holamundo.estrucdecision

fun main(){
    println( notaPalabras(0) )
    println( notaPalabras(3) )
    println( notaPalabras(6) )
    println( notaPalabras(9) )
    println( notaPalabras(-5) )
    println( notaPalabras(11) )
}

fun notaPalabras(nota: Int): String{

    var estado:String = ""
    when(nota){
        /*
        0,1,2,3 -> estado = "Reprobado"
        4,5,6-> estado = "Supletorio"
        7,8,9,10 -> estado = "Aprobado"
        else -> estado = "Nota errónea"
        */

        /*is-condition y existe in-condition*/
        /*is Int -> CÓDIGO*/
        /*!is   !in  operador de negación*/

        in 0..4 -> estado = "Reprobado"
        in 5..10 -> estado = "Aprobado"
        else -> estado = "Nota incorrecta"
    }

    return estado
}