package com.reverb.mvighexample.di

import com.reverb.mvighexample.data.GithubRepository
import com.reverb.mvighexample.data.GithubRepositoryImpl
import org.koin.dsl.module

val repositoryModule  = module{

    factory<GithubRepository>{
        GithubRepositoryImpl(
            githubApi = get()
        )
    }

}