package com.example.kotlinavanzado

fun main(){

    var p1: Person = Person("Julio","1234567890",1.70f)

    println(p1.heigh.toString() + " " + p1.alive)

    var d = p1.let {
            k ->
        k.die()
        k.heigh = 1.65f
        k.passport = "0000061001"

        5.4f
        //esta línea no es necesaria, solo se almacenaría en 'd'
        //sin esa línea, la variable de sería de tipo UNIT
    }

    println(p1.heigh.toString() + " " + p1.alive)
    println(d)


    println("\n\n---------------------------------------\n\n")

    var p2: Person = Person("Marco","549849406",1.67f)

    println(p2.heigh.toString() + " " + p2.alive)
    p2.apply {
        this.die()
        heigh=1.70f
        passport="9999879819"
    }
    println(p2.heigh.toString() + " " + p2.alive)
    p2.also {
        it.alive = true
        it.heigh = 1.723f
    }
    println(p2.heigh.toString() + " " + p2.alive)


    println("\n\n---------------------------------------\n\n")

    var p3: Person = Person("Marco","549849406",1.67f).apply {
        this.die()
        heigh=1.11f
        passport="0000000001"
    }
    println(p3.heigh.toString() + " " + p3.alive)


    println("\n\n---------------------------------------\n\n")

    //ELVIS OPERATOR
    var pais: String? = "Rusia"
    //          if <no es nulo> ?: else <es nulo>
    pais = pais?.uppercase() ?: "DESCONOCIDO"
    println("País: $pais")


    var ciudad: String? = null
    ciudad = ciudad?.lowercase() ?: null
    println("Ciudad: $ciudad")


    //LATEINIT NO SE USA CON DATOS PRIMITIVOS
    //lateinit var numero: Int
    lateinit var cadena: String

    //LAZY solo funciona con valores
    val calle: String by lazy { "Nueva" } //se inicializa la primera vez que se use
}