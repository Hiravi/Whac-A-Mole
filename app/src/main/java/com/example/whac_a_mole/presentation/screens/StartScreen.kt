package com.example.whac_a_mole.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whac_a_mole.R
import com.example.whac_a_mole.boldPixelFont
import com.example.whac_a_mole.pixelFont

@Composable
fun StartScreen(bestScore: Int) {
    val interactionSourcePlay = remember {
        MutableInteractionSource()
    }
    val isStartButtonPressed by interactionSourcePlay.collectIsPressedAsState()

    val interactionSourceRules = remember {
        MutableInteractionSource()
    }
    val isRulesButtonPressed by interactionSourceRules.collectIsPressedAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.title),
                contentDescription = "Title"
            )

            Image(
                modifier = Modifier.clickable(
                    interactionSource = interactionSourcePlay,
                    indication = null,
                    onClick = {

                    }
                ),
                painter = painterResource(
                    id = if (isStartButtonPressed) R.drawable.play_button_pressed
                        else R.drawable.play_button),
                contentDescription = "Play button",
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box() {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.top),
                            contentDescription = "Best score",
                        )
                        Text(
                            text = "$bestScore",
                            fontFamily = boldPixelFont,
                            fontSize = 48   .sp,
                            color = Color(255, 238, 131)
                        )
                    }
                }

                Image(
                    modifier = Modifier.clickable(
                        interactionSource = interactionSourceRules,
                        indication = null,
                        onClick = {

                        }
                    ),
                    painter = painterResource(
                        id = if (isRulesButtonPressed) R.drawable.rules_button_pressed
                            else R.drawable.rules_button
                    ),
                    contentDescription = "Rules",
                )
            }
        }
    }
}