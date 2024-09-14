package com.reverb.mvighexample.data

import com.reverb.mvighexample.data.model.Repo
import com.reverb.mvighexample.data.model.User
import com.reverb.mvighexample.data.model.UserDetail

interface GithubRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUser(userId: String): Result<UserDetail>
    suspend fun getRepos(userId: String): Result<List<Repo>>
}