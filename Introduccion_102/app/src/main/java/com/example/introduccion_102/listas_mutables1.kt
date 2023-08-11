package com.example.introduccion_102

fun main(){

    var bolsa: MutableList<String> = mutableListOf("ARCA CONTINENTAL","ADIDAS","NIKE","AMAZON","META")
    println(bolsa + "\n")

    bolsa.add("La Favorita")
    println(bolsa + "\n")

    bolsa.remove("NIKE")
    println(bolsa + "\n")
}