package com.reverb.mvighexample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.reverb.mvighexample.ui.feature.repos.ReposViewModel
import com.reverb.mvighexample.ui.feature.users.UsersContract
import com.reverb.mvighexample.ui.feature.users.UsersViewModel
import com.reverb.mvighexample.ui.feature.users.composables.UsersScreen
//import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun UsersScreenDestination(navController: NavController) {
    //val viewModel = getViewModel<UsersViewModel>{ }
    val viewModel = koinViewModel<UsersViewModel>()
    UsersScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event ->  viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is UsersContract.Effect.Navigation.ToRepos) {
                navController.navigateToRepos(navigationEffect.userId)
            }
        }
    )
}