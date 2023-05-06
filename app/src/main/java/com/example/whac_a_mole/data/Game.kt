package com.example.whac_a_mole.data

import android.app.Application
import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.example.whac_a_mole.domain.models.Hole
import com.example.whac_a_mole.domain.models.HoleState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class Game() {
    var isRunning = true
    private var gameScore: Int = 0
    private val _holes = (0..8).map {

        when (it) {
            0, 2 -> Hole(holeNumber = it, height = PaddingValues(top = 64.dp))
            else -> Hole(holeNumber = it, height = PaddingValues(0.dp))
        }
    }.toMutableList()

    private val holes : List<Hole>
        get() = _holes.toList()

    fun getHoles() : Flow<List<Hole>> = flow {
        emit(holes)
    }

    fun getGameScore() : Int {
        return gameScore
    }

    fun finishGame() {
        isRunning = false
    }

    suspend fun hideMole(holeNumber: Int) {
        delay(300)
        _holes[holeNumber] = holes[holeNumber].copy(holeState = HoleState.Hole)
    }


    suspend fun showMole(holeNumber: Int) {
        _holes[holeNumber] = holes[holeNumber].copy(holeState = HoleState.Mole)
    }


    suspend fun punchMole(holeNumber: Int) {
        if (holes[holeNumber].holeState == HoleState.Mole) {
            _holes[holeNumber] = holes[holeNumber].copy(holeState = HoleState.KickedMole)
            increaseScore()
        }
    }

    suspend fun increaseScore() {
        gameScore++
    }

    fun getBestScore(appContext: Application) : Int {
        val pref = appContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return pref.getInt("bestScore", 0)
    }

    fun updateBestScore(appContext: Application, newValue: Int) {
        val pref = appContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = pref.edit()

        val currentBestScore = getBestScore(appContext)
        if (currentBestScore < newValue) {
            editor.putInt("bestScore", newValue)
            editor.apply()
        }


    }
}