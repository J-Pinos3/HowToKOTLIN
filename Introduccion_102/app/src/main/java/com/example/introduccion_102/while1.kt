package com.example.introduccion_102


var saldo1: Float = 548.0F
fun main(){


    var bolsa: MutableList<String> = mutableListOf("ARCA CONTINENTAL","ADIDAS","NIKE","AMAZON","META")
    var inversiones: MutableMap<String, Float> = mutableMapOf()

    mostrar_saldo()
    var empresa: String? = ""
    var cantidad_a_invertir: Float = 90.0f
    var index: Int = 0


    while(saldo1 >= cantidad_a_invertir){
        empresa  = bolsa.elementAtOrNull(index)//!! al poner "!!", estoy asegurándole que no es null
        if(empresa != null){
            saldo1 -= cantidad_a_invertir

            println("Se ha invertido $cantidad_a_invertir en $empresa")
            inversiones.put(empresa, cantidad_a_invertir)
        }else{
            break
        }

        index++
    }

    mostrar_saldo()

}

fun mostrar_saldo(){
    println("Su saldo es:  $saldo1 $\n")
}