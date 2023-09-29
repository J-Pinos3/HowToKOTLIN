package com.example.testpeletas

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.widget.MediaController
import android.widget.VideoView



class videovista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videovista)

        var vvLocal = findViewById<VideoView>(R.id.vvLocal)
        var mcLocal = MediaController(this)
        mcLocal.setAnchorView(vvLocal)
        var  path = "android.resource://" + packageName + "/" + R.raw.video
        vvLocal.setVideoURI(Uri.parse(path))
        vvLocal.setMediaController(mcLocal)
        //**************************************************

        var vvWeb = findViewById<VideoView>(R.id.vvWeb)
        var mcWeb = MediaController(this)

        mcWeb.setAnchorView(vvWeb)
        vvWeb.setVideoPath("https://jotajotavm.com/img/video.mp4")
        vvWeb.setMediaController(mcWeb)

    }
}