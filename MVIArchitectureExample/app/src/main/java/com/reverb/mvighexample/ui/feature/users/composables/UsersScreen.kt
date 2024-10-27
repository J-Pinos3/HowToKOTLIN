package com.reverb.mvighexample.ui.feature.users.composables

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.reverb.mvighexample.R
import com.reverb.mvighexample.data.model.User
import com.reverb.mvighexample.data.model.buildUserPreview
import com.reverb.mvighexample.ui.base.SIDE_EFFECTS_KEY
import com.reverb.mvighexample.ui.feature.common.NetworkError
import com.reverb.mvighexample.ui.feature.common.Progress
import com.reverb.mvighexample.ui.feature.users.UsersContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UsersScreen(
    state: UsersContract.State,
    effectFlow: Flow<UsersContract.Effect>?,
    onEventSent: (event: UsersContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: UsersContract.Effect.Navigation) -> Unit
){

    val snackbarState: SnackbarHostState = remember{ SnackbarHostState() }
    val snackbarMessage = stringResource(id = R.string.users_screen_snackbar_loaded_message)

    LaunchedEffect( SIDE_EFFECTS_KEY ){
        effectFlow?.onEach {effect ->
            when(effect){
                is UsersContract.Effect.DataWasLoaded -> {
                    snackbarState.showSnackbar(
                        message = snackbarMessage,
                        duration = SnackbarDuration.Short
                    )
                }

                is UsersContract.Effect.Navigation.ToRepos -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarState)  },
        topBar = { UsersTopBar() }
    ){
        when{
            state.isLoading -> Progress()
            state.isError -> NetworkError{ onEventSent(UsersContract.Event.Retry) }
            else -> UsersList(users = state.users){
                onEventSent(UsersContract.Event.UserSelection(it))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun UsersScreenSuccessPreview(){
    val users = List(3){ buildUserPreview() }
    UsersScreen(
        state = UsersContract.State(
            users = users,
            isLoading = false,
            isError = false
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {}
    )
}



@Preview(showBackground = true)
@Composable
fun UsersScreenErrorPreview(){
    UsersScreen(
        state = UsersContract.State(
            users = emptyList<User>(),
            isLoading = false,
            isError = true
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {}
    )
}


