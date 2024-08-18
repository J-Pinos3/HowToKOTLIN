package com.reverb.dicerollerapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reverb.dicerollerapp.ui.theme.DiceRollerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //DiceRollerApp()
                    LemonadeApp()
                }
            }
        }
    }
}
/**
 * SOLUTION AT: [https://github.com/google-developer-training/basic-android-kotlin-compose-training-lemonade/blob/main/app/src/main/java/com/example/lemonade/MainActivity.kt]
 *
 *
 * */

@Composable
fun LemonImageAndText(
    modifier: Modifier = Modifier
        .fillMaxSize()
        //.background(color = Color(0xFF8BDBB6))
){

    val stringsResourcesList = listOf<Int>(
        R.string.tap_lemon_tree,
        R.string.keep_tapping,
        R.string.drink_lemonade,
        R.string.tap_empty_glass
    )

    val imageResourcesList = listOf<Int>(
        R.drawable.lemon_tree,
        R.drawable.lemon_squeeze,
        R.drawable.lemon_drink,
        R.drawable.lemon_restart
    )
    /***
     * STATE: any value in an app that can change
     */

    //compose elements dont have a default state so jetpack
    //compose may recompose everything again and updates ui
    //at any momentand again gets its default value
    //composables by default do not automatically remember their state

    //with REMEMBER, compose functions can store objects in memory across compositions
    // mutableStateOf shows an observable element, when description or imageId change,
    //recomposition activates and UI will update withj the new value

    //when compose recomposes ui set  values to its inital value again
    //then compose calls again all the function (recomposes the UI)

    //with mutableStateOf  i can observe an state and its changes and trigger recomposition
    //each time the app data is updated
    var description by remember{ mutableStateOf( stringsResourcesList[0] ) }
    var imageId by remember{ mutableStateOf( imageResourcesList[0] ) }

    var timesToSqueezeLemon: Int = 0
    val color = Color(0xFCBFECD6)
    var randomValue: Int = 0
    randomValue =  (2..4).random()
    println("RandomValue: $randomValue")
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {

                when(imageId){
                    imageResourcesList[0] -> {
                        //take the next string resource and the next image id
                        imageId = imageResourcesList[1]
                        description = stringsResourcesList[1]

                    }
                    imageResourcesList[1] -> {
                        if( timesToSqueezeLemon == randomValue ){
                            imageId = imageResourcesList[2]
                            description = stringsResourcesList[2]
                        }
                        timesToSqueezeLemon++
                        println("timesToSqueezeLemon: $timesToSqueezeLemon")

                    }
                    imageResourcesList[2] -> {
                        imageId = imageResourcesList[3]
                        description = stringsResourcesList[3]
                    }
                    imageResourcesList[3] -> {
                        imageId = imageResourcesList[0]
                        description = stringsResourcesList[0]
                    }
                }

            },

            modifier = Modifier
                .border(2.dp, color, RoundedCornerShape(14.dp))
                .background(color, shape = RoundedCornerShape(14.dp)),

            colors = ButtonDefaults.buttonColors( containerColor = color),

        ){
            Image(painter = painterResource(id = imageId),   contentDescription = null)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = description),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }

}



@Preview(showSystemUi = true)
@Composable
fun LemonadeApp(){
    LemonImageAndText( )

}



/*
@Composable
fun DiceWithButtonImage(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
){

    var result by remember { mutableStateOf(1) }
    val imageResult = when(result){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column (
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = imageResult),
            contentDescription = result.toString()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = (1..6).random()
            }
        ){
            Text(
                stringResource(id = R.string.roll),
                fontSize = 24.sp
            )
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun DiceRollerApp(){
    DiceWithButtonImage()
}
*/