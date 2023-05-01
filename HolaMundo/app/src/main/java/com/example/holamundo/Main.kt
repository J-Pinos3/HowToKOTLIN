package com.example.holamundo

fun main(){
    print("Hola Mundo, esto es kotlin\n")
    // val se usa oara una variable cuyo valor no cambia nunca
    //var se usa para una variable cuyo valor puede cambiar

    val edad: Int = 10
    //edad= 11 ERROR!! no se puede reasignar
    println("La edad es: " + "$edad")//imprime un salto de línea
    print("La edad es: " + "$edad" + "\n\n\n")

    var valor: Int = 45
    val num_byte:Byte = 2;
    val num_entero:Int = 15;
    val num_float: Float = 15.63f;
    val num_double: Double = 4.52;
    val cadena: String = "Hola, soy una cadena de texto"

    println("\'Variable\' valor: " + valor)
    println("Valor byte: " + num_byte);
    println("Valor entero: " + "$num_entero");
    println("Valor float: " + num_float);
    println("Valor double: " + "$num_double");
    println("Valor string: " + cadena)

    valor += 10
    println("\'Variable\' valor: $valor")
}