package com.reverb.mvighexample.ui.feature.common


import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color



@Composable
fun ConnectionSnackBar(isConnected: Boolean){
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    
    LaunchedEffect(isConnected){
        if(!isConnected){
            snackbarHostState.showSnackbar(
                message = "No Internet Connection",
                duration = SnackbarDuration.Long
            )
        }
    }
    
    SnackbarHost (hostState = snackbarHostState,
        snackbar = {

            Snackbar(action = {
                TextButton(onClick = { snackbarHostState.currentSnackbarData?.dismiss() }) {
                    Text(text = "Dismiss")
                }
            },
                containerColor = Color.Yellow){
                //this is content of the composable
                Text(text = "No internet connection", color = Color.Black)
            }
        })
}

