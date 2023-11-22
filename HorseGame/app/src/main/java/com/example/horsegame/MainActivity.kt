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
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var width_bonus = 0

    private var cellSelected_X = 0
    private var cellSelected_Y = 0
    private lateinit var board: Array<IntArray>

    private var levelMoves = 64
    private var movesRequired = 4//cada x movimientos, hay bonus
    private var moves =64
    private var options = 0
    private var bonus = 0

    private var nameColorBlack = "black_cell"
    private var nameColorWhite = "white_cell"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initScreenGame()
        resetBoard()
        setFirstPosition()

    }//on create


    public fun checkCellClicked(view:View){
        var name = view.tag.toString()//cxy
//        var x = name.substring(1,2).toInt()
//        var y = name.substring(2).toInt()
        var x = name.subSequence(1,2).toString().toInt()
        var y = name.subSequence(2,3).toString().toInt()

        checkCell(x, y)
    }

    private fun checkCell(x: Int, y: Int) {
        var dif_X = x - cellSelected_X
        var dif_Y = y - cellSelected_Y

        var checkTrue = false
        if (dif_X == 1 && dif_Y == 2) checkTrue = true      //right - top long
        if (dif_X == 1 && dif_Y == -2) checkTrue = true     //right - bottom long
        if (dif_X == 2 && dif_Y == 1) checkTrue = true      //right - top
        if (dif_X == 2 && dif_Y == -1) checkTrue = true     //right - bottom
        if (dif_X == -1 && dif_Y == 2) checkTrue = true     //left  - top long
        if (dif_X == -1 && dif_Y == -2) checkTrue = true    //left  - top bottom
        if (dif_X == -2 && dif_Y == 1) checkTrue = true     //left  - top
        if (dif_X == -2 && dif_Y == -1) checkTrue = true    //left  - bottom

        if(board[x][y] == 1){
            checkTrue = false
        }

        if( checkTrue ){
            selectCell(x, y)
        }
    }

    private fun resetBoard() {

        /*
        0 = casilla libre
        1 = casilla marcada
        2 = bonus
        9 = opción del movimiento actual
         */

        board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0)
        )
    }

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

        moves--
        var tvMovesData: TextView = findViewById(R.id.tvMovesData)
        tvMovesData.text = moves.toString()

        growProgressBonus()

        if(board[x][y] == 2){
            bonus++

            var tvBonusData = findViewById<TextView>(R.id.tvBonusData)
            tvBonusData.text = " + ${bonus}"

        }

        board[x][y] = 1

        //cada vez que pintemos un nuevo elemento, pintamos el anterior de naranja
        paintHorseCell(cellSelected_X, cellSelected_Y, "previus_cell")
        //almacenan la nueva posición
        cellSelected_X = x
        cellSelected_Y = y

        clearOptions()

        paintHorseCell(x, y, "selected_cell")
        checkOption(x,y)

        if(moves > 0){
            checkNewBonus()
            checkGameOver(x,y)

        } else{
            showMessage("You Win", "Next Level", false)
        }

    }


    private fun checkGameOver(x: Int, y: Int) {
        if( options == 0 ){
            if(bonus == 0){
                showMessage("Game Over", "Try Again", true)
            }
        }
    }

    private fun showMessage(title: String, action: String, gameOver: Boolean) {
        var lyMessage = findViewById<LinearLayout>(R.id.lyMessage)
        lyMessage.visibility = View.VISIBLE

        var tvTitleMessage = findViewById<TextView>(R.id.tvTitleMessage)
        tvTitleMessage.text = title


        var tvTimeData = findViewById<TextView>(R.id.tvTimeData)

        var score: String = ""
        if(gameOver){
            score = "Score " + (levelMoves-moves) + "/" + levelMoves
        }else{
            score = tvTimeData.text.toString()
        }

        var tvScoreMessage = findViewById<TextView>(R.id.tvScoreMessage)
        tvScoreMessage.text = score

        var tvAction = findViewById<TextView>(R.id.tvAction)
        tvAction.text = action
    }

    private fun growProgressBonus() {
        var moves_done = levelMoves - moves
        var bonus_done = moves_done / movesRequired
        var moves_rest = movesRequired * bonus_done
        var bonus_grow = moves_done - moves_rest

        var v = findViewById<View>(R.id.vNewBonus)
        var widthBonus =((width_bonus/movesRequired) * bonus_grow).toFloat()

        var height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, getResources().getDisplayMetrics()).toInt()
        var width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthBonus, getResources().getDisplayMetrics()).toInt()

        v.setLayoutParams(TableRow.LayoutParams(width, height))
    }

    private fun checkNewBonus() {
        if (moves % movesRequired == 0){
            var bonusCell_x = 0
            var bonusCell_y = 0

            var bonusCell:Boolean = false
            while(bonusCell == false){
                bonusCell_x = (0..7).random()
                bonusCell_y = (0..7).random()
                if(board[bonusCell_x][bonusCell_y] == 0){
                    bonusCell = true
                }
            }

            board[bonusCell_x][bonusCell_y] = 2
            paintBonusCell(bonusCell_x, bonusCell_y)
        }
    }

    private fun paintBonusCell(x: Int, y: Int) {
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        iv.setImageResource(R.drawable.bonus)
    }

    private fun clearOptions() {
        //recorremos la matriz y si hallamos un matriz con 9, la limpiamos
        for(i in 0 .. 7){
            for(j in 0 ..  7){
                if(board[i][j] == 9 || board[i][j] == 2){
                    if(board[i][j] == 9 ){
                        board[i][j] = 0
                    }
                    clearOption(i,j)
                }
            }
        }
    }

    private fun clearOption(x: Int, y: Int) {
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        if(checkColorCell(x,y) == "Black"){
            iv.setBackgroundColor(ContextCompat.getColor(this, resources.getIdentifier(nameColorBlack,"color", packageName)))

        }else{
            iv.setBackgroundColor(ContextCompat.getColor(this, resources.getIdentifier(nameColorWhite,"color", packageName)))
        }

        if(board[x][y] == 1){
            iv.setBackgroundColor(ContextCompat.getColor(this, resources.getIdentifier("previus_cell","color", packageName)))
        }

    }

    private fun checkOption(x: Int, y: Int) {
        options = 0
        //son los mismos movimientos que arriba
        checkMove(x,y,1,2)
        checkMove(x,y,2,1)
        checkMove(x,y,1,-2)
        checkMove(x,y,2,-1)
        checkMove(x,y,-1,2)
        checkMove(x,y,-2,1)
        checkMove(x,y,-1,-2)
        checkMove(x,y,-2,-1)

        //lets update options counter
        var tvOptionsData: TextView = findViewById(R.id.tvOptionsData)
        tvOptionsData.text = options.toString()
    }

    private fun checkMove(x: Int, y: Int, mov_x: Int, mov_y: Int) {
        var option_x = x+mov_x
        var option_y = y+mov_y

        //valores dentro del tablero 0-7
        if(option_x < 8 && option_y < 8 && option_x >= 0 && option_y >= 0){
            //si la casilla está libre o es bonus
            if(board[option_x][option_y] == 0 || board[option_x][option_y] == 2){
                options++
                paintOptions(option_x, option_y)

                if( board[option_x][option_y] == 0 ){
                    board[option_x][option_y] = 9
                }

            }
        }
    }


    private fun paintOptions(x: Int, y: Int){
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        if( checkColorCell(x,y) == "Black" ){
            iv.setBackgroundResource(R.drawable.option_black)
        }else{
            iv.setBackgroundResource(R.drawable.option_white)
        }
    }

    private fun checkColorCell(x: Int, y: Int): String {
        var color = ""
        var blackColumn_x = arrayOf(0,2,4,6)//de acuerdo al tablero, estas casillas son negras
        var blackRow_x = arrayOf(1,3,5,7)

        if( (blackColumn_x.contains(x) && blackColumn_x.contains(y) )
            || (blackRow_x.contains(x) && blackRow_x.contains(y)) ){
            color="Black"
        }else{
            color="White"
        }

        return color
    }


    private fun paintHorseCell(x: Int, y: Int, color: String) {
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        iv.setBackgroundColor(ContextCompat.getColor(this, resources.getIdentifier(color,"color", packageName) ))
        iv.setImageResource(R.drawable.imagencaballoblanco)

    }


    private fun initScreenGame() {
        setSizeBoard()
        hideMessage()
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

        width_bonus = 2 * width_cell.toInt()

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

    private fun hideMessage() {
        var lyMessage = findViewById<LinearLayout>(R.id.lyMessage)
        lyMessage.visibility = View.INVISIBLE
    }


}