package com.claramente.feature.lesson.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.claramente.core.designsystem.theme.ClaramenteColors

@Composable
internal fun NoticeChip(text: String, color: Color, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = ClaramenteColors.Background.copy(alpha = 0.8f),
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = color,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
        )
    }
}
