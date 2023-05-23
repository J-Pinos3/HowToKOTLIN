package com.example.holamundo.Listas

fun main(){

    inmutableLista()



}

fun inmutableLista(){
    //es inmutable
    val readOnly: List<String> = listOf("Julie","Andrea","Esteban","Daniel","Marco", "Antonio", "Byron")
    println(readOnly.size)
    println(readOnly.toString())//ES IGUAL A: println(readOnly)

    println("Primer elemento: " + readOnly.first())
    println("Último elemento: " + readOnly.last())

}