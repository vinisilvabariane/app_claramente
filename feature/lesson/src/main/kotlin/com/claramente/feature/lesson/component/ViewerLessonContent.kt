package com.claramente.feature.lesson.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.claramente.core.designsystem.component.ScreenTopBar
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.core.model.lesson.Lesson

@Composable
internal fun ViewerLessonContent(lesson: Lesson, onBack: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        ViewerStage(shape = lesson.shape, modifier = Modifier.fillMaxSize())
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
            NoticeChip(
                text = "Modo 3D: este aparelho não oferece realidade aumentada.",
                color = ClaramenteColors.Warning,
            )
        }
        LessonInfoCard(
            lesson = lesson,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(12.dp),
        ) {
            Text(
                text = "Arraste para girar. Use dois dedos para dar zoom.",
                style = MaterialTheme.typography.bodyMedium,
                color = ClaramenteColors.Accent,
            )
        }
    }
}
