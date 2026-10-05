package com.claramente.feature.lesson.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.claramente.core.ar.error.ArErrors
import com.claramente.core.ar.model.ArHint
import com.claramente.core.designsystem.component.ErrorPanel
import com.claramente.core.designsystem.component.ScreenTopBar
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.core.model.lesson.Lesson
import com.claramente.feature.lesson.state.PlacedShape

@Composable
internal fun ArLessonContent(
    lesson: Lesson,
    onBack: () -> Unit,
    onUseViewer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val placed = remember { mutableStateListOf<PlacedShape>() }
    var hint by remember { mutableStateOf(ArHint.SEARCHING_SURFACE) }
    var failure by remember { mutableStateOf<String?>(null) }
    var status by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        ArStage(
            shape = lesson.shape,
            placed = placed,
            onPlace = { placed += it },
            onHintChanged = { hint = it },
            onStatusChanged = { status = it },
            onFailed = { failure = it },
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = ClaramenteColors.Background.copy(alpha = 0.8f),
            ) {
                ScreenTopBar(title = lesson.title, onBack = onBack)
            }
            HintChip(hint = hint)
            if (status.isNotEmpty()) NoticeChip(text = status, color = ClaramenteColors.TextMuted)
        }
        LessonInfoCard(
            lesson = lesson,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(12.dp),
        ) {
            PlacementFooter(
                placedCount = placed.size,
                onClear = {
                    val old = placed.toList()
                    placed.clear()
                    old.forEach { it.anchor.detach() }
                },
            )
        }
        failure?.let { reason ->
            Surface(color = ClaramenteColors.Background, modifier = Modifier.fillMaxSize()) {
                ErrorPanel(
                    message = ArErrors.SESSION_FAILED,
                    detail = "${ArErrors.SESSION_FAILED_HINT}\n\n$reason",
                    actionLabel = "Ver em 3D",
                    onAction = onUseViewer,
                )
            }
        }
    }
}
