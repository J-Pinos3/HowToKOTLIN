package com.reverb.mvighexample.ui.feature.repos

import android.media.effect.EffectFactory
import com.reverb.mvighexample.data.model.Repo
import com.reverb.mvighexample.data.model.UserDetail
import com.reverb.mvighexample.ui.base.ViewEvent
import com.reverb.mvighexample.ui.base.ViewSideEffect
import com.reverb.mvighexample.ui.base.ViewState

class ReposContract {

    sealed class Event: ViewEvent{
        object Retry: Event()
        object BackButtonClicked: Event()
    }

    data class State(
        val user: UserDetail?,
        val reposList: List<Repo>,
        val isUserLoading: Boolean,
        val isReposLoading: Boolean,
        val isError: Boolean
    ):ViewState

    sealed class Effect: ViewSideEffect{
        sealed class Navigation:Effect(){
            object Back: Navigation()
        }
    }

}