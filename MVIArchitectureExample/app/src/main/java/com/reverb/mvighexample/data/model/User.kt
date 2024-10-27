package com.reverb.mvighexample.data.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("login") val userId: String = "",
    @SerializedName("avatar_url") val avatarUrl: String = "",
    @SerializedName("html_url") val htmlUrl: String = "",
)

fun buildUserPreview() = User(
    userId = "51235654",
    avatarUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVA_HrQLjkHiJ2Ag5RGuwbFeDKRLfldnDasw&s",
    htmlUrl = "https://github.com/J-Pinos3"
)