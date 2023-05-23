package com.example.holamundo.estrucdecision

fun main(){

    print("Ingresa tu edad: ")
    var edad: Int = readln().toInt()
    val esLunes: Boolean = false

    precioTicket(edad, esLunes)
}

fun precioTicket( edad: Int , esLunes: Boolean){

    when(edad){
        in 0..12 -> print("El precio de su ticket es 12\$")
        in 13..60 -> if(esLunes){
            print("El precio de su ticket es 25\$")
        }else{
            print("El precio de su ticket es 30\$")
        }
        in 60..100 -> print("El precio de su ticket es 20\$")
        else -> print("Error ticket = -1\$")
    }
}

