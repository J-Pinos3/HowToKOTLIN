package com.reverb.antonleivjetpackcourse.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.reverb.antonleivjetpackcourse.model.getMedia
import com.reverb.antonleivjetpackcourse.ui.screens.common.Thumb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    mediaId: Int
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Detail Screen $mediaId",
            color = Color.Blue,
            style = MaterialTheme.typography.titleMedium
        )
    }

    val mediaItem = remember {
        getMedia().first {  it.id == mediaId  }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Item #${mediaId}") },
            )
        }
    ){
        Thumb(item = mediaItem, Modifier.padding(it) )
    }
}