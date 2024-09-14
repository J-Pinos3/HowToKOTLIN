package com.reverb.mvighexample.data

import com.reverb.mvighexample.data.model.Repo
import com.reverb.mvighexample.data.model.User
import com.reverb.mvighexample.data.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET(Endpoints.GET_USERS)
    suspend fun getUsers(): List<User>

    @GET(Endpoints.GET_USER)
    suspend fun getUser(@Path("userLogin") userID: String): UserDetail?

    @GET(Endpoints.GET_REPOS_BY_USER)
    suspend fun getRepos(@Path("userLogin") userID: String): List<Repo>
}