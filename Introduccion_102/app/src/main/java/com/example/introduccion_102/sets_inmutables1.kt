package com.example.introduccion_102

//los sets son conjuntos

fun main(){

    //conjunto de C.I. de cada cliente
    var clientesVIP: Set<Int> = setOf(1234,5678,91011, 4568, 3397)
    println("Cojunto de cliente VIP: \n\n${clientesVIP}")
    println("Número de clientes VIP: ${clientesVIP.size}")

    //clientesVIP.add(234) NO SE PUEDE AÑADIR A UN CONJUNTO INMUTABLE

    if ( clientesVIP.contains(5678) ) println("EL usuario de id: 5678 está registrado") else println("EL usuario de id: 5678 NO existe")



}