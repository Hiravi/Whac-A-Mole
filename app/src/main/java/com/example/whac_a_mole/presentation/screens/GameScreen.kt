package com.example.whac_a_mole.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.whac_a_mole.domain.models.HoleState
import com.example.whac_a_mole.presentation.HolesEvent
import com.example.whac_a_mole.presentation.HolesState
import com.example.whac_a_mole.presentation.components.HolesGrid
import com.example.whac_a_mole.presentation.components.ScoreTable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun GameScreen(
    uiState: StateFlow<HolesState>,
    onEvent: (HolesEvent) -> Unit,
    onBack: () -> Unit,
) {
    var isRunning = true
    val scope = rememberCoroutineScope()
    val state = uiState.collectAsState()
    val currentTime = remember {
        mutableStateOf(60)
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ScoreTable(state = state, time = currentTime.value)
            HolesGrid(state = state, onEvent = onEvent)
        }



        LaunchedEffect(key1 = currentTime) {
            while (isRunning) {
                if (currentTime.value > 0) {
                    delay(1000L)
                    currentTime.value -= 1
                } else isRunning = false
            }
        }

        LaunchedEffect(Unit) {
            val moles = (0..8).toMutableList()

            while (isRunning) {
                delay(Random.nextLong(400, 1200))

                scope.launch {
                    val holeNumber = moles.random()
                    moles.remove(holeNumber)

                    if (state.value.holes[holeNumber].holeState == HoleState.Hole) {
                        onEvent.invoke(HolesEvent.MoleAppears(holeNumber = holeNumber))
                    }
                    delay(700)

                    onEvent.invoke(HolesEvent.EmptyHole(holeNumber = holeNumber))

                    delay(300)
                    moles.add(holeNumber)
                }
            }
        }
    }
}

