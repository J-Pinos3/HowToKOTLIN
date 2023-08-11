package com.example.introduccion_102

fun main(){

    var clientes: MutableSet<Int> = mutableSetOf(1234,5678,91011, 4568, 3397)
    println("Clientes: ")
    println(clientes + "\n")


    clientes.add(3358)
    println(clientes + "\n")

    clientes.remove(5678)
    println(clientes + "\n")

}