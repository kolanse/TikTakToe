package com.kolanse.tiktaktoe.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.util.UiEvent
import com.kolanse.tiktaktoe.R
import com.kolanse.tiktaktoe.navigation.Route
import com.kolanse.tiktaktoe.ui.theme.Typography
import com.kolanse.tiktaktoe.ui.theme.boxColor
import com.kolanse.tiktaktoe.ui.theme.lightPurple
import com.kolanse.tiktaktoe.ui.theme.titleTextColor

@Composable
fun GameInterfaceScreen(onNavigate: (UiEvent.Navigate) -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            color = lightPurple,
            style = Typography.titleLarge.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            text = stringResource(R.string.round),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .border(width = 1.dp, color = lightPurple, RoundedCornerShape(12.dp))
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 24.dp, vertical = 14.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ColoredText(stringResource(R.string.you), Modifier.align(Alignment.CenterVertically))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                color = titleTextColor,
                style = Typography.titleLarge.copy(fontSize = 40.sp),
                text = stringResource(R.string.score),
            )
            ColoredText(stringResource(R.string.opponent_name), Modifier.align(Alignment.CenterVertically))
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
        GameInterface()
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp)
        )
        Spacer(modifier = Modifier.weight(1f, false))
        GameControls(onNavigate)
    }
}

@Composable
fun GameInterface() {
    LazyVerticalGrid(
        modifier = Modifier
            .height(327.dp)
            .padding(start = 24.dp),
        columns = GridCells.Adaptive(minSize = 95.dp)
    ) {
        items(9) {
            GameSquares()
        }
    }
}

@Composable
fun GameSquares() {
 Box(modifier = Modifier
     .padding(end = 20.dp, bottom = 20.dp)
     .background(color = boxColor, shape = RoundedCornerShape(15.dp))
     .width(95.dp)
     .height(92.dp))
}

@Composable
fun GameControls(onNavigate: (UiEvent.Navigate) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.end), contentDescription = "cancel turn" )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        BoldColoredText(
            text = stringResource(R.string.your_turn),
            modifier = Modifier
                .border(width = 1.dp, color = lightPurple, RoundedCornerShape(20.dp))
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            BoldColoredText(
                text = stringResource(R.string.pause_game),
                modifier = Modifier.padding(8.dp)
            )
            BoldColoredText(
                text = stringResource(R.string.end_game),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onNavigate(UiEvent.Navigate(Route.RESULTS)) }
            )
        }
    }
}

@Preview
@Composable
fun PreviewGameScreen() {
    GameInterfaceScreen {}
}