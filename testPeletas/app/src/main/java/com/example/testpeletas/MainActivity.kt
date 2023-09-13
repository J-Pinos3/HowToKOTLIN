package com.example.testpeletas

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.testpeletas.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //es igual a var textViewMensaje: TextView = findViewById(R.id.tvMensaje)
        var textViewMensaje = findViewById<TextView>(R.id.tvMensaje)

        textViewMensaje.apply {
            setTextColor(Color.BLUE)
            text = "Texto cambiado desde código"
            setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
        }

//        textViewMensaje.setTextColor(Color.BLUE)
//        textViewMensaje.text = "Texto cambiado desde código"
//        textViewMensaje.setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
        textViewMensaje.setOnClickListener {
            Toast.makeText(this, "Nuevo Texto On Click", Toast.LENGTH_SHORT).show()
            textViewMensaje.setTextColor(Color.RED)
        }

        /*
        var etTextNombre = findViewById<EditText>(R.id.etTextNombre)
        //cada vez que se cambie el contenido, se ejecutará esto
        etTextNombre.addTextChangedListener{
            if(etTextNombre.text.length == 0) etTextNombre.setError("Campo vacío")

        }
        etTextNombre.setSelection(4)
        var inicio = etTextNombre.selectionStart
        var fin = etTextNombre.selectionEnd
        etTextNombre.setSelection(inicio, fin)
        etTextNombre.selectAll()
        */
        var etTextNombre = findViewById<EditText>(R.id.etTextNombre)
        etTextNombre.addTextChangedListener {
            if (etTextNombre.text.length == 0) etTextNombre.setError("Campo vacío")
        }
        etTextNombre.setSelection(3)
        var inicio = etTextNombre.selectionStart
        var fin = etTextNombre.selectionEnd

        etTextNombre.setSelection(inicio, fin)
        etTextNombre.selectAll()




        var autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        var paises: Array<String> = resources.getStringArray(R.array.countries_array)

        var adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,paises)

        autoCompleteTextView.setAdapter(adapter)



        var multiAutoCompleteTextView = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)
        multiAutoCompleteTextView.setAdapter(adapter)
        multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

    }//FIN DE ON CREATE
}





