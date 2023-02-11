package com.kolanse.tiktaktoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kolanse.tiktaktoe.navigation.Route
import com.kolanse.tiktaktoe.navigation.navigate
import com.kolanse.tiktaktoe.ui.theme.TikTakToeTheme
import com.kolanse.tiktaktoe.ui.view.GameInterfaceScreen
import com.kolanse.tiktaktoe.ui.view.InputNameScreen
import com.kolanse.tiktaktoe.ui.view.PlayerTypeSelection
import com.kolanse.tiktaktoe.ui.view.ResultsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TikTakToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApplication()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApplication() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)}
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Route.INPUT_NAME
        ) {
            composable(Route.INPUT_NAME) {
                InputNameScreen(onNavigate = navController::navigate)
            }
            composable(Route.PLAYER_TYPE) {
                PlayerTypeSelection(onNavigate = navController::navigate)
            }
            composable(Route.GAME) {
                GameInterfaceScreen(onNavigate = navController::navigate)
            }
            composable(Route.RESULTS) {
                ResultsScreen(onNavigate = navController::navigate)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TikTakToeTheme {
    }
}