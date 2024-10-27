package com.reverb.mvighexample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.reverb.mvighexample.ui.feature.repos.ReposContract
import com.reverb.mvighexample.ui.feature.repos.ReposViewModel
import com.reverb.mvighexample.ui.feature.repos.composables.ReposScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ReposScreenDestination(userId: String, navController: NavController) {
    //val viewModel = getViewModel<ReposViewModel> { parametersOf(userId) }
    val viewModel = koinViewModel<ReposViewModel>{ parametersOf(userId) }
    ReposScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is ReposContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        },
    )
}

