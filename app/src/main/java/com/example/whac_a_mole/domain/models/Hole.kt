package com.example.whac_a_mole.domain.models

import androidx.compose.foundation.layout.PaddingValues
import com.example.whac_a_mole.R

data class Hole(
    var height: PaddingValues,
    var state: HoleState = HoleState.Hole,
)

sealed class HoleState() {
    object Hole : HoleState()
    object Mole : HoleState()
    object KickedMole : HoleState()
}