package com.example.introduccion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Operadorescalculo: AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var a: Int = 5 + 5
        val b: Int = 10 - 2
        val c: Int = 3 * 4
        val d: Int = 14 / 2
        val e: Int = 10 % 3
        val f: Int = 10 / 6
        val g: Float = 10 / 6f
        val h: Double = 10 / 6.0
        val i: Double = 10 * 3.4
        val j: Double = 10.0 / 6

        println(a)
        println(b)
        println(c)
        println(d)
        println(e)
        println(f)
        println(g)
        println(h)
        println(i)
        println(j)
        println(3.0+4.0)

        println("\n--------------------\n")

        var aPreIncremento: Int  = 5;
        var bPreDecremento: Int  = 5;
        var cPostIncremento: Int  = 5;
        var dPostDecremento: Int  = 5


        println(aPreIncremento)
        println(++aPreIncremento)
        println(aPreIncremento)

        println("\n--------------------\n")

        println(bPreDecremento)
        println(--bPreDecremento)
        println(bPreDecremento)

        println("\n--------------------\n")

        println(cPostIncremento)
        println(cPostIncremento++)
        println(cPostIncremento)

        println("\n--------------------\n")

        println(dPostDecremento)
        println(dPostDecremento--)
        println(dPostDecremento)

        println("\n--------------------\n")

    }
}