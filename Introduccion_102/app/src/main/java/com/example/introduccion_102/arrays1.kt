package com.example.introduccion_102

fun main(){
    var recibos: Array<String> = arrayOf("luz","agua","internet","teléfono","arriendo")
    var fruticas: Array<String> = arrayOf("banano","pera","kiwi","sandía","naranjilla")


    var matriz = arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(4,5,6),
        intArrayOf(7,8,9)
    )

    println("Índices del arreglo: ${recibos.indices}")

    recorrer_Array(recibos)
    println("\n\n--------\n\n")

    recibos.set(4, "gas")
    recorrer_Array(recibos)

    println("\n\n--------\nFrutas\n")
    recorrer_Array(fruticas)
}

fun recorrer_Array(recibos: Array<String>) {


    //FORMA  1 DE ITERAR SOBRE EL ARRAY
    /*
    for (i in recibos){
        println(i)
    }
    */


    //FORMA  2 DE ITERAR SOBRE EL ARRAY
    /*
    for(i in recibos.indices){
        //print(recibos[i] + "  ")

        //.get() es equivalente al .at() de C++ porque valida los índices
        print(recibos.get(i) + "  ")
    }
    */


    //FORMA  3 DE ITERAR SOBRE EL ARRAY
    /*             ..
              0 <= x <= recibos.size-1
    for( i in 0 .. recibos.size-1 ){
        print(recibos.get(i) + "  ")
    }
    */


    //FORMA  4 DE ITERAR SOBRE EL ARRAY
    /*
    for(k in 0 until recibos.size){
        print(recibos.get(k) + "  ")
    }
    */


    //FORMA  5 DE ITERAR SOBRE EL ARRAY
    for( (posicion, valor) in  recibos.withIndex() ){
        print("$posicion - $valor\n")
    }

}
