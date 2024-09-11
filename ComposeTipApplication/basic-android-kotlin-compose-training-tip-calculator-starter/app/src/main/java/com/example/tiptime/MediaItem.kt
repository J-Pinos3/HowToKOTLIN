package com.example.tiptime

import android.os.Build
import android.text.format.DateFormat
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

data class MediaItem(
    val id: Int,
    val image: String,
    val title: String,
    val artist: String,
    val year: String
)
//https://medium.com/@arshamjafari85/mastering-date-and-time-handling-in-kotlin-35cc1192d226
//https://stackoverflow.com/questions/57402045/how-to-format-in-kotlin-date-in-string-or-timestamp-to-my-preferred-format

@RequiresApi(Build.VERSION_CODES.O)
fun getMedia() = (1..10).map {
    val sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val currentDate =  LocalDate.now().plusDays(it.toLong()).format(sdf)


    MediaItem(
        id = it,
        image = "https://picsum.photos/seed/$it/200/300",
        title = "Title Art $it",
        artist = "Artist $it",
        year = currentDate
    )
}