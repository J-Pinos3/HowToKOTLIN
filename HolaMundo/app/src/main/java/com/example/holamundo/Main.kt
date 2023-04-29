package com.example.holamundo

fun main(){
    print("Hola Mundo, esto es kotlin\n")
    // val se usa oara una variable cuyo valor no cambia nunca
    //var se usa para una variable cuyo valor puede cambiar

    val edad: Int = 10
    //edad= 11 ERROR!! no se puede reasignar
    println("La edad es: " + "$edad")//imprime un salto de línea
    print("La edad es: " + "$edad")


}