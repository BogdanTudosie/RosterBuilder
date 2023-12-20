package com.bogdantudosie.rosterbuilder.viewmodel

import androidx.lifecycle.ViewModel
import com.bogdantudosie.rosterbuilder.data.Unit
import kotlinx.coroutines.flow.MutableStateFlow

class UnitViewModel: ViewModel() {
    private val unitState = MutableStateFlow(Unit())
}