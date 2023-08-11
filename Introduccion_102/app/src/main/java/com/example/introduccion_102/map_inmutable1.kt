package com.example.introduccion_102

fun main(){

    var mapa: Map<Int, String> = mapOf(

        1 to "España",
        2 to "Alemania",
        3 to "Finlandia",
        4 to "Chile",
        5 to "Irlanda"

    )

    println("País: ${mapa.get(3)} \n")

    println(mapa.toString() + "\n")

    println(mapa.keys + "\n")

    println(mapa.values + "\n")

}