package com.example.poo

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var  maincontext: Context
    }

    private lateinit var pok: Pokemon
    private lateinit var waterPok: Waterpokemon
    private lateinit var firePok: FirePokemon
    private lateinit var earthPok: EarthPokemon

    //objetos anónimos
    //no es de un tipo o de una clase previamente declarada
    object fernanda{
        var apodo = "fer"
        fun saludo(){
            println("Hola, me llaman $apodo")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        maincontext = this


/*
        var humano: Person = Person("José Pinos","1726908138")
        var anonimo: Person = Person()//se puede quedar en blanco porque ya  hay valores por defecto



        println("Pasajero: " + humano.name)
        println("Pasaporte: " + humano.passport)
        println("Estado: " + when(humano.alive){
                true -> "Vivo"
                false -> "Muerto"
                else -> "Error"
        } + "\n\n")


        anonimo.Person()
        println("Pasajero: " + anonimo.name)
        println("Pasaporte: " + anonimo.passport)
        println("Estado: " + when(anonimo.alive){
            true -> "Vivo"
            false -> "Muerto"
            else -> "Error"
        } + "\n\n")


        println("aquiiii")


        var bicho:Pokemon = Pokemon()
        bicho.Pokemon("Pikachu",78.4f)
        println("Nombre: "+bicho.getName())
        println("Fuerza: "+bicho.getAttackPower())
        println("Vida: "+bicho.getLife())


        val persona: Person = Person("Julio Ríos","052186408")
        println("\n\nPERSONA: ${persona.getNamePassport()}")
        var pele:Athlete =  Athlete("Pele","1234567890","Soccer")
        pele.getNamePassport()
        //pele.alive se puede acceder porque es heredadp
        //pele.die()
        println("ATLETA: ${pele.getNamePassport()}")
*/

        var sc = SubClases()
        println(sc.presentar())

        var ani = SubClases.Anidada()
        println(ani.presentar())

        var interna = SubClases().Interna()//al ser una clase interna, necesita que la clase "padre" se cree primero
        println(interna.presentar())

        fernanda.saludo()

        var estrella: star = star("Sol",347f,"Vía Láctea")
        println(estrella)


        var hoy:dias = dias.JUEVES
        var semana: Array<dias> = dias.values()//var semana = dias.values()
        for(k in semana){
            print("${k}  ")
        }
        println(dias.valueOf("MIERCOLES"))
        println(hoy.name)//devuelve el valor que almacena "jeuves"
        println(hoy.ordinal)//devuelve la posición en que se encuentra jueves dentro del enum class
        println(hoy.descripcioDia())
        println(hoy.laboral.toString() + " " + hoy.jornada)


        var btnFight = findViewById<Button>(R.id.btnFight)
        btnFight.setOnClickListener{
            fight(waterPok, firePok)
        }


    }//FIN DE LA FUNCIÓN ONCREATE



    fun createNewPokemon(view: View){
        var etName = findViewById<EditText>(R.id.etName)
        var etAttackPower = findViewById<EditText>(R.id.etAttackPower)

        pok = Pokemon()

        if( !etName.text.isNullOrEmpty() && !etAttackPower.text.isNullOrEmpty() ){
            pok.Pokemon(etName.text.toString(), etAttackPower.text.toString().toFloat())
        }

        var ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
        ivPokemon.setImageResource(R.mipmap.pokemon)//nombre de la imagen


        var tvPokemon = findViewById<TextView>(R.id.tvPokemon)
        loadDataPokemon(tvPokemon, pok)


    }//FIN DE LA FUNCIÓN CREATENEWPOKEMON



    fun createNewWaterPokemon(v: View){
        var etWaterName = findViewById<EditText>(R.id.etWaterName)
        var etWaterAttackPower = findViewById<EditText>(R.id.etWaterAttackPower)
        var etWaterMaxResistance = findViewById<EditText>(R.id.etWaterMaxResistance)

        waterPok = Waterpokemon()


        if( !etWaterName.text.isNullOrEmpty() &&  !etWaterAttackPower.text.isNullOrEmpty()){
            waterPok.Waterpokemon(etWaterName.text.toString(),
                                  etWaterAttackPower.text.toString().toFloat(),
                                  etWaterMaxResistance.text.toString().toInt()
                                 )
        }

        var ivWaterPokemon = findViewById<ImageView>(R.id.ivWaterPokemon)
        ivWaterPokemon.setImageResource(R.mipmap.water)
        ivWaterPokemon.setBackgroundColor(ContextCompat.getColor(this, R.color.white))


        var tvWaterpokemon = findViewById<TextView>(R.id.tvWaterpokemon)
        loadDataPokemon(tvWaterpokemon, waterPok)

    }

    fun cureWaterPokemon(v: View){
        waterPok.cure()
        var tvWaterpokemon = findViewById<TextView>(R.id.tvWaterpokemon)
        loadDataPokemon(tvWaterpokemon, waterPok)
    }


    fun sayHiWaterPokemon(v: View){ waterPok.sayHi() }
    fun evolveWaterPokemon(v: View){
        var etEvolveWaterPokemon = findViewById<EditText>(R.id.etEvolveWaterPokemon)
        waterPok.evolve(etEvolveWaterPokemon.text.toString())

        var ivWaterPokemon = findViewById<ImageView>(R.id.ivWaterPokemon)
        ivWaterPokemon.setImageResource(R.mipmap.water)//debería ser una nueva imágen para el evolucionado

        var tvWaterpokemon = findViewById<TextView>(R.id.tvWaterpokemon)
        loadDataPokemon(tvWaterpokemon, waterPok)
    }

    //***************************************************************************


    fun createNewFirePokemon(v: View){
        var etFireName = findViewById<EditText>(R.id.etFireName)
        var etFireAttackPower = findViewById<EditText>(R.id.etFireAttackPower)
        var etFireBallTemperature = findViewById<EditText>(R.id.etFireBallTemperature)


        firePok = FirePokemon()

        if( !etFireName.text.isNullOrEmpty() &&  !etFireAttackPower.text.isNullOrEmpty()){
            firePok.FirePokemon(etFireName.text.toString(),
                etFireAttackPower.text.toString().toFloat(),
                etFireBallTemperature.text.toString().toInt()
            )
        }

        var ivFirePokemon = findViewById<ImageView>(R.id.ivFirePokemon)
        ivFirePokemon.setImageResource(R.mipmap.fire)
        ivFirePokemon.setBackgroundColor(ContextCompat.getColor(this, R.color.white))


        var tvFirepokemon = findViewById<TextView>(R.id.tvFirepokemon)
        loadDataPokemon(tvFirepokemon, firePok)

    }

    fun cureFirePokemon(v: View){
        firePok.cure()
        var tvFirepokemon = findViewById<TextView>(R.id.tvFirepokemon)
        loadDataPokemon(tvFirepokemon, firePok)
    }


    fun sayHiFirePokemon(v: View){ firePok.sayHi() }

    fun evolveFirePokemon(v: View){
        var etEvolveFirePokemon = findViewById<EditText>(R.id.etEvolveFirePokemon)
        firePok.evolve(etEvolveFirePokemon.text.toString())

        var ivFirePokemon = findViewById<ImageView>(R.id.ivFirePokemon)
        ivFirePokemon.setImageResource(R.mipmap.fire)//debería ser una nueva imágen para el evolucionado

        var tvFirepokemon = findViewById<TextView>(R.id.tvFirepokemon)
        loadDataPokemon(tvFirepokemon, firePok)
    }



    //**************************************************************************

    fun createNewEarthPokemon(v: View){
        var etEarthName = findViewById<EditText>(R.id.etEarthName)
        var etEarthAttackPower = findViewById<EditText>(R.id.etEarthAttackPower)
        var etEarthMaxDepth = findViewById<EditText>(R.id.etEarthMaxDepth)


        earthPok = EarthPokemon()

        if( !etEarthName.text.isNullOrEmpty() &&  !etEarthAttackPower.text.isNullOrEmpty()){
            earthPok.EarthPokemon(etEarthName.text.toString(),
                etEarthAttackPower.text.toString().toFloat(),
                etEarthMaxDepth.text.toString().toInt()
            )
        }

        var ivEarthPokemon = findViewById<ImageView>(R.id.ivEarthPokemon)
        ivEarthPokemon.setImageResource(R.mipmap.earth)
        ivEarthPokemon.setBackgroundColor(ContextCompat.getColor(this, R.color.white))


        var tvEarthPokemon = findViewById<TextView>(R.id.tvEarthPokemon)
        loadDataPokemon(tvEarthPokemon, earthPok)

    }

    fun cureEarthPokemon(v: View){
        earthPok.cure()
        var tvEartgpokemon = findViewById<TextView>(R.id.tvEartgpokemon)
        loadDataPokemon(tvEartgpokemon, earthPok)
    }


    fun sayHiEarthPokemon(v: View){ earthPok.sayHi() }

    fun evolveEarthPokemon(v: View) {
        var etEvolveEarthPokemon = findViewById<EditText>(R.id.etEvolveEarthPokemon)
        earthPok.evolve(etEvolveEarthPokemon.text.toString())

        var ivEarthPokemon = findViewById<ImageView>(R.id.ivEarthPokemon)
        ivEarthPokemon.setImageResource(R.mipmap.earth)//debería ser una nueva imágen para el evolucionado

        var tvEarthPokemon = findViewById<TextView>(R.id.tvEarthPokemon)
        loadDataPokemon(tvEarthPokemon, earthPok)
    }




    //***************************************************************************

    private fun fight(p1: Pokemon, p2: Pokemon){
        var emtlog = findViewById<EditText>(R.id.emtlog)
        emtlog.setText("")

        var text: String = ""

        text += "\n${p1.getName()}  (${p1.getLife().toInt()})   vs   ${p2.getName()}  (${p2.getLife().toInt()})"

        while( p1.getLife() > 0 && p2.getLife() > 0 ){//mientras los dos esten vivos pelean
            text += "\n${p1.getName()} ataca!"
            p1.attack()
            p2.setLife(p2.getLife() - p1.getAttackPower())

            text += "\n${p1.getName()}  (${p1.getLife().toInt()})   vs   ${p2.getName()}  (${p2.getLife().toInt()})"
            if(p2.getLife() > 0){
                text += "\n${p2.getName()} ataca!"
                p2.attack()
                p1.setLife(p1.getLife() - p2.getAttackPower())
                text += "\n${p1.getName()}  (${p1.getLife().toInt()})   vs   ${p2.getName()}  (${p2.getLife().toInt()})"
            }
        }

        if(p1.getLife() > 0){
            text += "\n\nEl campeón es: ${p1.getName()}!!"
        }else{
            text += "\n\nEl campeón es: ${p2.getName()}!!"
        }

        emtlog.setText(text)

        var tvFirepokemon = findViewById<TextView>(R.id.tvFirepokemon)
        loadDataPokemon(tvFirepokemon, firePok)

        var tvWaterpokemon = findViewById<TextView>(R.id.tvFirepokemon)
        loadDataPokemon(tvWaterpokemon, waterPok)


    }//FIN DE LA FUNCIÓN FIGHT


    //**************************************************************************

    private fun loadDataPokemon(tv: TextView, pok: Pokemon) {

        var description: String = ""
        description += pok.getName() + " ("
        description += "AP: " + pok.getAttackPower().toInt()
        description += " -L: " + pok.getLife().toInt() + ")"

        tv.text = description

    }//FIN DE LA FUNCIÓN LOADDATAPOKEMON






}