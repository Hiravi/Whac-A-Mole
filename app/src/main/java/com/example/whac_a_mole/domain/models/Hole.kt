package com.example.whac_a_mole.domain.models

import androidx.compose.foundation.layout.PaddingValues

data class Hole(
    val holeNumber: Int,
    var height: PaddingValues,
    var holeState: HoleState = HoleState.Hole,
)

sealed class HoleState() {
    object Hole : HoleState()
    object Mole : HoleState()
    object KickedMole : HoleState()
}