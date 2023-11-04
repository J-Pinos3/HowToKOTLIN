package com.example.horsegame

import android.content.Context
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableRow
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var cellSelected_X = 0
    private var cellSelected_Y = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initScreenGame()
        setFirstPosition()

    }//on create

    private fun setFirstPosition() {
        var x = 0
        var y = 0
        //coordenadas del caballo al comienzo de la partida
        x = (0..7).random()
        y = (0..7).random()

        cellSelected_X = x
        cellSelected_Y = y
        selectCell(x,y)
    }

    private fun selectCell(x: Int, y: Int) {
        //cada vez que pintemos un nuevo elemento, pintamos el anterior de naranja
        paintHorseCell(cellSelected_X, cellSelected_Y, "previus_cell")
        //almacenan la nueva posición
        cellSelected_X = x
        cellSelected_Y = y
        paintHorseCell(x, y, "selected_cell")
    }

    private fun paintHorseCell(x: Int, y: Int, color: String) {
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        iv.setBackgroundColor(ContextCompat.getColor(this, resources.getIdentifier(color,"color", packageName) ))
        iv.setImageResource(R.drawable.caballoblanco)

    }


    private fun initScreenGame() {
        setSizeBoard()
        hide_message()
    }

    private fun setSizeBoard() {
        var iv: ImageView

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x//con esto almacenamos la pantalla

        //transformamos la pantalla en dp
        var width_dp = (width / getResources().getDisplayMetrics().density)

        var lateralMarginsDP = 0
        val width_cell = (width_dp - lateralMarginsDP)/8 //ancho de la casilla
        val heigh_cell = width_cell

        for(i in 0 .. 7){
            for(j in 0 .. 7){
                iv = findViewById(resources.getIdentifier("c$i$j", "id", packageName))

                //transformamos el ancho y alto en dp
                var heigh = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heigh_cell, getResources().getDisplayMetrics()).toInt()
                var width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width_cell, getResources().getDisplayMetrics()).toInt()

                iv.setLayoutParams(TableRow.LayoutParams(width, heigh))
            }
        }
    }

    private fun hide_message() {
        var lyMessage = findViewById<LinearLayout>(R.id.lyMessage)
        lyMessage.visibility = View.INVISIBLE
    }

}