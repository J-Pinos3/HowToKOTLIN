package com.example.kotlinavanzado

//FUNCIONES DE EXTENSIÓN
fun main(){

    var usuario: String = "hola, soy yo Jose esto es kotlin"
    println(usuario.noSpaces())


    var array1: Array<Int> = arrayOf(2,4,6,8,10)

    var array2 = IntArray(3)
    array2[0] = 10;  array2[1] = 20; array2[2] = 30
    array2.showArray()

    var array3: IntArray = intArrayOf(3,5,7,9,11)
    array3.showArray()


}

//FUNCIONES DE EXTENSIÓN
//funciones de extensión, permiten añadir métodos a objetos que ya existe, sin tener que heredar
//extensión de los objetos String
private fun String.noSpaces():String{

    return this.replace(" ","")
}



public fun IntArray.showArray(){
    print("[")
    for (k in this){
        print("$k")
    }
    println("]")
}