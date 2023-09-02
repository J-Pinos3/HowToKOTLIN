package com.example.kotlinavanzado
/*
Crea una funcion de orden superior que reciba un int y una funcion que reciba un int y devuelva un boolean
Llama a dicha funcion de orden superior con expresiones lambdas que hagan lo siguientes
- comprobar si el numero es un numero par
- comprobar si el numero es un numero primo
- comprobar si el numero es un numero guay (un numero es guay cuando es el resultado de la suma de numeros consecutivos desde el 1.
Por ejemplo el número 10 es guay porque 1+2+3+4 = 10. El 15 también es guay porque 1+2+3+4+5 = 15.
El numero 8 no es guay porque no hay ninguna posible combinación de sumas consecutivas desde el 1 que resulte en 8)

*/

fun main(){

    devuelveResultado(10, ::esGuay)

    println("\n")

    devuelveResultado(20){ x: Int -> x % 2 == 0 } // es par

    println("\n")

    devuelveResultado(21){ x: Int -> x % 2 == 0 } // es impar

    println("\n")

    devuelveResultado(19){x: Int ->
        var suma:Int = 0
        var i:Int = 1

        while(i <= x){

            if(x % i == 0){
                suma += 1
            }

            i++
        }


        //suma == 2  RESUMIDO
        //SI SUMA == 2 QUIERE DECIRF QUE HAY 2 DIVISORES
        if (suma == 2) true
        else false

    }


}


private fun devuelveResultado( n1: Int, myFunction:(x:Int)->Boolean ): Unit{
    println("Número: $n1\n")

    if( myFunction(n1) ) {
        println("la condición es verdadera")
    }else{
        println("la condición es falsa")
    }

}

private fun esGuay(n:Int): Boolean{
    var i: Int = 1
    var suma :Int = 0
    var esguay: Boolean = false

    while(i <= n && esguay == false){

        if(suma == n){
            esguay = true
            break
        }else{
            esguay = false
            suma += i
        }

        i+=1
    }


    return esguay
}