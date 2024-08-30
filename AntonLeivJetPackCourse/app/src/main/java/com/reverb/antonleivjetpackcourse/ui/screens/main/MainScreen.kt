package com.reverb.antonleivjetpackcourse.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.reverb.antonleivjetpackcourse.ui.MyMoviesApp

@Composable
fun MainScreen(navController: NavHostController) {

    MyMoviesApp{
        Scaffold(
            topBar = { MainAppBar() }
        ) { padding ->
            MediaList(
                navController = navController,
                modifier = Modifier.padding(padding)
            )
        }
    }

}