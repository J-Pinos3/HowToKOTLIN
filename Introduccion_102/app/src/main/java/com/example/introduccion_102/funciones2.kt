package com.example.introduccion_102

var saldo: Float = 156.2f

fun main(){

    mostrar_saludo()

    depositar_dinero(456.6f)

    retirar_dinero(-15f)

    retirar_dinero(saldo1-10.0f)


    println("\n\n-----------\n")

    segundos_hor_min_Seg(4187)

}


fun mostrar_saludo(){
    println("Su saldo es:  $saldo1 $\n")
}

fun depositar_dinero(cantidad: Float = 0.0F){

    if (cantidad < 0) saldo1 += 0 else saldo1 += cantidad
    mostrar_saludo()
}

fun retirar_dinero(cantidad: Float = 0.0F){

    if (cantidad < 0 || cantidad > saldo1){
        println("No se puede retirar esa cantidad de dinero\n")
    }else{
        saldo1 -= cantidad
    }
    mostrar_saludo()
}


fun segundos_hor_min_Seg(segundos: Int ){

    var auxiliar = segundos

    var hor: Int = 0
    var min: Int = 0
    var seg: Int = 0

    hor = auxiliar/3600

    auxiliar %= 3600

    min =auxiliar / 60

    seg = auxiliar % 60

    println("${segundos} equivalen a: ${hor} horas con ${min} minutos y ${seg} segundos")

}