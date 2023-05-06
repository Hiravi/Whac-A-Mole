package com.example.whac_a_mole.di

import android.app.Application
import com.example.whac_a_mole.data.Game
import com.example.whac_a_mole.data.repository.GameRepositoryImpl
import com.example.whac_a_mole.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(currentGame: Game) : GameRepository {
        return GameRepositoryImpl(currentGame)
    }

    @Provides
    @Singleton
    fun provideCurrentGame() : Game {
        return Game()
    }
}