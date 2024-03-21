package com.example.horsegame

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.util.TimeUtils
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.test.runner.screenshot.ScreenCapture
import androidx.test.runner.screenshot.Screenshot.capture
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.stripe.android.PaymentConfiguration
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var bitmap: Bitmap? =  null
    private var mHandler: Handler? = null
    private var timeinSeconds: Long = 0

    private var gaming = true
    private var string_share = ""

    private var width_bonus = 0

    private var cellSelected_X = 0
    private var cellSelected_Y = 0
    private lateinit var board: Array<IntArray>

    private var nextLevel: Boolean = false
    private var level = 1
    private var levelMoves = 0
    private var scoreLevel = 1
    private var movesRequired = 0//cada x movimientos, hay bonus
    private var moves =0
    private var lives = 1
    private var score_lives = 1

    private var options = 0
    private var bonus = 0

    private var checkMovement = true

    private var nameColorBlack = "black_cell"
    private var nameColorWhite = "white_cell"

    private  var interstitialAd: InterstitialAd?= null
    private var unloadedAd = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAds()

        initScreenGame()
        startGame()



    }//on create

    fun launchPaymentCard(v: View){
        callPayment()
    }

    private fun callPayment(){
        val keyStripePayment= "pk_test_Dt4ZBItXSZT1EzmOd8yCxonL"
        PaymentConfiguration.init(
            applicationContext,
            keyStripePayment
        )

        val intent = Intent(this, CheckoutActivity::class.java)
        startActivity(intent)
    }

    private fun initAds() {
        MobileAds.initialize(this){}

        val adView = AdView(this)
        adView.setAdSize(AdSize.BANNER)
        adView.adUnitId = "ca-app-pub-3940256099942544/9214589741"

        val lyAdsBanner = findViewById<LinearLayout>(R.id.lyAdsBanner)
        lyAdsBanner.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    private fun showAdInterstitial(){
        if(interstitialAd != null){
            unloadedAd = true
            interstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback(){
                override fun onAdClicked() {
                    Log.d("Ad","Ad was clicked")
                }

                override fun onAdDismissedFullScreenContent() {
                    Log.d("Ad","Ad dismissed fullscreen content")
                    interstitialAd = null
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    //super.onAdFailedToShowFullScreenContent(p0)
                    Log.d("Ad","Ad failed to show fullscreen content")
                    interstitialAd = null
                }

                override fun onAdImpression() {
                    Log.d("Ad","Ad recorded an impression")
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("Ad","Ad showed fullscreen content")
                }
            }


            interstitialAd?.show(this)
        }else{
            Log.d("Ad","The interstitial ad wasn't ready yet")
        }
    }

    private fun getReadyAds(){
        val adRequest = AdRequest.Builder().build()
        unloadedAd = false
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
            object:InterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(p0: LoadAdError) {  interstitialAd = null  }

                override fun onAdLoaded(p0: InterstitialAd) {  interstitialAd = p0  }
            })
    }

    private fun initScreenGame() {
        setSizeBoard()
        hideMessage(false)
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

    private fun hideMessage(start: Boolean) {
        val lyMessage = findViewById<LinearLayout>(R.id.lyMessage)
        lyMessage.visibility = View.INVISIBLE

        if (start){
            startGame()
        }
    }

    fun launchAction(view:View){
        hideMessage(true)
    }

    fun launchShareGame(view: View){
        shareGame()
    }

    private fun shareGame() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)


        val ssc: ScreenCapture = capture(this)
        bitmap = ssc.getBitmap()

        if(bitmap != null){
            var idGame = SimpleDateFormat("yyyy/MM/dd").format(Date())
            idGame = idGame.replace(":","")
            idGame = idGame.replace("/","")

            val path = saveImage(bitmap, "${idGame}.jpg")
            val bmpUri = Uri.parse(path)

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri)
            shareIntent.putExtra(Intent.EXTRA_TEXT, string_share)
            shareIntent.type = "image/png"

            val finalShareIntent = Intent.createChooser(shareIntent,"Select the app you want to share the game to")
            finalShareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(finalShareIntent)
        }
    }

    private fun saveImage(bitmap: Bitmap?, fileName:String): String?{
        if (bitmap == null)
            return null

        if(android.os.Build.VERSION.SDK_INT  >= VERSION_CODES.Q ){
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/screenshots")
            }


            val uri = this.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            if(uri != null){
                this.contentResolver.openOutputStream(uri).use {
                    if(it == null)
                        return@use

                    bitmap.compress(Bitmap.CompressFormat.PNG, 85, it)
                    it.flush()
                    it.close()

                    //add pic to gallery
                    MediaScannerConnection.scanFile(this, arrayOf(uri.toString()), null, null )
                }
            }
            return uri.toString()
        }
        val filePath = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES+"/screenshots"
        ).absolutePath

        val dir = File(filePath)
        if(!dir.exists())
            dir.mkdirs()
        val file = File(dir, fileName)
        val fOut = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut)
        fOut.flush()
        fOut.close()

        //add pic to gallery
        MediaScannerConnection.scanFile(this, arrayOf(file.toString()), null, null )
        return filePath
    }

    public fun checkCellClicked(view:View){
        var name = view.tag.toString()//cxy
//        var x = name.substring(1,2).toInt()
//        var y = name.substring(2).toInt()
        var x = name.subSequence(1,2).toString().toInt()
        var y = name.subSequence(2,3).toString().toInt()

        checkCell(x, y)
    }

    private fun checkCell(x: Int, y: Int) {

        var checkTrue = true
        if(checkMovement){
            var dif_X = x - cellSelected_X
            var dif_Y = y - cellSelected_Y
            checkTrue = false
            if (dif_X == 1 && dif_Y == 2) checkTrue = true      //right - top long
            if (dif_X == 1 && dif_Y == -2) checkTrue = true     //right - bottom long
            if (dif_X == 2 && dif_Y == 1) checkTrue = true      //right - top
            if (dif_X == 2 && dif_Y == -1) checkTrue = true     //right - bottom
            if (dif_X == -1 && dif_Y == 2) checkTrue = true     //left  - top long
            if (dif_X == -1 && dif_Y == -2) checkTrue = true    //left  - top bottom
            if (dif_X == -2 && dif_Y == 1) checkTrue = true     //left  - top
            if (dif_X == -2 && dif_Y == -1) checkTrue = true    //left  - bottom
        }else{
            if(board[x][y] != 1){
                bonus--
                val tvBonusData = findViewById<TextView>(R.id.tvBonusData)
                tvBonusData.text = " + $bonus"

                if(bonus == 0){
                    tvBonusData.text = ""
                }
            }
        }


        if(board[x][y] == 1){
            checkTrue = false
        }

        if( checkTrue ){
            selectCell(x, y)
        }
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
        checkMovement = true
        checkOption(x,y)

        if(moves > 0){
            checkNewBonus()
            checkGameOver()

        } else{
            showMessage("You Win", "Next Level", false)
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

    private fun clearBoard(){
        var iv: ImageView
        var colorBlack = ContextCompat.getColor(this,
            resources.getIdentifier(nameColorBlack,"color", packageName))

        var colorWhite = ContextCompat.getColor(this,
            resources.getIdentifier(nameColorWhite,"color", packageName))

        for(i in 0..7){
            for(j in 0..7){
                iv = findViewById(resources.getIdentifier("c$i$j","id", packageName))
                iv.setImageResource(0)
                if(checkColorCell(i,j) == "black"){
                    iv.setBackgroundColor(colorBlack)
                }else{
                    iv.setBackgroundColor(colorWhite)
                }
            }
        }
    }

    private fun setFirstPosition() {
        var x = 0
        var y = 0

        var firstPosition = false
        while(firstPosition == false){
            //coordenadas del caballo al comienzo de la partida
            x = (0..7).random()
            y = (0..7).random()
            if(board[x][y] == 0){
                firstPosition = true
            }
            checkOption(x, y)
            if(options == 0){
                firstPosition = false
            }
        }


        cellSelected_X = x
        cellSelected_Y = y
        selectCell(x,y)
    }


    private fun setLevel(){
        if(nextLevel){
            level++
            setLives()
        }else{
            lives--
            if(lives < 1){
                level= 1
                lives=1
            }
        }
    }

    private fun setLives(){
        when(level){
            1->lives = 1
            2->lives = 4
            3->lives = 3
            4->lives = 3
            5->lives = 4
            6->lives = 3
            7->lives = 5
            8->lives = 3
            9->lives = 4
            10->lives = 5
            11->lives = 5
            12->lives = 3
            13->lives = 4
        }
    }


    private fun setLevelParameters(){
        val tvLiveData = findViewById<TextView>(R.id.tvLiveData)
        tvLiveData.text = lives.toString()

        val tvLevelNumber = findViewById<TextView>(R.id.tvLevelNumber)
        tvLevelNumber.text = level.toString()


        val tvBonusData = findViewById<TextView>(R.id.tvBonusData)
        tvBonusData.text = ""

        setLevelMoves()
        movesRequired = setMovesRequired()
    }

    private fun setLevelMoves(){
        when(level){
            1->levelMoves = 64
            2->levelMoves = 56
            3->levelMoves = 32
            4->levelMoves = 16
            5->levelMoves = 48
            6->levelMoves = 36
            7->levelMoves = 48
            8->levelMoves = 49
            9->levelMoves = 59
            10->levelMoves = 48
            11->levelMoves = 64
            12->levelMoves = 48
            13->levelMoves = 48
        }
    }

    private fun setMovesRequired(): Int {
        var movesRequired=0
        when (level) {
            1 -> movesRequired = 8
            2 -> movesRequired = 10
            3 -> movesRequired = 12
            4 -> movesRequired = 10
            5 -> movesRequired = 10
            6 -> movesRequired = 12
            7 -> movesRequired = 5
            8 -> movesRequired = 7
            9 -> movesRequired = 9
            10 -> movesRequired = 8
            11 -> movesRequired = 1000
            12 -> movesRequired = 5
            13 -> movesRequired = 5

        }
        return movesRequired
    }

    private fun setBoardLevel(){
        when(level){
            2 -> paintLevel_2()
            3 -> paintLevel_3()
            4 -> paintLevel_4()
            5 -> paintLevel_5()
            6 -> paintLevel_6()
            7 -> paintLevel_7()
            8 -> paintLevel_8()
            9 -> paintLevel_9()
            10 -> paintLevel_10()
            11 -> paintLevel_11()
            12 -> paintLevel_12()
            13 -> paintLevel_13()
        }
    }

    private fun paint_Column(Column: Int){
        for(i in 0..7){
            board[Column][i] = 1
            paintHorseCell(Column, i, "previus_cell")
        }
    }

    private fun paint_Row(row:Int){
        for(i in 0..7){
            board[i][row] = 1
            paintHorseCell(i, row, "previus_cell")
        }
    }

    private fun paint_Diagonal(diagonal:Int){
        for(i in 0..diagonal){
            board[i][i]=1
            paintHorseCell(i, i, "previus_cell")
        }
    }

    private fun paint_DiagonalInverse(diagonal:Int){
        for( i in (diagonal downTo  0) ){
            board[i][i]=1
            paintHorseCell(i, i, "previus_cell")
        }
    }

    private fun paintLevel_2(){
        paint_Column(6)
    }

    private fun paintLevel_3(){
        for(i in 0..7){
            for(j in 4..7){
                board[j][i] = 1
                paintHorseCell(j, i, "previus_cell")
            }
        }
    }

    private fun paintLevel_4(){
        paintLevel_3();  paintLevel_5()
    }

    private fun paintLevel_5(){
        for(i in 0..3){
            for(j in 0..3){
                board[j][i] = 1
                paintHorseCell(j, i, "previus_cell")
            }
        }
    }


    private fun paintLevel_6(){
        paintLevel_5()
        paint_Diagonal(4)
    }

    private fun paintLevel_7(){
        paintLevel_4()
        paintLevel_2()
    }

    private fun paintLevel_8(){
        paint_Row(4)
    }

    private fun paintLevel_9(){
        paint_Row(3)
        paint_Column(7)
    }

    private fun paintLevel_10(){
        paint_DiagonalInverse(5)
    }

    private fun paintLevel_11(){
        paintLevel_3(); paint_Row(6)
    }

    private fun paintLevel_12(){
        paint_Diagonal(4); paint_Column(1)
    }

    private fun paintLevel_13(){
        paintLevel_10();  paint_Column(2)
    }



    private fun checkGameOver() {
        if( options == 0 ){
            if(bonus > 0){
                checkMovement = false
                paintAllOptions()
            }else {
                showMessage("Game Over", "Try Again", true)
            }
        }
    }


    private fun showMessage(title: String, action: String, gameOver: Boolean) {
        gaming = false
        nextLevel = !gameOver

        var lyMessage = findViewById<LinearLayout>(R.id.lyMessage)
        lyMessage.visibility = View.VISIBLE

        var tvTitleMessage = findViewById<TextView>(R.id.tvTitleMessage)
        tvTitleMessage.text = title


        var tvTimeData = findViewById<TextView>(R.id.tvTimeData)

        var score: String = ""
        if(gameOver){

            showAdInterstitial()
            score = "Score " + (levelMoves-moves) + "/" + levelMoves
            string_share = "This game makes me sick!! (" +
                    score + "). https://concepto.de/wp-content/uploads/2021/07/caballos-e1626738224231.jpg"
        }else{
            score = tvTimeData.text.toString()
            string_share = "Let's go!!, New Challenge Completed. Level: $level (" +
                    score + "). https://concepto.de/wp-content/uploads/2021/07/caballos-e1626738224231.jpg"
        }

        var tvScoreMessage = findViewById<TextView>(R.id.tvScoreMessage)
        tvScoreMessage.text = score

        var tvAction = findViewById<TextView>(R.id.tvAction)
        tvAction.text = action
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

    private fun paintOptions(x: Int, y: Int){
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        if( checkColorCell(x,y) == "Black" ){
            iv.setBackgroundResource(R.drawable.option_black)
        }else{
            iv.setBackgroundResource(R.drawable.option_white)
        }
    }

    private fun paintAllOptions() {
        for(i in 0..7){
            for(j in 0..7){
                if(board[i][j] != 1){
                    paintOptions(i,j)
                }

                if(board[i][j] == 0){
                    board[i][j] = 9
                }
            }
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


    private fun resetTime(){
        mHandler?.removeCallbacks(chronometer)
        timeinSeconds = 0

        val tvTimeData = findViewById<TextView>(R.id.tvTimeData)
        tvTimeData.text = "00:00"
    }

    private fun startTime(){
        mHandler = Handler(Looper.getMainLooper())
        chronometer.run()
    }

    private var chronometer: Runnable = object : Runnable{
        override fun run() {
            try {
                //increase seconds
                if(gaming){
                    timeinSeconds++
                    updateStopWatchView(timeinSeconds)
                }

            }finally {
                //interval of time between executions
                mHandler!!.postDelayed(this, 1000L)

            }
        }

    }

    private fun updateStopWatchView(timeinSeconds: Long) {
        val formattedTime = getFormattedStopWatch(timeinSeconds*1000)
        val tvTimeData = findViewById<TextView>(R.id.tvTimeData)
        tvTimeData.text = formattedTime
    }

    private fun getFormattedStopWatch(ms: Long): String{
        var milliseconds = ms
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
        return "${if(minutes < 10) "0" else ""}$minutes: " +
                "${if(seconds<10) "0" else ""}$seconds"
    }

    private fun startGame(){

        if(unloadedAd == true){
            getReadyAds()
        }



        setLevel()

        setLevelParameters()


        resetBoard()
        clearBoard()

        setBoardLevel()

        setFirstPosition()

        resetTime()
        startTime()
        gaming = true
    }

}