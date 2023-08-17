package com.example.poo

enum class dias(val laboral: Boolean, val jornada: Int) {

    //si días tiene atributos "datos asociados", las constantes también deben tener esos datos

    LUNES(true,8), MARTES(true,8),
    MIERCOLES(true,8), JUEVES(true,8),
    VIERNES(false,6), SABADO(false,7),
    DOMINGO(false,4);

    fun descripcioDia(): String = when(this){

        LUNES -> "Empezando con alegría!!"

        MARTES -> "Ya queda menos"
        MIERCOLES -> "Sabías que los miércoles son los días más productivos?"
        JUEVES -> "Esta noches es jueveves"
        VIERNES -> "Hoy es viernes y el cuerpo lo sabe"
        else -> "Ese día no existe"
    }


    /*
    fun saludo(): String{

        when(this){

            LUNES ->  return "Empezando con alegría!!"
            MARTES -> return "Ya queda menos"
            MIERCOLES -> return "Sabías que los miércoles son los días más productivos?"
            JUEVES -> return "Esta noches es jueveves"
            VIERNES -> return "Hoy es viernes y el cuerpo lo sabe"
            else -> return  "Ese día no existe"
        }

    }
    */


}