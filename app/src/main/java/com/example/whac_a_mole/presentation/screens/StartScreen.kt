package com.example.whac_a_mole.presentation.screens

import android.graphics.Paint
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whac_a_mole.R
import com.example.whac_a_mole.pixelFont

@Composable
fun StartScreen(
    getBestScore: () -> Int,
    onPlayGame: () -> Unit,
) {
    val interactionSourcePlay = remember {
        MutableInteractionSource()
    }
    val isStartButtonPressed by interactionSourcePlay.collectIsPressedAsState()

    val interactionSourceRules = remember {
        MutableInteractionSource()
    }
    val isRulesButtonPressed by interactionSourceRules.collectIsPressedAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            contentAlignment = Alignment.TopEnd,
        ) {
            Image(
                painter = painterResource(id = R.drawable.table_best_score),
                contentDescription = "Table best score"
            )

            val pixelTypeface = LocalContext.current.resources.getFont(R.font.pixel_font)
            // Draw the text on top of the image
            Canvas(modifier = Modifier.size(100.dp)) {

                val paint = Paint().apply {
                    color = Color(255, 238, 131).toArgb()
                    textSize = 130f
                    typeface = pixelTypeface
                }
                drawContext.canvas.nativeCanvas.drawText(
                    "${getBestScore.invoke()}",
                    60f,
                    190f,
                    paint
                )
            }
        }

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
                            onPlayGame.invoke()
                        }
                    ),
                    painter = painterResource(
                        id = if (isStartButtonPressed) R.drawable.button_play_pressed
                        else R.drawable.button_play
                    ),
                    contentDescription = "Play button",
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box() {

                    }

                    Image(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSourceRules,
                            indication = null,
                            onClick = {

                            }
                        ),
                        painter = painterResource(
                            id = if (isRulesButtonPressed) R.drawable.button_help_pressed
                            else R.drawable.button_help
                        ),
                        contentDescription = "Rules",
                    )
                }
            }
        }
    }
}