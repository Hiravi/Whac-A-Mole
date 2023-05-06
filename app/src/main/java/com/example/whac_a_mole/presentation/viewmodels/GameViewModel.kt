package com.example.whac_a_mole.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        updateData()
    }

    fun onEvent(event: HolesEvent) {
        when (event) {
            is HolesEvent.EmptyHole -> {
                viewModelScope.launch {
                    repository.hideMole(event.holeNumber)
                    updateData()
                }
            }
            is HolesEvent.MoleAppears -> {
                viewModelScope.launch {
                    repository.showMole(event.holeNumber)
                    updateData()
                }
            }
            is HolesEvent.MolePunched -> {
                viewModelScope.launch {
                    repository.punchMole(event.holeNumber)
                    updateData()
                }
            }
        }
    }

    private fun updateData() {
        getHolesJob?.cancel()
        getHolesJob = repository.getHoles()
            .onEach { holes ->
                _uiState.value = uiState.value.copy(
                    holes = holes,
                    score = repository.getScore(),
                )
            }
            .launchIn(viewModelScope)
    }


}