package com.claramente.core.designsystem.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.claramente.core.designsystem.theme.ClaramenteColors

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ClaramenteColors.Accent,
            contentColor = ClaramenteColors.OnAccent,
            disabledContainerColor = ClaramenteColors.SurfaceAlt,
            disabledContentColor = ClaramenteColors.TextMuted,
        ),
        modifier = modifier.height(52.dp),
    ) {
        Text(text, style = MaterialTheme.typography.labelMedium)
    }
}
