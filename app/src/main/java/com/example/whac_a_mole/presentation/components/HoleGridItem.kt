package com.example.whac_a_mole.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
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
    Log.d("GridItem", "Recomposing!")
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
                    when (hole.holeState) {
                        is HoleState.Hole -> R.drawable.hole
                        is HoleState.Mole -> R.drawable.hole_mole
                        is HoleState.KickedMole -> R.drawable.hole_kicked_mole
                    }
                ),
                contentDescription = "Hole",
                Modifier
                    .size(100.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(onPress = {
                            onClick.invoke()
                        })
                    }
            )
        }
    }
}
