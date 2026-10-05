package com.claramente.feature.lesson.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.claramente.core.domain.contract.IFindLessonUseCase
import com.claramente.core.domain.error.LessonErrors
import com.claramente.feature.lesson.state.LessonUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LessonViewModel(
    private val findLesson: IFindLessonUseCase,
    private val lessonId: String,
) : ViewModel() {
    private val _state = MutableStateFlow<LessonUiState>(LessonUiState.Loading)
    val state: StateFlow<LessonUiState> = _state.asStateFlow()

    init {
        load()
    }

    fun load() {
        _state.value = LessonUiState.Loading
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { findLesson.execute(lessonId) }
            result
                .onSuccess { _state.value = LessonUiState.Ready(it) }
                .onFailure { _state.value = LessonUiState.Failed(it.message ?: LessonErrors.FIND_FAILED) }
        }
    }
}
