package com.reverb.antonleivjetpackcourse.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.reverb.antonleivjetpackcourse.ui.theme.AntonLeivJetPackCourseTheme

@Composable
fun MyMoviesApp(content: @Composable () -> Unit) {
    AntonLeivJetPackCourseTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}