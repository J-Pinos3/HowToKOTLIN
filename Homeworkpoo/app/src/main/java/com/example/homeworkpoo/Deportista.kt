package com.example.homeworkpoo

abstract class Deportista(private var name: String = "",
                          private  var estatura: Float = 0f,
                          private  var peso: Float = 0f,
                          private  var edad: Int = 0 ){


        fun Deportista(name:String, estatura:Float, peso: Float, edad:Int){
                this.name = name
                this.estatura = estatura
                this.peso = peso
                this.edad = edad
        }

        fun getName(): String = name

        fun getHeight(): Float = this.estatura

        fun getWeight(): Float{
            return this.peso
        }

        fun getAge(): Int { return edad }

        open fun presentarse(): String{

            return "Hola, yo me llamo ${getName()}, tengo ${getAge()} años.\n" +
                    "Físicamente mi estatura es ${getHeight()} y peso ${getWeight()} Kg."
        }


        open public fun aCompetir(estilo: String){
            println("Voy a")
        }

        open fun descanzar(){
            println("DÉJANME DORMIR!!")
        }

}