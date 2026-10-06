package com.claramente.feature.ar.component

import com.claramente.core.designsystem.component.Eyebrow
import com.claramente.core.designsystem.theme.ClaramenteColors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ArHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Eyebrow(text = "REALIDADE AUMENTADA")
        Text(
            text = "Aponte a câmera e veja em 3D.",
            style = MaterialTheme.typography.headlineMedium,
            color = ClaramenteColors.Ink,
            modifier = Modifier.padding(top = 2.dp),
        )
    }
}
