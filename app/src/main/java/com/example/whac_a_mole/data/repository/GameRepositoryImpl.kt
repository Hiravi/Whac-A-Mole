package com.example.whac_a_mole.data.repository

import com.example.whac_a_mole.data.Game
import com.example.whac_a_mole.domain.models.Hole

import com.example.whac_a_mole.domain.repository.GameRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val currentGame: Game
) : GameRepository {

    override fun getHoles(): Flow<List<Hole>> {
        return currentGame.getHoles()
    }

    override suspend fun increaseScore() {
        currentGame.increaseScore()
    }

    override fun setHighScore() {
        TODO("Not yet implemented")
    }


    override suspend fun getScore(): Int = currentGame.getGameScore()

    override fun getHighScore(): Int {
        TODO("Not yet implemented")
    }

    override fun gameOver() {
        currentGame.finishGame()
    }

    override suspend fun getGameStatus(): Boolean = currentGame.isRunning


    override suspend fun hideMole(holeNumber: Int) {
        currentGame.hideMole(holeNumber)
    }


    override suspend fun showMole(holeNumber: Int) {
        currentGame.showMole(holeNumber)
    }


    override suspend fun punchMole(holeNumber: Int) {
        currentGame.punchMole(holeNumber)
    }
}