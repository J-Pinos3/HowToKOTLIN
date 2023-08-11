package com.example.introduccion_102

import java.util.concurrent.atomic.AtomicIntegerArray

fun main()
{
    /*
                CONTADOR DEL 9 AL 0
    var contador: Int= 9
    do
    {
        print(contador.toString() + " ")
        contador--
    }while( contador >= 0 )
    */





    /*
                MOSTRAR LOS PARES DEL 1 AL 10
    val arreglo: Array<Int> = arrayOf(1,2,3,4,5,6,7,8,9,10)

    for(k in arreglo){
        if( k % 2 == 0) print("${k} ")
    }
    */



    /*
                MOSTRAR LOS PLATOS DE UN ARRAY
    val platos: Array<String> = arrayOf("Hamburguesa","Encebollado","Pizza","Pollo Horneado","Costillas de Brontosaurio")
    println("\tPLATILLOS\n")
    for( i in platos.indices)
    {
        println("${platos[i]}")
    }
    */



    /*
                ARREGLO CON PLATO Y PRECIO
    var platos: Array< Map<String,Float> > = arrayOf(

        mapOf<String, Float>("Pizza" to 1.50f),
        mapOf<String, Float>("Lasagna" to 5.87f),
        mapOf<String, Float>("Papas Fritas" to 0.5f),

    )

    for(k in 0 until platos.size){
        println(platos[k])
    }
    */




    val platos: Array< MutableMap< Map<String,Float>, Array<String> > > = arrayOf(


        mutableMapOf< Map<String, Float>, Array<String> >( mapOf<String, Float>("Pizza" to 1.56f) to arrayOf("queso","masa","tomate","peperonni") ),
        mutableMapOf< Map<String, Float>, Array<String> >( mapOf<String, Float>("Hamburguesa" to 1.56f) to arrayOf("carne molida","queso","pan","cebolla") ),
        mutableMapOf< Map<String, Float>, Array<String> >( mapOf<String, Float>("Jugo de Mandarina" to 1.56f) to arrayOf("agua","mandarina","azucar") )

    )


    for(k in platos.indices){

        //println("${platos[k].keys}      ${platos[k].values}")
        println(platos[k].keys)
        for(i in platos[k].keys){
            //print(platos[k].get(i)?.size)//obtengo el tamaño de cada array
                for(j in 0 until platos[k].get(i)?.size!!){
                    print(platos[k].get(i)?.get(j) + " ")
                }
            println()
//            println(i)
        }
        println("\n")

    }




}

