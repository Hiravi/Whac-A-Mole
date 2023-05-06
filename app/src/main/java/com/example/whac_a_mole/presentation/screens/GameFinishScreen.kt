package com.example.whac_a_mole.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.whac_a_mole.R
import com.example.whac_a_mole.pixelFont

@Composable
fun GameFinishScreen(
    onRestart: () -> Unit,
    onHome: () -> Unit,
    getBestScore: () -> Int,
) {
    val interactionSourceHome = remember {
        MutableInteractionSource()
    }
    val isHomeButtonPressed by interactionSourceHome.collectIsPressedAsState()

    val interactionSourceRestart = remember {
        MutableInteractionSource()
    }
    val isRestartButtonPressed by interactionSourceRestart.collectIsPressedAsState()


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (table, rowButtons, sheet, score) = createRefs()

        Image(
            modifier = Modifier
                .constrainAs(table) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            painter = painterResource(id = R.drawable.table_result),
            contentDescription = "Result table",
        )

        Image(
            modifier = Modifier
                .padding(48.dp)
                .constrainAs(sheet) {
                    top.linkTo(table.top)
                    start.linkTo(table.start)
                    end.linkTo(table.end)
                },
            painter = painterResource(id = R.drawable.sheet_result),
            contentDescription = "Result sheet",
        )

        Text(
            modifier = Modifier.constrainAs(score) {
                top.linkTo(sheet.top)
                bottom.linkTo(sheet.bottom)
                start.linkTo(sheet.start)
                end.linkTo(sheet.end)
            },
            text = "${getBestScore.invoke()}",
            fontSize = 64.sp,
            fontFamily = pixelFont
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(rowButtons) {
                    top.linkTo(sheet.bottom)
                    bottom.linkTo(table.bottom)
                    start.linkTo(table.start)
                    end.linkTo(table.end)
                },
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            Image(
                modifier = Modifier.clickable(
                    interactionSource = interactionSourceHome,
                    indication = null,
                    onClick = {
                        onHome.invoke()
                    }
                ),
                painter = painterResource(
                    id = if (isHomeButtonPressed) R.drawable.button_home_pressed
                    else R.drawable.button_home
                ),
                contentDescription = "Home button",
            )

            Image(
                modifier = Modifier.clickable(
                    interactionSource = interactionSourceRestart,
                    indication = null,
                    onClick = {
                        onRestart.invoke()
                    }
                ),
                painter = painterResource(
                    id = if (isRestartButtonPressed) R.drawable.button_restart_pressed
                    else R.drawable.button_restart
                ),
                contentDescription = "Restart button",
            )
        }
    }
}