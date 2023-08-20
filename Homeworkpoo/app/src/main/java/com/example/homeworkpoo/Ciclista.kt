package com.example.homeworkpoo

//tipo de bici, velocidad
class Ciclista(name:String = "", estatura:Float = 0f, peso: Float = 0f, edad: Int = 0
               ,var velocidad: Float = 50.0f, var tipoBici: String = "default"):
                Deportista(name, estatura, peso, edad) {


    fun obtenerVelocidad():Float{
        return this.velocidad
    }

    fun setVelocida(velocidad: Float) {  this.velocidad = velocidad }


    fun getBici(): String = tipoBici

    fun setBici(bici: String){
        this.tipoBici = bici
    }

    override
    fun presentarse(): String {
        return super.presentarse() +
                "\nMi tipo de bicicleta es: ${getBici()} y " +
                "soy capaz de rodar a ${obtenerVelocidad()} Km/h.\n"
    }

    override
    fun aCompetir(estilo: String) {
        println("Voy a pedalear estilo: ${estilo}")
    }

}




enum class Bicicletas{
    MONTANIA, RUTA, CARRETERA, AERO, TRIATLON;


    fun descripcionBici(): String = when(this){
                            MONTANIA -> "BICICLETA DE MONTAÑA"
                            RUTA -> "BICICLETA DE RUTA TRANQUILA"
                            CARRETERA -> "BICICLETA PARA CARRERA EN CARRETERA"
                            AERO -> "BICICLETA AERODINÁMICA"
                            TRIATLON -> "BICICLETA PARA TRIATLÓN"
                        }
}