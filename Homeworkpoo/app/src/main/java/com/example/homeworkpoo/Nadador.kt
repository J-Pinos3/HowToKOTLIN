package com.example.homeworkpoo

class Nadador(name:String = "", estatura:Float = 0f, peso: Float = 0f, edad: Int = 0,
    var velocidad: Float= 10.0f, var estiloNado: String = "Simple"):
    Deportista(name, estatura, peso, edad) {


    fun Nadador(n: String, est:Float, pes:Float, age:Int, veloci: Float, estile: String){
        this.velocidad = veloci
        this.estiloNado = estile
        super.Deportista(n,est,pes,age)
    }



    fun getVeloci(): Float{ return this.velocidad}

    fun getEstileNado():String = estiloNado

    fun setEstileNado(estilo:String ){  this.estiloNado = estilo  }


    override
    fun presentarse(): String {
        return super.presentarse() +
                "\nMi tipo de nado es: ${getEstileNado()} y " +
                "puedo nadar a ${getVeloci()} + Km/h.\n"
    }

    override
    fun aCompetir(estilo: String) {
        println("Voy a nadar estilo: ${estilo}")
    }



}


enum class Estilos_de_Nado{
    LIBRE, DORSO, PECHO, MARIPOSA, SINCRONIZADO;

    fun descripcionNado(): String = when(this){
        LIBRE -> "NADO LIBRE"
        DORSO -> "NADO DORSO"
        PECHO -> "NADO PECHO"
        MARIPOSA -> "NADO MARIPOSA"
        SINCRONIZADO -> "NADO ARTÍSTICO"
    }
}