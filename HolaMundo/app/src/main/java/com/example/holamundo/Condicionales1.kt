package com.example.holamundo

fun main(){

    var anio: Int = 2008
    var nombre: String = "Andrés"

    if(anio < 0){
        println("Los años no son valores negativos")
    }

    if(anio > 2010){
        println("Sobrevivimos el fin del mundo :)")
    }

    if( esBisiesto(anio) ){
        println("$anio no es bisiesto")
    }else{
        println("${anio} es bisiesto")
    }

    print("\n\nOtro tipo de if-else v.1 ")
    println( if (esBisiesto(anio) == true) "$anio no es bisiesto" else "${anio} es bisiesto" )
    print("\n\nOtro tipo de if-else v.2 ")

    var resultBisiesto= when(esBisiesto(anio)){
        true -> "$anio no es bisiesto"
        false -> "$anio es bisiesto"
    }
    println(resultBisiesto)


    /*----------------------------------------------------------*/
    println("\n\n\n")
    if(nombre == "Andrés"){
        println("Bienvenido mijo")
    }

}



fun esBisiesto(anio: Int): Boolean{
    //filtramos los que no son bisiestos
    return  (anio %4 != 0) || (anio % 100 == 0  &&  anio % 400 !=0)
}