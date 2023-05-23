package com.example.holamundo.Arrays

fun main(){

    val weekDays = arrayOf("Lunes","Martes","Miércoles","Jueves","Viernes")


//    println("Size of weekDays: " + weekDays.size )
//    println(weekDays[0])
//    println(weekDays[1])
//    println(weekDays[2])
//    println(weekDays.get(3))//get lanza una excepción sin el índice está fuera de los límites
//    println(weekDays.get(4))


    println(weekDays.indices + "\n")
    for(valor in weekDays.indices){
        print(weekDays[valor] + " ")
    }
    println("\n\n")

    for( (posicion, valor) in weekDays.withIndex() ){
        println("La posición ${posicion}, contiene: ${valor}")
    }
    println("\n\n")

    for(dia in weekDays){
        println("Hoy es: " + dia)
    }


}