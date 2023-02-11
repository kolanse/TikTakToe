package com.kolanse.tiktaktoe.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.util.UiEvent
import com.kolanse.tiktaktoe.R
import com.kolanse.tiktaktoe.navigation.Route

@Composable
fun PlayerTypeSelection(onNavigate: (UiEvent.Navigate) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        TitleText(text = stringResource(R.string.how_would_u_like_to_play))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        )
        FilledButton(
            modifier = Modifier,
            buttonText = stringResource(R.string.host_game),
            onClick = {onNavigate(UiEvent.Navigate(Route.RESULTS))}
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
        )
        OutlinedButton(
            modifier = Modifier,
            buttonText = stringResource(R.string.join_game),
            onClick = {onNavigate(UiEvent.Navigate(Route.RESULTS))}
        )
    }
}

@Preview
@Composable
fun PreviewPlayerTypeSelection() {
    PlayerTypeSelection({})
}