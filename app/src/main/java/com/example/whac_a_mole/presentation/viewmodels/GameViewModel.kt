package com.example.whac_a_mole.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.whac_a_mole.data.repository.GameRepositoryImpl
import com.example.whac_a_mole.domain.models.Hole
import com.example.whac_a_mole.domain.repository.GameRepository
import com.example.whac_a_mole.presentation.HolesEvent
import com.example.whac_a_mole.presentation.HolesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
     private val repository: GameRepository
    ) : ViewModel() {


    private var _uiState = MutableStateFlow(HolesState())
    val uiState: MutableStateFlow<HolesState>
        get() = _uiState

    private var getHolesJob: Job? = null

    init {
        getHoles()
    }

    fun onEvent(event: HolesEvent) {
        when (event) {
            is HolesEvent.EmptyHole -> {
                viewModelScope.launch {
                    repository.hideMole(event.holeNumber)
                    getHoles()
                }
            }
            is HolesEvent.MoleAppears -> {
                viewModelScope.launch {
                    repository.showMole(event.holeNumber)
                    getHoles()
                }
            }
            is HolesEvent.MolePunched -> {
                viewModelScope.launch {
                    repository.punchMole(event.holeNumber)
                    getHoles()
                }
            }
        }
    }

    private fun getHoles() {
        getHolesJob?.cancel()
        getHolesJob = repository.getHoles()
            .onEach { holes ->
                _uiState.value = uiState.value.copy(
                    holes = holes
                )
            }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                score = repository.getScore()
            )
        }
    }


}