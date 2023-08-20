package com.example.homeworkpoo

class Triatleta(name:String = "", estatura:Float = 0f, peso: Float = 0f, edad: Int = 0):
    Deportista(name, estatura, peso, edad),Interfaz_Nado, Interfaz_Corre, Interfaz_Pedalea {//FIN DE LA CLASE TRIATLETA


    override var velocidadCorre: Float = 0.0f


    override fun estiloDeCorrer(estilo: String) {
        println("Puedo correr estilo: ${estilo} a ${velocidadCorre} Km/h")
    }



    override var velocidadNado: Float = 0.0f


    override fun estiloDeNado(estilo: String) {
        println("Puedo nadar estilo: ${estilo} a ${velocidadNado} Km/h")
    }



    override var velocidadPedalea: Float = 0.0f


    override fun estiloDePedalear(estilo: String) {
        println("Puedo pedalear estilo: ${estilo} a ${velocidadPedalea} Km/h")
    }

    override fun presentarse(): String {
        return super.presentarse() + "\n" +
                "Soy Atleta de Triatlón."
    }

}