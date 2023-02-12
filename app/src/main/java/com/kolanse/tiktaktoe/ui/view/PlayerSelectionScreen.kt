import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.Navigation.findNavController
import com.example.core.util.UiEvent
import com.kolanse.tiktaktoe.ConnectionType
import com.kolanse.tiktaktoe.R
import com.kolanse.tiktaktoe.navigation.Route
import com.kolanse.tiktaktoe.ui.view.OutlinedButton
import com.kolanse.tiktaktoe.ui.theme.Typography
import com.kolanse.tiktaktoe.ui.theme.lightPurple
import com.kolanse.tiktaktoe.ui.theme.titleTextColor
import com.kolanse.tiktaktoe.ui.view.FilledButton
import com.kolanse.tiktaktoe.ui.view.LoadingState
import com.kolanse.tiktaktoe.ui.view.TitleText
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerSelectionScreen(onNavigate: (UiEvent.Navigate) -> Unit) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }
    var currentBottomSheet: ConnectionType? by remember {
        mutableStateOf(null)
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            Spacer(modifier = Modifier.height(1.dp))
            currentBottomSheet?.let {
                SheetLayout(bottomSheetType = it, onNavigate = onNavigate)
            }
        },
        modifier = Modifier.fillMaxSize(),
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {
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
                onClick = { /*onNavigate(UiEvent.Navigate(Route.GAME))*/
                    currentBottomSheet = ConnectionType.HOTSPOT
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(26.dp)
            )
            OutlinedButton(
                modifier = Modifier,
                buttonText = stringResource(R.string.join_game),
                onClick = {
                    currentBottomSheet = ConnectionType.WIFI
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                }
            )
        }
    }
}

@Composable
fun BottomSheet(title: String, subtitle: String, loadingText: String, onNavigate: (UiEvent.Navigate) -> Unit) {
    Column(
        modifier = Modifier
            .height(400.dp)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val mCheckedState = remember { mutableStateOf(false) }
            Text(
                color = titleTextColor,
                style = Typography.titleLarge,
                text = title,
                textAlign = TextAlign.Start,
                modifier = Modifier.clickable { onNavigate(UiEvent.Navigate(Route.GAME)) }
            )
            Switch(
                checked = mCheckedState.value,
                onCheckedChange = {
                    mCheckedState.value = it
                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = lightPurple,
                    checkedThumbColor = Color.White,
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = subtitle,
            style = Typography.titleSmall.copy(fontSize = 14.sp, lineHeight = 16.8.sp)
        )

        LoadingState(loadingText)
    }
}

@Composable
fun SheetLayout(
    bottomSheetType: ConnectionType,
    onNavigate: (UiEvent.Navigate) -> Unit
) {

    when (bottomSheetType) {
        ConnectionType.HOTSPOT -> BottomSheet(
            stringResource(R.string.turn_on_hotspot),
            stringResource(R.string.turn_on_hotspot_to_join),
            stringResource(R.string.waiting_for_a_connection),
            onNavigate
        )
        ConnectionType.WIFI -> BottomSheet(
            stringResource(R.string.turn_on_wifi),
            stringResource(R.string.turn_on_wifi_to_connect),
            stringResource(R.string.searching_for_a_connection),
            onNavigate
        )
    }

}

@Preview
@Composable
fun PreviewBottomSheetScreen() {
    PlayerSelectionScreen {}
}

@Preview
@Composable
fun PreviewBottomSheet() {
    BottomSheet("", "", "") {}
}