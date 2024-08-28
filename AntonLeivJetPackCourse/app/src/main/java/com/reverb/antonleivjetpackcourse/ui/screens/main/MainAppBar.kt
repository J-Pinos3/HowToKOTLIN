package com.reverb.antonleivjetpackcourse.ui.screens.main

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CameraIndoor
import androidx.compose.material.icons.filled.SouthAmerica
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(){
    val ctx = LocalContext.current
    TopAppBar(
        title = {  Text(text = "My App de Compose")  },
        navigationIcon = {
            IconButton(
                onClick = { Toast.makeText(ctx,"Hi Brrro", Toast.LENGTH_LONG).show() },
            ){
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        },
        actions = {
            AppBarAction(
                imageVector = Icons.Default.SouthAmerica,
                onClick = fun ():Unit{ }//or just{  }
            )

            AppBarAction(
                imageVector = Icons.Default.CameraIndoor,
                onClick = {     }
            )

//            IconButton(
//                onClick = { },
//                content = {
//                    Icon(
//                        imageVector = Icons.Default.CameraIndoor,
//                        contentDescription = null, tint = Color.Black
//                    )
//                }
//            )
        }
    )
}

@Composable
private fun AppBarAction(
    imageVector: ImageVector,
    onClick: () -> Unit
){
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                //imageVector = Icons.Default.SouthAmerica,
                imageVector = imageVector,
                contentDescription = null, tint = Color.Black
            )
        }
    )
}