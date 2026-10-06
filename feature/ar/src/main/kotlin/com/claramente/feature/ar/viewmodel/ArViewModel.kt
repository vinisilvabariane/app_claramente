package com.claramente.feature.ar.viewmodel

import com.claramente.feature.ar.state.ArMode
import com.claramente.feature.ar.state.ArModel
import com.claramente.feature.ar.state.ArModels
import com.claramente.feature.ar.state.ArUiState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArViewModel(arWebUrl: String?) : ViewModel() {
    private val _state = MutableStateFlow(
        ArUiState(
            models = ArModels.all,
            selected = ArModels.default,
            arWebUrl = arWebUrl?.takeIf { it.isNotBlank() },
        ),
    )
    val state: StateFlow<ArUiState> = _state.asStateFlow()

    fun select(model: ArModel) {
        _state.update { it.copy(selected = model) }
    }

    fun selectMode(mode: ArMode) {
        _state.update { it.copy(mode = mode) }
    }
}
