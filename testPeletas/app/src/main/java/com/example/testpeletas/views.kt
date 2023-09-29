package com.example.testpeletas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import com.squareup.picasso.Picasso

class views : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_views)

        /*
        ESTE IMV ES DE LA IMÁGEN CARGADA DESDE INTERNET
        var ivEjemplo = findViewById<ImageView>(R.id.ImgUrl)
        val imageURL = "https://d2emr0qhzqfj88.cloudfront.net/s3fs-public/2019-12/02_John-Petrucci_03_134.jpg"
        Picasso.get().load(imageURL).into(ivEjemplo)
        */

        var webView = findViewById<WebView>(R.id.webView)
        var webSettings: WebSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true
        webView.setWebViewClient(WebViewClient())

        webView.loadUrl("https://www.google.com")




        try {
            var videoViewInternet = findViewById<VideoView>(R.id.videoViewInternet)
            var mcWeb = MediaController(this)

            mcWeb.setAnchorView(videoViewInternet)
            videoViewInternet.setVideoPath("http://jotajotavm.com/img/video.mp4")
            videoViewInternet.setMediaController(mcWeb)
        } catch (e: Exception) {
            println(e.message)
        }


    }
}