package com.example.whac_a_mole.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.whac_a_mole.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GameRepository,
    private val appContext: Application,
) : ViewModel() {

    fun getBestScore() : Int {
        return repository.getBestScore(appContext)
    }

    fun updateBestScore(newValue: Int) {
        repository.updateBestScore(appContext, newValue)
    }
}