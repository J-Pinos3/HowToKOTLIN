package com.example.holamundo.estrucdecision


fun main(){

    println( mesAnio(1) )
    println( mesAnio(3) )
    println( mesAnio(5) )
    println( mesAnio(8) )
    println( mesAnio(12) )
    println( mesAnio(4) )
    println( mesAnio(0) )
    println( mesAnio(17) )
}


fun mesAnio(mes: Int): String{

    var mesPalabras:String = ""
    when(mes){

        1 -> mesPalabras="Enero"
        2 -> mesPalabras="Febrero"
        3 -> mesPalabras="Marzo"
        4 -> mesPalabras="Abril"
        5 -> mesPalabras="Mayo"
        6 -> mesPalabras="Junio"
        7 -> mesPalabras="Julio"
        8 -> mesPalabras="Agosto"
        9 -> mesPalabras="Septiembre"
        10 -> mesPalabras="Octubre"
        11 -> mesPalabras="Noviembre"
        12 -> mesPalabras="Diciembre"
        else -> mesPalabras = "Error, el mes ingresado no existe"
    }

    return mesPalabras
}