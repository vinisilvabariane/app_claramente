package com.claramente.feature.lesson.state

import com.claramente.core.model.lesson.Lesson

sealed interface LessonUiState {
    data object Loading : LessonUiState

    data class Ready(val lesson: Lesson) : LessonUiState

    data class Failed(val message: String) : LessonUiState
}
