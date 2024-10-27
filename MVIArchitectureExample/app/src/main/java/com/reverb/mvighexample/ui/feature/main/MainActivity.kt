package com.reverb.mvighexample.ui.feature.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.reverb.mvighexample.ui.feature.common.BroadcastReceiver
import com.reverb.mvighexample.ui.feature.common.ConnectionSnackBar
import com.reverb.mvighexample.ui.navigation.AppNavigation
import com.reverb.mvighexample.ui.theme.MVIGhExampleTheme

class MainActivity : ComponentActivity() {

    private val connectivityReceiver = BroadcastReceiver{isConnected ->
        setContent{
            MVIGhExampleTheme{
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                    ConnectionSnackBar(isConnected = isConnected)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        setContent {
            MVIGhExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(  color = MaterialTheme.colorScheme.background  ) {
                    AppNavigation()
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectivityReceiver)
    }
}

