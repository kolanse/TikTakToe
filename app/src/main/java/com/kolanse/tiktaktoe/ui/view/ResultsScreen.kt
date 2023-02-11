package com.kolanse.tiktaktoe.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.util.UiEvent
import com.kolanse.tiktaktoe.R
import com.kolanse.tiktaktoe.navigation.Route
import com.kolanse.tiktaktoe.ui.theme.Typography
import com.kolanse.tiktaktoe.ui.theme.titleTextColor

@Composable
fun ResultsScreen(onNavigate: (UiEvent.Navigate) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.result_image),
            contentDescription = "results image",
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(22.dp)
        )
        Text(
            color = titleTextColor,
            style = Typography.titleMedium,
            text = stringResource(R.string.you_lose),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
        Text(
            color = titleTextColor,
            style = Typography.titleLarge.copy(fontSize = 40.sp),
            text = stringResource(R.string.score),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        )
        FilledButton(
            modifier = Modifier,
            buttonText = stringResource(R.string.new_game),
            onClick = {onNavigate(UiEvent.Navigate(Route.PLAYER_TYPE))}
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
        )
        OutlinedButton(
            modifier = Modifier,
            buttonText = stringResource(R.string.exit_game),
            onClick = {onNavigate(UiEvent.Navigate(Route.PLAYER_TYPE))}
        )
    }
}

@Preview
@Composable
fun PreviewResultScreen() {
    ResultsScreen({})
}