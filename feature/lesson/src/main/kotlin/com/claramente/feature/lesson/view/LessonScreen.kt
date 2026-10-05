package com.claramente.feature.lesson.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.claramente.core.designsystem.component.CenterMessage
import com.claramente.core.designsystem.component.ErrorPanel
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.feature.lesson.component.LessonModeGate
import com.claramente.feature.lesson.state.LessonUiState

@Composable
fun LessonScreen(state: LessonUiState, onBack: () -> Unit, onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(ClaramenteColors.Background)) {
        when (state) {
            LessonUiState.Loading -> CenterMessage(text = "Carregando lição…", modifier = Modifier.safeDrawingPadding())
            is LessonUiState.Failed -> ErrorPanel(
                message = state.message,
                actionLabel = "Tentar de novo",
                onAction = onRetry,
                modifier = Modifier.safeDrawingPadding(),
            )
            is LessonUiState.Ready -> LessonModeGate(lesson = state.lesson, onBack = onBack)
        }
    }
}
