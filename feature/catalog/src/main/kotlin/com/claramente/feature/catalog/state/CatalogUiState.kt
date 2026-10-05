package com.claramente.feature.catalog.state

import com.claramente.core.model.lesson.Lesson

sealed interface CatalogUiState {
    data object Loading : CatalogUiState

    data class Ready(val lessons: List<Lesson>) : CatalogUiState

    data class Failed(val message: String) : CatalogUiState
}
