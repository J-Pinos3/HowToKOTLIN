package com.reverb.antonleivjetpackcourse.model

import com.reverb.antonleivjetpackcourse.model.MediaItem.Type

data class MediaItem(
    val id: Int,
    val title: String,
    val thumb: String,
    val type: Type

){

    enum class Type { PHOTO, VIDEO }

}

fun getMedia() = (1..11).map{
    MediaItem(
        id = it,
        title = "Title $it",
        thumb = "https://picsum.photos/seed/$it/200/300",
        type = if(it %3 == 0) Type.VIDEO else Type.PHOTO
    )
}