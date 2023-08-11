package com.example.introduccion_102

fun main(){

    //al ser inmutable, la lista no se puede modificar
    var divisas: List<String> = listOf("ARS","USD","EUR","JPN","CAD","DKK","GBP","NZD")
    println(divisas)


    recorrerLista(divisas)




}


fun recorrerLista(lista: List<String>){

    for ( k in lista.indices) {
        print(lista[k] + " ")
    }

}