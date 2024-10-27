package com.reverb.mvighexample.di

import com.reverb.mvighexample.ui.feature.repos.ReposViewModel
import com.reverb.mvighexample.ui.feature.users.UsersViewModel
import org.koin.dsl.module
//import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.*

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