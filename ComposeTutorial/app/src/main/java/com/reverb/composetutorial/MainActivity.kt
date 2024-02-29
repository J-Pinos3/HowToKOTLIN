package com.reverb.composetutorial

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reverb.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column {
                ComposeTutorialTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        MessageCard(Message("Android","Jetpack Compose"))
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))


                CustomButton(context = this@MainActivity)
            }



            /*
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
            */
        }
    }
}

@Composable
fun MessageCard(msg: Message){
    Row (modifier = Modifier.padding(all = 8.dp) ){
        Image(painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)//la imágen es de 40dp
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary, //Color.Green
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding( all = 4.dp  )
            )
            Spacer(modifier = Modifier.width(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, shadowElevation =  3.dp) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}


@Composable
fun CustomButton(context: Context){
    Button(onClick = {
        val intent = Intent(context , ListActivity::class.java)
        context.startActivity(intent)
    },
    modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Next Activity")
    }
}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)

@Preview
@Composable
fun previewMessageCard(){

    Column {
        ComposeTutorialTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                MessageCard(Message("Colleaguess", "Jetpack Compose, it's great!"))
            }
        }
    }
}


/*
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
        ComposeTutorialTheme {
            Greeting("Android")
        }
    }
*/