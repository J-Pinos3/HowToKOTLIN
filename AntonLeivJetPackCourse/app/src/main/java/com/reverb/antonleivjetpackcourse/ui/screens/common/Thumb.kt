package com.reverb.antonleivjetpackcourse.ui.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.reverb.antonleivjetpackcourse.R
import com.reverb.antonleivjetpackcourse.model.MediaItem

@Composable
fun Thumb(item: MediaItem, modifier: Modifier = Modifier){

    Box(
        modifier = modifier
            .height(dimensionResource(id = R.dimen.cell_thumb_heigh))
            .fillMaxWidth()
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data( item.thumb )
                //.transformations(CircleCropTransformation())
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop

        )

        if ( item.type == MediaItem.Type.VIDEO){
            Icon(
                imageVector = Icons.Default.PlayCircleOutline,
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.cell_play_icon_size))//102.dp
                    .align(Alignment.Center),//can also center in the box with the contentAlignment
                tint = Color.White,
            )
        }
    }

}