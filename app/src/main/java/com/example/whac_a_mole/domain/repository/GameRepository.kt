package com.example.whac_a_mole.domain.repository

import android.app.Application
import com.example.whac_a_mole.domain.models.Hole
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getHoles() : Flow<List<Hole>>

    suspend fun increaseScore()


    suspend fun getScore(): Int


    fun gameOver()

    suspend fun getGameStatus(): Boolean

    suspend fun hideMole(holeNumber: Int)

    suspend fun showMole(holeNumber: Int)

    suspend fun punchMole(holeNumber: Int)

    fun getBestScore(appContext: Application) : Int

    fun updateBestScore(appContext: Application, newValue: Int)
}