package com.reverb.kotlinreview

fun main(){
    printMessage("Todos los array son de tamaño fijo\n")

    printMessage("Array<Int>")
    val arrarray: Array<Int> = arrayOf(1,2,3,4,5,6) //CORRECTO ☑️
    showArrayIntElements(arrarray)

    printMessage("Array(int, function)")
    val a = Array(5){ element -> Math.pow((element+1.0),2.0)   }//.sum

    for(k in a){
        print("$k ")
    }
    println("\n")

    //si el array es Array<Float>, no puedo usar floatArrayOf
    //Array<T> , no se puede <T>AarrayOf
    //val arrarray2: Array<Float> = floatArrayOf(0f)// ERROR REQUIERED Array<Float>, found floatarray
    //val arrarray22: Array<Float> = floatArrayOf(0f).toTypedArray() WORKS
    //EXCEPTO PARA STRINGS
    val arrStrinss: Array<String> = arrayOf(" ", "d ")



    val arrIntsss: Array<Int> = intArrayOf(1,5,3,6).toTypedArray()
    //FUNCIONA, pero es mejor que el arreglo sea IntArray como debería ser



    printMessage("Array<Float>")
    val arrarray2: Array<Float> = arrayOf(3f,5.6f) //CORRECTO ☑️
    showArrayFloatElements(arrarray2)


    printMessage("FloatArray.toTypedArray()")
    val arrarray3: FloatArray = floatArrayOf(0.12f, 0.15f, 0.18f) //CORRECTO ☑️
    showArrayFloatElements(arrarray3.toTypedArray())


    printMessage("FloatArray")
    val arrarray4: FloatArray = FloatArray(5)
    arrarray4[0] = 11f
    arrarray4[1] = 22f
    arrarray4[2] = 33f
    arrarray4[3] = 44f
    arrarray4[4] = 55f
    showFloatArrayElements(arrarray4)

}



private fun printMessage(message: String){
    print("\n\nExercise: $message\n")
}



private fun showArrayFloatElements(array: Array<Float>){
    for(k in array.indices){
        print("${array[k]} ")
    }
    println("\n")
}


private fun showFloatArrayElements(array: FloatArray){
    for(k in array.indices){
        print("${array[k]} ")
    }
    println("\n")
}


private fun showArrayIntElements(array: Array<Int>){
    for(k in array.indices){
        print("${array[k]} ")
    }
    println("\n")
}