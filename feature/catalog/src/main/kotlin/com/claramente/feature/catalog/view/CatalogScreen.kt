package com.claramente.feature.catalog.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.claramente.core.designsystem.component.CenterMessage
import com.claramente.core.designsystem.component.ErrorPanel
import com.claramente.core.designsystem.component.ScreenTopBar
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.core.model.lesson.Lesson
import com.claramente.feature.catalog.component.LessonCard
import com.claramente.feature.catalog.state.CatalogUiState

@Composable
fun CatalogScreen(
    state: CatalogUiState,
    onBack: () -> Unit,
    onOpenLesson: (Lesson) -> Unit,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ClaramenteColors.Background)
            .safeDrawingPadding(),
    ) {
        ScreenTopBar(title = "Lições", onBack = onBack)
        when (state) {
            CatalogUiState.Loading -> CenterMessage(text = "Carregando lições…")
            is CatalogUiState.Failed -> ErrorPanel(message = state.message, actionLabel = "Tentar de novo", onAction = onRetry)
            is CatalogUiState.Ready -> LazyColumn(
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(state.lessons, key = { it.id }) { lesson ->
                    LessonCard(lesson = lesson, onClick = { onOpenLesson(lesson) })
                }
            }
        }
    }
}
