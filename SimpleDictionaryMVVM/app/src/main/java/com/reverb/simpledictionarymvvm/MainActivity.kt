package com.reverb.simpledictionarymvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.reverb.simpledictionarymvvm.ui.theme.SimpleDictionarymvvmTheme
import com.reverb.simpledictionarymvvm.view.DictionaryScreen
import com.reverb.simpledictionarymvvm.viewmodel.DictionaryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleDictionarymvvmTheme {
                val dictionaryViewModel: DictionaryViewModel = viewModel()
                DictionaryScreen(viewModel = dictionaryViewModel)
            }
        }
    }
}
