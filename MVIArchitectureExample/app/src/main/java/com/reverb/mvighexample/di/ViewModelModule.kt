package com.reverb.mvighexample.di

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule  = module{

    viewModel {
        UsersViewModel(githubRepository = get())
    }

    viewModel {parameters->
        ReposViewModel(
            userId = parameters.get(),
            githubRepository = get()
        )
    }

}