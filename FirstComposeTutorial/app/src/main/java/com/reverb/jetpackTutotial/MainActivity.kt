package com.reverb.jetpackTutotial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reverb.jetpackTutotial.ui.theme.TutorialOfComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TutorialOfComposeTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    /*
                    GreetingImage(
                        message = getString(R.string.feliz_cumplea_os_melvin),
                        from = getString(R.string.signature_text),
                        modifier = Modifier.padding(8.dp)
                    )


                    JetPackDeclaration()

                    TaskScreen()

                    DividedScreen()

                    ComposeQuadrantApp()
                    */
                    ContactScreen()

                }
            }


        }
    }
}

@Composable
private fun ContactScreen(){
    Column(

        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color(0xFF86F382))
            .fillMaxSize()
    ){
        Row(modifier = Modifier.padding(top = 20.dp)){
            BasicInfoCard()
        }

        Row(modifier = Modifier.padding(bottom = 20.dp)){
            ContactCard()
        }
    }
}

@Composable
private fun ContactCard(){
    val phoneImage = painterResource(id = R.drawable.ic_phone)
    val shareImage = painterResource(id = R.drawable.ic_share)
    val mailImage = painterResource(id = R.drawable.ic_mail)
    val icColor = Color(0xFF088003)
    Column{
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier =  Modifier.padding(vertical = 5.dp)
        ){
            Icon(
                painter = phoneImage ,
                contentDescription = null,
                tint = icColor
            )


            Text(
                text = "(+593) 0962151117",
                modifier = Modifier.padding(start = 15.dp)
            )
        }

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier =  Modifier.padding(vertical = 5.dp)
        ){
            Icon(
                painter = shareImage ,
                contentDescription = null,
                tint = icColor
            )

            Text(
                text = "jose.pinos.guitar",
                modifier = Modifier.padding(start = 15.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier =  Modifier.padding(vertical = 5.dp)
        ){
            Icon(
                painter = mailImage ,
                contentDescription = null,
                tint = icColor
            )

            Text(
                text = "Reverb1@outlook.es",
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}

@Composable
private fun BasicInfoCard(){
    val image = painterResource(id = R.drawable.puppyimage)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        
        Text(
            text = "José Pinos",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(10.dp)
        )
        
        Text(
            text = "Junior Android Developer",
            style = TextStyle(color = Color.Black)
        )
    }
}



@Composable
fun ComposeQuadrantApp(){
    Column(Modifier.fillMaxWidth()){
        Row(Modifier.weight(1f)){
            ComposableInfoCard(
                title = "Text composable",
                description = "Displays text and follows the recommended Material Design guidelines.",
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )

            ComposableInfoCard(
                title = "Image composable",
                description = "Creates a composable that lays out and draws a given Painter class object.",
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }

        Row(Modifier.weight(1f)){
            ComposableInfoCard(
                title = "Row composable",
                description = "A layout composable that places its children in a horizontal sequence.",
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )

            ComposableInfoCard(
                title = "Column composable",
                description = "A layout composable that places its children in a vertical sequence.",
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,    description:String,
    backgroundColor: Color,    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}




@Composable
fun TopLeftCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFEADDFF))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Text composable",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .wrapContentHeight()
        )

        Text(
            text = "Displays text and follows the recommended Material Design guidelines.",
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun TopRightCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFD0BCFF))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Image composable",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .wrapContentHeight()
        )

        Text(
            text = "Creates a composable that lays out and draws a given Painter class object.",
            textAlign = TextAlign.Justify
        )
    }
}

// El mismo ajuste en BottomLeftCard y BottomRightCard

@Composable
fun BottomLeftCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFB69DF8))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Row composable",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .wrapContentHeight()
        )

        Text(
            text = "A layout composable that places its children in a horizontal sequence.",
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun BottomRightCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6EDFF))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Column composable",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .wrapContentHeight()
        )

        Text(
            text = "A layout composable that places its children in a vertical sequence.",
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun DividedScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier.weight(1f) ){
            TopLeftCard(modifier = modifier.weight(1f))

            TopRightCard(modifier = modifier.weight(1f))
        }
        
        Row(modifier.weight(1f)){
            BottomLeftCard(modifier = modifier.weight(1f))
            BottomRightCard(modifier = modifier.weight(1f))
        }
    }
}



@Composable
fun TaskScreen(modifier: Modifier = Modifier){
    val image = painterResource(id = R.drawable.ic_task_completed)

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            text = "All tasks completed",
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 8.dp)
        )

        Text(
            text = "Nice work!",
            fontSize = 16.sp
        )
    }
}



@Composable
fun JetPackHeader(modifier: Modifier = Modifier){
    val image = painterResource(id = R.drawable.composeheader)
    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
    )
}

@Composable
fun JetPackDeclaration(modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
    ) {
        JetPackHeader()

        Text(
            text = stringResource(id = R.string.jp_header),
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
        )

        Text(
            text = stringResource(id = R.string.jp_intro),
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.jp_declaration),
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}



@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier){
    val image = painterResource(id = R.drawable.androidparty)
    Box(modifier = modifier){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}



@Preview(showBackground = true,
    name = "First Preview",
    showSystemUi = true)
@Composable
fun BirthdayCardPreview(){
    TutorialOfComposeTheme {
        //GreetingImage(message = "Happy Birthday Joe", from = "From Emma")
        //JetPackDeclaration()
        //TaskScreen()
        //DividedScreen()
        ContactScreen()
    }
}


