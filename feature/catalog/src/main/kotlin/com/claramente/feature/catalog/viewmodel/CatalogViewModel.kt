package com.claramente.feature.catalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.claramente.core.domain.contract.IListLessonsUseCase
import com.claramente.core.domain.error.LessonErrors
import com.claramente.feature.catalog.state.CatalogUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatalogViewModel(private val listLessons: IListLessonsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<CatalogUiState>(CatalogUiState.Loading)
    val state: StateFlow<CatalogUiState> = _state.asStateFlow()

    init {
        load()
    }

    fun load() {
        _state.value = CatalogUiState.Loading
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { listLessons.execute() }
            result
                .onSuccess { _state.value = CatalogUiState.Ready(it) }
                .onFailure { _state.value = CatalogUiState.Failed(LessonErrors.LIST_FAILED) }
        }
    }
}
