package com.example.homeworkpoo

class Runner(var velocidad: Float = 20.0f, name: String = "", estatura: Float = 0f, peso: Float = 0f, edad: Int = 0):
    Deportista(name, estatura, peso, edad) {


    var tipoRunner: String = ""
    var arrayCategories:Array<String> = arrayOf(
        "100 METROS LISOS","400 METROS CON OBSTÁCULOS",
        "MARATÓN", "FONDO", "RELEVOS"

    )


    fun Runner(n: String, est:Float, pes:Float, age:Int, veloci: Float, tR: String){
        velocidad = veloci
        tipoRunner = tR
        super.Deportista(n, est, pes, age)
    }



    fun seleccionaDisciplina(posicion:Int): String{
        return arrayCategories[posicion]
    }

    fun mostrarCategorias(){
        println("Las categorías para corredores son: \n")
        var i = 0
        for(k in arrayCategories){
            println("$k (${++i}) ")
        }
    }

    override
    fun presentarse():String{
        return "${super.presentarse()}\n" +
                "Mi categoría es: ${tipoRunner}\n\n."
    }


    override
    fun aCompetir(estilo: String) {
        println("Voy a correr estilo: ${estilo}")
    }

    fun setCategorie(cat: String){
        tipoRunner = cat
    }

    //debe contener estilo(100m lisos, 400m con obstaculos, maratón...) y velocidad
}


