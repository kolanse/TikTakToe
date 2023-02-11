package com.kolanse.tiktaktoe.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.util.UiEvent
import com.kolanse.tiktaktoe.R
import com.kolanse.tiktaktoe.ui.theme.Typography
import com.kolanse.tiktaktoe.ui.theme.lightPurple
import com.kolanse.tiktaktoe.ui.theme.titleTextColor

@Composable
fun GameInterfaceScreen(onNavigate: (UiEvent.Navigate) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            color = lightPurple,
            style = Typography.titleLarge.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            text = stringResource(R.string.round),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ColoredText("You")
            Text(
                color = titleTextColor,
                style = Typography.titleLarge.copy(fontSize = 40.sp),
                text = stringResource(R.string.score),
                textAlign = TextAlign.Center,
            )
            ColoredText("Wandyy23")
        }
    }
}

@Preview
@Composable
fun PreviewGameScreen() {
    GameInterfaceScreen({})
}