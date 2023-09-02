package com.example.kotlinavanzado

class Subclases {

    private var name = "Padre"
    fun presentar(): String = this.name



    class Anidada{
        private val nameAnidada = "Anidada"
        fun presentar(): String{
            //NO PUEDO USAR EL ATRIBUTO DEL PADRE "name"
            return this.nameAnidada
        }
    }



    inner class Interna{
        private val nameInterna = "Interna"

        fun presentar(): String{
            return "Hola, soy ${this.nameInterna}, soy hija de ${name}"//no funciona con super porque no estamos heredando
        }
    }

}