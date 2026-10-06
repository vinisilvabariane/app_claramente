package com.claramente.core.designsystem.component

import com.claramente.core.designsystem.theme.ClaramenteColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun Eyebrow(text: String, modifier: Modifier = Modifier, letterSpacing: TextUnit = 0.sp) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
        color = ClaramenteColors.TextSecondary,
        letterSpacing = letterSpacing,
    )
}
