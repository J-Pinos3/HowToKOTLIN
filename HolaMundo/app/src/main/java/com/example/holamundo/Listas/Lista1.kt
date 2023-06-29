package com.example.holamundo.Listas

fun main(){

    inmutableLista()

    //val Arr1: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
    //mostrarLista(Arr1)


   //mutableLista()
}

fun mutableLista(){
    var diasSemana: MutableList<String> = mutableListOf("Lunes","Martes","Miércoles","Jueves","Viernes")
    for(dia in diasSemana){
        print(dia + " ")
    }

    diasSemana.add("SÁBADO")
    diasSemana.add("DOMINGO")

    println("\n")
    for(dia in diasSemana){
        print(dia + " ")
    }
    println("\n\n")


}


fun inmutableLista(){
    //es inmutable
    val readOnly: List<String> = listOf("Julie","Andrea","Esteban","Daniel","Marco", "Antonio", "Byron")
    println(readOnly.size)
    println(readOnly.toString())//ES IGUAL A: println(readOnly)

    println("Primer elemento: " + readOnly.first())
    println("Último elemento: " + readOnly.last())

    var ejemplo = readOnly.filter { it.contains('e', true) }
    println(ejemplo)

    readOnly.forEach{print(it)}

}


fun mostrarLista(lista: List<Int>){
    var suma: Int = 0

    for(numero in lista){
        suma += numero
    }

    for (valor in lista.indices){
        print("${lista[valor]} ")
    }

    println("\n" + suma )


}
