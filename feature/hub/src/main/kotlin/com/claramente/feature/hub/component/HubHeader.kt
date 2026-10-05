package com.claramente.feature.hub.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.claramente.core.designsystem.theme.ClaramenteColors

@Composable
internal fun HubHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "Claramente",
            style = MaterialTheme.typography.headlineMedium,
            color = ClaramenteColors.Accent,
        )
        Text(
            text = "Aprenda explorando em realidade aumentada.",
            style = MaterialTheme.typography.bodyLarge,
            color = ClaramenteColors.TextMuted,
        )
    }
}
