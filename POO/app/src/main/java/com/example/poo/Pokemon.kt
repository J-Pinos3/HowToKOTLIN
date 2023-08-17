package com.example.poo

import android.widget.Toast
import com.example.poo.MainActivity.Companion.maincontext

//si los especificadores de acceso son private, las clases no los pueden heredar
open class Pokemon (protected var name: String = "Poke",
               protected var attackPower: Float = 30.0f,
               protected var life: Float = 100.0f): thanks(){



    //CONSTRUCTOR
    fun  Pokemon(n: String, aP: Float){
        this.name = n
        this.attackPower = aP
        this.life = 100.0f
        this.sayHi()
    }



    //MÉTODOS SETTER Y GETTER

    internal fun getName(): String = name
    /*
    fun getName(): String{
        return this.name
    }
    */


    internal fun getAttackPower(): Float{
        return attackPower
    }


    internal fun getLife(): Float{  return this.life  }


    internal fun setAttackPower(attack: Float){
        if( attack > 0 )
            this.attackPower = attack
        else
            this.attackPower =  0.0f
    }

    internal fun setLife(l: Float) { this.life = l }


    fun sayHi() {   Toast.makeText(maincontext,"Hola!! soy $name", Toast.LENGTH_LONG).show()  }

    fun cure() { this.life = 100.0f
        thanksCure()
    }

    fun evolve(n: String) {
        this.name = n
        this.attackPower += 1.20f
        this.sayHi()
    }

    open fun attack() {   Toast.makeText(maincontext,"Al ataqueee!!", Toast.LENGTH_LONG).show()  }

}





class Waterpokemon(n: String = "Pok", aP: Float = 30.0f, l: Float = 100.0f): Pokemon(n, aP, l){

    private var maxResistance: Int = 500
    private var submergedTime: Int = 0

    fun Waterpokemon(n: String, aP: Float, mr: Int){
       // super.Pokemon(n, aP)
        this.name = n
        this.attackPower = aP
        this.life = 100.0f
        this.maxResistance = mr
        this.sayHi()
    }

    fun breathe(){ this.submergedTime = 0 }
    fun inmerse() { this.submergedTime++ }

    override
    fun attack() {   Toast.makeText(maincontext,"Ataque con chorro de agua!!", Toast.LENGTH_LONG).show()  }
}



class FirePokemon(name: String = "Pok", attackPower: Float = 30.0f, life: Float=100.0f): Pokemon(name, attackPower, life){

    private var ballTemperature: Int = 90
    private lateinit var ball: ballFire
    var numBall: Int = 0

    fun FirePokemon(n: String, aP: Float, bT: Int){
        this.name = n
        this.attackPower = aP
        this.life = 100.0f
        this.ballTemperature = bT
        sayHi()
    }

    override
    fun attack() {

        super.attack()
        Toast.makeText(maincontext,"Ataque con bola de fuego!!", Toast.LENGTH_LONG).show()

        numBall++                                   //${++numBall}
        Toast.makeText(maincontext,"Lanzando bola número $numBall", Toast.LENGTH_LONG).show()
        ball = ballFire(ballTemperature+90)
        ball.throwBall()
    }

}

class ballFire(var t:Int = 100){
    fun throwBall(){
        Toast.makeText(maincontext,"Lanzando bola de fuego a $t grados !!!", Toast.LENGTH_LONG).show()
    }
}

/*
si no le pongo var, no puedo usarlo dentro del constructor secundario
class jose(var nombre: String){
    fun jose(n:String){
        nombre = n
    }
}
*/


class EarthPokemon(name: String = "Pok", attackPower: Float = 30.0f, life: Float=100.0f): Pokemon(name, attackPower, life), sayBye{
    private var depth: Int = 150

    override var valor: Int
        get() = TODO("Not yet implemented")
        set(value) {}



    fun EarthPokemon(n: String, aP: Float, d: Int){
        this.name = n
        this.attackPower = aP
        this.life = 100.0f
        this.depth = d
        this.sayHi()
    }


    fun digTunnel(){
        Toast.makeText(maincontext,"Cabaré un tunel de ${depth}m de profundidad", Toast.LENGTH_LONG).show()
    }


    override fun attack() {
        Toast.makeText(maincontext,"Ataque con piedras!!", Toast.LENGTH_LONG).show()
    }


}


abstract class thanks(){

    fun thanksCure(){
        Toast.makeText(maincontext,"Gracias por curarme!!", Toast.LENGTH_LONG).show()
    }

}


interface sayBye{

    var valor: Int


    fun sayBye(){
        Toast.makeText(maincontext,"Adiós!!, eres un gran oponente", Toast.LENGTH_LONG).show()
    }
}

