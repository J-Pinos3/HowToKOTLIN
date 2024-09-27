package com.reverb.mvighexample.ui.feature.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.reverb.mvighexample.R


@Composable
fun NetworkError(
    modifier: Modifier = Modifier,
    onRetryButtonClick: () -> Unit
){

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.network_error_title),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.network_error_description),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding( dimensionResource(id = R.dimen.padding_medium) ),
            textAlign = TextAlign.Center
        )
        
        Button(onClick = {  onRetryButtonClick()  }) {
            Text(
                text = stringResource(id = R.string.network_error_retry_button_text).uppercase()
            )
        }
    }
}

@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
fun NetworkErrorPreview(){
    NetworkError(   modifier = Modifier,  onRetryButtonClick = {}   )
}
