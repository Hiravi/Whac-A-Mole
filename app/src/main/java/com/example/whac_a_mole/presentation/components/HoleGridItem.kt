package com.example.whac_a_mole.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.whac_a_mole.R
import com.example.whac_a_mole.domain.models.Hole
import com.example.whac_a_mole.domain.models.HoleState

@Composable
fun HoleGridItem(
    hole: Hole,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(hole.height),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(
                    when (hole.state) {
                        HoleState.Hole -> R.drawable.hole
                        HoleState.Mole -> R.drawable.hole_mole
                        HoleState.KickedMole -> R.drawable.hole_kicked_mole
                    }
                ),
                contentDescription = "Hole",
                Modifier
                    .size(100.dp)
                    .noRippleClickable {
                        onClick.invoke()
                    }
            )
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}