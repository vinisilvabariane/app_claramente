package com.claramente.feature.lesson.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.core.model.lesson.Lesson

@Composable
internal fun LessonInfoCard(
    lesson: Lesson,
    modifier: Modifier = Modifier,
    footer: @Composable () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = ClaramenteColors.Surface.copy(alpha = 0.92f),
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.titleLarge,
                color = ClaramenteColors.Text,
            )
            Text(
                text = lesson.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = ClaramenteColors.TextMuted,
            )
            footer()
        }
    }
}
