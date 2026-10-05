package com.claramente.feature.catalog.component

import androidx.compose.foundation.BorderStroke
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
internal fun LessonCard(lesson: Lesson, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = ClaramenteColors.Surface,
        border = BorderStroke(1.dp, ClaramenteColors.Outline),
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
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
            Text(
                text = "Ver em realidade aumentada",
                style = MaterialTheme.typography.labelMedium,
                color = ClaramenteColors.Accent,
            )
        }
    }
}
