package com.example.whac_a_mole.presentation

import com.example.whac_a_mole.domain.models.Hole

data class HolesState(
    var holes: List<Hole> = emptyList(),
    var score: Int = 0
)
