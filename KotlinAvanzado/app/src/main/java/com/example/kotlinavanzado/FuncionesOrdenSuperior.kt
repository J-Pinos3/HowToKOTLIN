package com.example.kotlinavanzado

//FUNCIONES DE ORDEN SUPERIOR
fun main(){

    val v1 = 44
    val v2 = 8

    println( "La suma de $v1 y $v2 es: ${calculadora(v1,v2, ::suma)}")
    println( "La resta de $v1 y $v2 es: ${calculadora(v1,v2, ::resta)}")
    println( "La multiplicación de $v1 y $v2 es: ${calculadora(v1,v2, ::multiplicacion)}")
    println( "La división de $v1 y $v2 es: ${calculadora(v1,v2, ::division)}")



    println("\n\n----------------------------------------\n\n")

    /*
        EN EXTENSIÓN SERÍA, NO ES CORRECTO HACERLO, DEBERÍA ESTAR DENTRO DE LA CLASE, ES MEJOR CON LAS CLASES POR DEFECTO DE KOTLIN
        public fun Person.checkPolicia(fn: (Float)->Boolean): Boolean{
            return fn(heigh)
        }
    */

    var p1:Person = Person("Lucas","05616849646",1.41f)
    if( p1.checkPolicia( ::poliEnEcuador ) ){
        println("Bienvenido a la policía nacional")
    }else{
        println("Usted no puede ser policía")
    }


    println("\n\n----------------------------------------\n\n")


    //FUNCIÓN LAMBDA
    var funcion = {x:Int, y:Int -> x + y}
    //no s epone :: porque 'funcion' es una variable local, en los otros casos era un función global
    println("15 + 53 = ${calculadora(15,53, funcion)}")


    funcion = {x:Int, y:Int -> x - y}
    println("15 - 53 = ${calculadora(15,53, funcion)}")

    //labda anónima
    println("8 * 23 = ${ calculadora(8,23, {x:Int, y:Int -> x * y} ) } ")

    println("144 / 7 = ${ calculadora(144,7) { x: Int, y: Int -> x / y }} ")//lambda fuera del paréntesis


    println("3 ^ 4 = ${ calculadora(3,4,
        { x, y ->
           var valor = 1
           for(k in 1..y){
               valor *= x
           }
            
            valor
        }
    ) }")


    println("\n\n----------------------------------------\n\n")


    //var array4 = IntArray(10,{5}) el lambda pero adentro
    var array4 = IntArray(10) {5}
    print("Array 4: "); array4.showArray()

    var array5 = IntArray(10, {it * 5} )
    print("Array 5: ");array5.showArray()

    //si uso la flecha, puedo renombrar el paraámetro "it" de la lambda
    var array6 = IntArray(10, {it -> it + 1} )
    print("Array 6: ");array6.showArray()

    var array7 = IntArray(10){
        k -> (k + 1) *5
    }
    print("Array 7: "); array7.showArray()

    var sumaelem = 0
    recorrerArray(array7){
        sumaelem += it
    }
    println("La suma de los elementos de arrat 7 es: $sumaelem")
}



//FUNCIONES DE ORDEN SUPERIOR (reciben otras funciones como parámetros)
//                               ...,función(parámetros)->retorno
private fun calculadora(n1: Int, n2:Int, fn:(x:Int, y:Int)->Int ):Int{

    return fn(n1,n2)

}

private fun suma(n1:Int, n2:Int): Int = n1 + n2

private fun resta(n1:Int, n2:Int): Int = n1 - n2

private fun multiplicacion(n1:Int, n2:Int): Int = n1 * n2

private fun division(n1:Int, n2:Int): Int = n1 / n2


fun poliEnEcuador(altura:Float): Boolean{
    if( altura <= 1.60 ){
        return false
    }
    return true
}


fun recorrerArray(array: IntArray, fn:(Int)->Unit ){
    for(k in array){
        fn(k)
    }
}