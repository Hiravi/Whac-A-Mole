package com.example.whac_a_mole.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
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
    onFinish: () -> Unit,
    updateScore: (Int) -> Unit,
    getBestScore: () -> Int,
) {
    var isRunning = true
    val scope = rememberCoroutineScope()
    val state = uiState.collectAsState()
    val currentTime = remember {
        mutableStateOf(60)
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (table, grid) = createRefs()

        createVerticalChain(table, grid, chainStyle = ChainStyle.Spread)

        ScoreTable(
            modifier = Modifier.constrainAs(table) {
                centerVerticallyTo(parent)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            state = state,
            time = currentTime.value
        )
        HolesGrid(
            modifier = Modifier
                .padding(start = 8.dp)
                .constrainAs(grid) {
                centerVerticallyTo(parent)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            state = state,
            onEvent = onEvent
        )

        LaunchedEffect(key1 = currentTime) {
            while (isRunning) {
                if (currentTime.value > 0) {
                    delay(1000L)
                    currentTime.value -= 1
                } else {
                    isRunning = false

                    if (state.value.score > getBestScore.invoke()) updateScore.invoke(state.value.score)

                    onFinish.invoke()
                }
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

