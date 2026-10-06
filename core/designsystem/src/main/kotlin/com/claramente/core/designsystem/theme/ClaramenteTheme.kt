package com.claramente.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ClaramenteTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ClaramenteColors.LightScheme,
        typography = ClaramenteTypography.typography,
        content = content,
    )
}
