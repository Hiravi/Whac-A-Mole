package com.example.whac_a_mole.presentation

sealed class HolesEvent {
    object IncrementCount : HolesEvent()
    data class EmptyHole(
        val holeNumber: Int
    ) : HolesEvent()
    data class MoleAppears(
        val holeNumber: Int
    ) : HolesEvent()
    data class MolePunched(
        val holeNumber: Int
    ) : HolesEvent()
}