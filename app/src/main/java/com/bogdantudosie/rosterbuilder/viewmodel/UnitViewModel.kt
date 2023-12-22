package com.bogdantudosie.rosterbuilder.viewmodel

import androidx.lifecycle.ViewModel
import com.bogdantudosie.rosterbuilder.data.GameUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UnitViewModel: ViewModel() {
    private val gameUnitState = MutableStateFlow(GameUnit())
    val unitUIState: StateFlow<GameUnit> = gameUnitState.asStateFlow()

    // Update UI here
    fun updateUnit() {
        // implement this please!
    }
}