package com.reverb.mvighexample.ui.feature.repos.composables

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.reverb.mvighexample.data.model.RepoPreview
import com.reverb.mvighexample.data.model.buildUserDetailPreview
import com.reverb.mvighexample.ui.base.SIDE_EFFECTS_KEY
import com.reverb.mvighexample.ui.feature.common.NetworkError
import com.reverb.mvighexample.ui.feature.common.Progress
import com.reverb.mvighexample.ui.feature.repos.ReposContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReposScreen(
    state: ReposContract.State,
    effectFlow: Flow<ReposContract.Effect>?,
    onEventSent: (event: ReposContract.Event) -> Unit,
    onNavigationRequested: (ReposContract.Navigation) -> Unit
){
    LaunchedEffect(SIDE_EFFECTS_KEY){
        effectFlow?.onEach {effect->
            when(effect){
                ReposContract.Effect.Navigation.Back->{
                    onNavigationRequested(ReposContract.Effect.Navigation.Back)
                }
            }
        }?.collect()
    }


    Scaffold(
        topBar = { ReposTopBar {
            onEventSent(ReposContract.Event.BackButtonClicked)
        } }
    ) {
        when {
            state.isUserLoading || state.isReposLoading -> Progress()
            state.isError -> NetworkError { onEventSent(ReposContract.Event.Retry) }
            else -> {
                state.user?.let { user ->
                    ReposList(
                        header = { ReposListHeader(userDetail = user) },
                        reposList = state.reposList
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun RepoScreenSuccessPreview(){
    val repos = List(3) { RepoPreview.repo }
    ReposScreen(
        state = ReposContract.State(
            user = buildUserDetailPreview(),
            reposList = repos,
            isUserLoading = false,
            isReposLoading = false, isError = false
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {}
    )
}

@Preview(showBackground = true)
@Composable
fun RepoScreenErrorPreview(){
    ReposScreen(
        state = ReposContract.State(
            user = buildUserDetailPreview(),
            reposList = repos,
            isUserLoading = false,
            isReposLoading = false, isError = true
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested ={}
    )
}