package com.example.whac_a_mole.domain.repository

import com.example.whac_a_mole.domain.models.Hole

interface GameRepository {

    fun increaseScore()

    fun setHighScore()

    fun getHoles() : List<Hole>

    fun getScore() : Int

    fun getHighScore() : Int

    suspend fun moleAction()
}