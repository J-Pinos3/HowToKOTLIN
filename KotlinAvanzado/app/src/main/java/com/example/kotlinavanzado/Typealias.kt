package com.example.kotlinavanzado
import java.lang.Exception

typealias aliasObjeto = Subclases.Anidada

typealias alasDato = MutableMap<Int, ArrayList<String> >

class IllegalPasswordEsception(message:String ): Exception(message)

fun main(){

    var sc = Subclases()
    println(sc.presentar())


    var ani = Subclases.Anidada()
    println(ani.presentar())

    var interna = Subclases().Interna()//al ser una clase interna, necesita que la clase "padre" se cree primero
    println(interna.presentar())

    var anidada = aliasObjeto()
    println(anidada.presentar())


    println("\n\n----------------------------------------------\n\n")

    var saludos: alasDato = mutableMapOf()
    saludos[0] = arrayListOf("Hola", "Adios")
    saludos[1] = arrayListOf("Hi","Bye")
    println(saludos)
    //desestructuración de saludos
    for((id, palabras) in saludos){
        println("K = $id  V = $palabras")
    }

    println("\n\n----------------------------------------------\n\n")

    //DESESTRUCTURACIONES
    var planet1:Planeta = Planeta("Plutón",24622.0F, 11.15F, "Urbain Le Verrier")
    println("planeta 1: ${planet1}")
    //objeto.propiedad TAMBIÉN ES POSIBLE

    var(name2, redius2, gravity2, discoverer2) = Planeta("Tierra",6370.0F, 9.8F, "Humanidad")
    println("planeta 2: ${name2}, ${redius2}, ${gravity2}, ${discoverer2}")

    //omito un parámetro
    var(name3, redius3, gravity3) = Planeta("Venus",6501.8F, 8.87F, "Galileo Galilei")
    println("planeta 3: ${name3}, ${redius3}, ${gravity3}")

    var(name4, _, _, discoverer4) = Planeta("Júpiter",71492F, 24.79F, "Griegos")
    println("planeta 4: ${name4}, ${discoverer4}")


    println("\n\n----------------------------------------------\n\n")


    try {
        println("5/0 = ${5/0}")
    }catch (e: ArithmeticException){
        println(e.message + " NO SE PUEDE DIVIDIR PARA 0.")
    }

    println("\n")

    var res1 = value_try(10,2)
    println(res1)

    var res2 = value_try(8,0)
    println(res2)


    val password: String = "1234"
    if(password.length < 6){
        //throw Exception("Password muy corta")
        throw IllegalPasswordEsception("Password muy corta")
    }else{
        println("Password segura")
    }


}





fun value_try(a: Int, b: Int): Any{
    var resultado = try {
        println("División a/b = ${a/b}")

        a/b
    }catch (e: ArithmeticException){
        println(e.toString())

        "División no permitida"
    }

    return resultado
}