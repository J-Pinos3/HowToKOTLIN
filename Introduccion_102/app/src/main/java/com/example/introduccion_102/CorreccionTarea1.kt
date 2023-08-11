package com.example.introduccion_102

fun main(){

    Ejercicio_4V1(); println("\n\n")
    Ejercicio_4V2(); println("\n\n")
    Ejercicio_5(); println("\n\n")


}

fun Ejercicio_1() {

    var numero = 9
    do {
        println("\n${numero--}")
    } while (numero >=0)

}



fun Ejercicio_2(){
    var numeros = 1
    while (numeros <= 10) {
        if (numeros % 2 == 0) println("$numeros es par")

        numeros++
    }
}


fun Ejercicio_3(){

    val platos= arrayOf("Ceviche peruano", "Tacos mexicanos", "Salchipapaaaaaaas", "Paella", "Tiramisú")

    for (i in platos) {
        println("\n$i")
    }

}



fun Ejercicio_4V1(){

    var menu: Map<String, Int> = mapOf(
        "Arroz de camarón" to 200,
        "Costillas BBQ" to 250,
        "Lomito de cerdo" to 150,
        "Carne asada" to 130,
        "Pechuga gratinada" to 140)

    var i: Int = 1
    for( (plato, precio) in menu) {
        println("Plato $i++: $plato tiene un precio de $precio")
    }

}



fun Ejercicio_4V2(){

    var menu: MutableMap<String, Double> = mutableMapOf<String, Double>()
    menu.put("Onion Soup", 10.17)
    menu.put("Salad", 15.85)
    menu.put("Pizza", 14.64)
    menu.put("Rice", 20.99)
    menu.put("Cake", 33.9)

    for (x in menu) {
        println("Plato: ${x.key} - Precio$${x.value}")
    }

}


fun Ejercicio_5(){

    var menu = arrayOf(
        arrayOf("guacamole",45.5f, arrayOf("aguacate","cebolla","chile","jitomate")),
        arrayOf("enchiladas",90.0f, arrayOf("tortillas","frijoles","queso","chile","crema","cebolla","guacamole")),
        arrayOf("torta",50.0f, arrayOf("bolillo","guizado","salsa")),
        arrayOf("tacos rojos", 75.5f,arrayOf("tortilla","chile","queso","papas","zanahoria","crema","salsa"))
    )

    println("menu con precios")
    for(i in (0 until menu.size)) {
        for (j in (0 until menu[i].size)) {
            println()
            when (j) {
                0 -> {
                    print("$i. ${menu[i][j]}")
                }

                1 -> {
                    print(" $${menu[i][j]}")
                }

                2 -> {
                    print(" Incredientes : ")
                    var ingredientes: Array<String> = menu[i][j] as Array<String>
                    for (i in ingredientes) {
                        print("$i - ")
                    }
                }
            }
        }

    }

//************************
}