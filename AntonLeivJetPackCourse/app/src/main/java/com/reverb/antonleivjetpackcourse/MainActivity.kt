package com.reverb.antonleivjetpackcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.dp
import com.reverb.antonleivjetpackcourse.ui.MyMoviesApp
import com.reverb.antonleivjetpackcourse.ui.screens.main.MainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoviesApp {

                /*
                    Greeting(name = "Jhonny")
                    MediaList()

                    var text by rememberSaveable {  mutableStateOf("")  }
                    val (value, onValueChange) = rememberSaveable {  mutableStateOf("")  }
                    StateSample(
                        text = text,//value
                        onValueChange = {  text = it  }//onValueChange
                    )
                */

                MainScreen()
            }
        }
    }

}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun StateSample(
    text:String,
    onValueChange: (String) -> Unit
){
    //now lets make a global state, and add value and onValueChange as parameters
    //when we rotate the device, activity is recreated
    //and we'll lose the state
    //var text by remember {  mutableStateOf("")  }
    //rememberSaveable remembers value when activity is recreated
    //or when device rotates
    //var text by rememberSaveable {  mutableStateOf("")  }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(64.dp),
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            value = text,
            //onValueChange ={  text = it  },
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .background(Companion.Yellow)
                .padding(bottom = 8.dp, top = 8.dp)
        )
        Button(
            onClick = { onValueChange("") },
            modifier = Modifier.fillMaxWidth(),
            enabled = text.isNotEmpty()
        ){
            Text(text = "Clear")
        }
    }
}

//@Preview(  showBackground = true,  widthDp = 200,  heightDp = 100)
@Composable
fun ButtonText() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Hello World",
//            fontWeight = FontWeight.ExtraBold,
//            fontFamily = FontFamily.SansSerif,
//            textAlign = TextAlign.Justify,
//            maxLines = 2,
//            softWrap = false,
//            overflow = TextOverflow.Visible,
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Companion.Green,
                shadow = Shadow(
                    offset = Offset(5f, 5f),
                    blurRadius = 5f,
                    color = Companion.Green
                )
            )

            /*
            modifier = Modifier
            .background(Companion.LightGray)//outern border
            .border( width = 2.dp, color = Companion.Red )
            .padding(8.dp)

             .background(Companion.LightGray)//inner border
            .border( width = 2.dp, color = Companion.Red )
            .padding(8.dp)
            */
        )
    }
}






/*
Row(
modifier = Modifier
.fillMaxSize()
.background(Companion.Gray),
horizontalArrangement = Arrangement.SpaceEvenly,
verticalAlignment = Alignment.CenterVertically
){
    Greeting(
        name = "Michael",
        modifier = Modifier
            .background(Companion.Green)
    )
    Greeting(
        name = "Android",
        modifier = Modifier
            .background(Companion.Magenta)
    )
}



Column(
modifier = Modifier.fillMaxSize(),
horizontalAlignment = Alignment.CenterHorizontally
){
    Greeting(
        name = "Michael",
        modifier = Modifier
            .background(Companion.Green)
            .weight(2f)
    )
    Greeting(
        name = "Android",
        modifier = Modifier
            .background(Companion.Magenta)
            .weight(1f)
    )

}




Box(
    modifier = Modifier
        .fillMaxSize()
        .background(color = Companion.LightGray),
    contentAlignment = Alignment.Center //postion for all box, child wild follow this
){
    Greeting("Michael")
    Greeting(
        name = "Android",
        modifier = Modifier.align(Alignment.BottomEnd)
    )
}


 */
