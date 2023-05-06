package com.example.whac_a_mole.presentation

import com.example.whac_a_mole.presentation.screens.GameScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.whac_a_mole.R
import com.example.whac_a_mole.presentation.screens.GameFinishScreen
import com.example.whac_a_mole.presentation.screens.StartScreen
import com.example.whac_a_mole.presentation.viewmodels.GameViewModel
import com.example.whac_a_mole.presentation.viewmodels.MainViewModel
import com.example.whac_a_mole.ui.theme.WhacAMoleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhacAMoleTheme {
                Box {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = "background",
                        contentScale = ContentScale.FillBounds
                    )
                    val mainViewModel = hiltViewModel<MainViewModel>()
                    val viewModel = hiltViewModel<GameViewModel>()
                    val state = viewModel.uiState

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "start") {
                        composable("start") {
                            StartScreen(
                                onPlayGame = { navController.navigate("game") },
                                getBestScore = mainViewModel::getBestScore,
                            )
                        }
                        composable("game") {
                            GameScreen(
                                onFinish = { navController.navigate("finish") },
                                onPause = { navController.clearBackStack("start") },
                                uiState = state,
                                onEvent = viewModel::onEvent,
                                updateScore = mainViewModel::updateBestScore,
                                getBestScore = mainViewModel::getBestScore,
                            )
                        }
                        composable("finish") {
                            GameFinishScreen(
                                onHome = { navController.navigate("start") },
                                onRestart = { navController.navigate("game") },
                                getBestScore = mainViewModel::getBestScore,
                            )
                        }
                    }
                }
            }
        }
    }
}