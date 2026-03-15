package com.reverb.helloworldcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reverb.helloworldcompose.ui.theme.HelloWorldComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            app()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun app(){
    
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ){

        item{
            Image(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                painter = painterResource(id = R.drawable.images),
                contentDescription = "Bob Esponja"
            )
            Text(
                modifier =  Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "AristiDevs",
                fontSize = 32.sp,
                color = Color.Black
            )
            Text(text = "Suscríbete")
            Text(text = "Hola :)")
        }

    }

}

/**
 * APP VERSION 1
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun app(){

    Column (
        modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)
        ){
        Image(
        modifier = Modifier.fillMaxWidth().height(300.dp),
        painter = painterResource(id = R.drawable.images),
        contentDescription = "Bob Esponja"
        )
        Text(
        modifier =  Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "AristiDevs",
        fontSize = 32.sp,
        color = Color.Black
        )
        Text(text = "Suscríbete")
        Text(text = "Hola :)")
    }

}
*/



/*
@Preview
@Composable
fun helloApp(){
    HelloWorldComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),  color = MaterialTheme.colorScheme.background
        ) {
            Greeting("Android")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun exampleModifier(){
    Text(
        text = "Newtoniana",
        modifier = Modifier.padding(
            vertical = 5.dp,
            horizontal = 2.dp
        )
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldComposeTheme {
        Greeting("Pepe")
    }
}

@Preview(showBackground = true)
@Composable
fun SecondPreview(){
    HelloWorldComposeTheme {
        Greeting(name = "Policancha")
    }
}
*/