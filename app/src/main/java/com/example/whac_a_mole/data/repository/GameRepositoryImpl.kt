package com.example.whac_a_mole.data.repository

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.example.whac_a_mole.domain.models.Hole
import com.example.whac_a_mole.domain.repository.GameRepository
import kotlinx.coroutines.delay
import kotlin.random.Random

class GameRepositoryImpl(context: Context) : GameRepository {
    private var gameScore: Int = 0
    private val holes = (1..9).map {

        when (it) {
            1, 3 -> Hole(height = PaddingValues(top = 64.dp))
            else -> Hole(height =PaddingValues(0.dp))
        }
    }

    override fun increaseScore() {
        gameScore++
    }

    override fun setHighScore() {
        TODO("Not yet implemented")
    }

    override fun getHoles(): List<Hole> {
        return holes
    }

    override fun getScore(): Int {
        return gameScore
    }

    override fun getHighScore(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun moleAction() {
        TODO("Not yet implemented")
    }


}