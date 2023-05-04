package com.example.holamundo

fun main(){

    val numero = 6

    println( "$numero " +esMayor(6) )
    println( "$numero " +esMayor(6-7) )
    println( "$numero " +esMayor(6-6) + "\n\n")

    val palabra: String = "Montaña"
    println(palabra.uppercase())
    println(palabra.lowercase())


}


fun esMayor(valor: Int): String{
    if(valor > 0) {
        return "Es un número positivo"
    }else if(valor < 0) {
        return "Es un número negativo"
    }else{
        return "Es cero"
    }
}