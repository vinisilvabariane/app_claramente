package com.claramente.feature.lesson.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.claramente.core.designsystem.component.PrimaryButton
import com.claramente.core.designsystem.theme.ClaramenteColors

@Composable
internal fun PlacementFooter(placedCount: Int, onClear: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Objetos na cena: $placedCount",
            style = MaterialTheme.typography.bodyMedium,
            color = ClaramenteColors.Accent,
        )
        PrimaryButton(text = "Limpar", onClick = onClear, enabled = placedCount > 0)
    }
}
