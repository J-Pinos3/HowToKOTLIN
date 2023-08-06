package com.example.introduccion_102

fun main(){

    var pin: Int = 1234
    var intentos: Int = 0

    var clave_ingresada: Int = 1232

    do{
        println("Ingrese el pin: ")
        //println("Clave ingresada: " + clave_ingresada++)
        println("Clave ingresada: ${clave_ingresada++}" )

        //intentos++;

    }while ( intentos < 3 &&  clave_ingresada != pin)

    println("Intentos realizados: $intentos")
    if(intentos >= 3){
        println("Tarjeta bloqueada")
    }
}