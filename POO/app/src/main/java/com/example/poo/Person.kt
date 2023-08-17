package com.example.poo

import android.annotation.SuppressLint

//al ser private, ya no se puede usar fuera de la clase
//class Person(private var name: String, var passport: String?) {
//los atributos aquí son opcionales porque ya tienen un valor asignado
open class Person (var name: String = "Anónimo", var passport: String? = null) {

    var alive:Boolean = true



    fun Person(){
        name = "Lucas"
        passport = "154087491"
    }

    open fun getNamePassport(): String = "Nombre: " + name +
                                    "\nPasaporte/C.I.: " + passport


    fun die(){
        alive = false
    }

}

//Person daba error porque estaba como 'Final', es decir el último de la cadena de herencia
//no es necesario poner var, xq esas características se heredan de la clase padre
class Athlete( name:String,  passport: String?, var sport: String): Person(name , passport){

    override // si el método a heredar es no final, no se puede heredar, tiene que ser open
    fun getNamePassport(): String = super.getNamePassport() + "\nDeporte: ${sport}"
}