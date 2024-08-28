package com.reverb.antonleivjetpackcourse.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.reverb.antonleivjetpackcourse.R
import com.reverb.antonleivjetpackcourse.model.MediaItem
import com.reverb.antonleivjetpackcourse.model.getMedia


@Preview
@Composable
fun MediaList(modifier: Modifier = Modifier){


    LazyVerticalGrid(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_xsmall)),
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),//num cells according this min size
        modifier = modifier
        //verticalArrangement = Arrangement.spacedBy(4.dp) //for lazy column
        //horizontalArrangement = Arrangement.spacedBy(4.dp) //for lazy row
    ){
        /*with LazyVerticalGrid this function does not work
        items( getMedia() ){item ->
            MediaListItem(item)
        }
        so use function below*/
        items(getMedia().size ){ index ->
            //MediaListItem(item = getMedia()[index], Modifier.padding(2.dp))
            MediaListItem(item = getMedia()[index], Modifier.padding(dimensionResource(R.dimen.padding_xsmall)))
        }
    }
}


//@Preview(   showBackground = true  )
@Composable
fun MediaListItem(item: MediaItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
        //modifier = Modifier.width(200.dp) for better view in lazyrow and below's fillmaxwidt has no efect
    ){
        Box(
            modifier = Modifier
                .height(dimensionResource(R.dimen.cell_thumb_heigh))
                .fillMaxWidth(),
            //contentAlignment = Alignment.Center
        ){


            /**     coil 1 [rememberImagePainter] is deprecated
            Image(
                painter = rememberImagePainter(
                data = "https://loremflickr.com/cache/resized/65535_53665376338_afe9dc3427_320_240_nofilter.jpg",

                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
             */



            // COIL 2
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


            //Icon(painter = painterResource(id = 0), contentDescription = null) OTHER OPTION


        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(dimensionResource(R.dimen.padding_medium))
        ){
            Text(text = item.title,
                style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Preview
@Composable
fun MediaListPreview(){
    MediaList()
}