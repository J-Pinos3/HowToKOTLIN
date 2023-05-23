package com.example.holamundo.estrucdecision

fun main(){

    val morning: Int = 15;
    val night: Int = 536;

    println( printNotifications(morning) )
    println( printNotifications(night) )

}

fun printNotifications( numMensajes: Int) :String =
    if (numMensajes < 100)
        "Tiene $numMensajes notificaciones."
    else
        "Teléfono lleno, tiene +99 notificaciones."

