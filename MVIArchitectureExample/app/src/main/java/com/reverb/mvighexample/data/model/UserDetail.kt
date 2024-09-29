package com.reverb.mvighexample.data.model

import com.google.gson.annotations.SerializedName

data class UserDetail (
    @SerializedName("avatar_url") val avatarUrl: String = "",
    @SerializedName("html_url") val htmlUrl: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("location") val location: String? = "",
    @SerializedName("blog") val blog: String = "",
    @SerializedName("public_repos") val publicRepos: Int = 0,
    @SerializedName("followers") val followers: Int = 0,
    @SerializedName("following") val following: Int = 0,
)

fun buildUserDetailPreview() = UserDetail(
    avatarUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVA_HrQLjkHiJ2Ag5RGuwbFeDKRLfldnDasw&s",
    htmlUrl = "https://github.com/51234843",
    name = "Jhon Doe",
    location = "Ecuador",
    publicRepos = 20, followers = 20,
    following = 10
)