package com.example.homeworkpoo

fun main(){

    var corredor1: Runner = Runner()
    corredor1.Runner("Juan Macizo", 1.80f, 71.0f, 26, 27f, "")

    corredor1.mostrarCategorias()

    println("\n--------------------------\n")
    corredor1.setCategorie( corredor1.seleccionaDisciplina(2) )

    println("Corredor 1\n" +
            "${corredor1.presentarse()}")



    println("\n\n**************************************\n\n")

    var bicicletas: Bicicletas = Bicicletas.TRIATLON
    var ciclista1:Ciclista = Ciclista("Karla Muñoz", 1.50f, 45.70f, 20,
                                        35.4f)

    ciclista1.setBici( bicicletas.name )
    println(bicicletas.descripcionBici() + "\n")
    println("Ciclista 1\n" +
            "${ciclista1.presentarse()}")


    println("\n\n**************************************\n\n")

    var estilosNado: Estilos_de_Nado = Estilos_de_Nado.LIBRE
    var nadador1: Nadador = Nadador()
    nadador1.Nadador("Esteban Páez", 1.91f, 60f, 23, 11f, "")
    nadador1.setEstileNado( estilosNado.name )

    println(estilosNado.descripcionNado() + "\n")
    println("Nadador 1\n" +
            "${nadador1.presentarse()}")

    println("\n\n**************************************\n\n")

    var triatlon1: Triatleta = Triatleta("JJVM",1.85f, 70f, 30)
    triatlon1.velocidadCorre = 27f
    triatlon1.velocidadNado = 10f
    triatlon1.velocidadPedalea = 30f

    triatlon1.estiloDeCorrer(  corredor1.seleccionaDisciplina(2)  )//para no crear otro String
    triatlon1.estiloDeNado( estilosNado.name )
    triatlon1.estiloDePedalear( bicicletas.name )
    println("Triatleta 1\n" +
            triatlon1.presentarse())

}