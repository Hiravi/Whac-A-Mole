package com.example.whac_a_mole.presentation

import com.example.whac_a_mole.presentation.screens.GameScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.whac_a_mole.R
import com.example.whac_a_mole.presentation.screens.StartScreen
import com.example.whac_a_mole.presentation.viewmodels.GameViewModel
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
                        painter = painterResource(id = R.drawable.back2),
                        contentDescription = "background",
                        contentScale = ContentScale.FillBounds
                    )
                    val viewModel = hiltViewModel<GameViewModel>()
                    val state = viewModel.uiState
                    GameScreen(uiState = state, onEvent = viewModel::onEvent)
                    //StartScreen(16)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WhacAMoleTheme {

    }
}