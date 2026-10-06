package com.claramente.feature.login.component

import com.claramente.core.designsystem.component.OutlinedPillButton
import com.claramente.core.designsystem.theme.ClaramenteColors
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun MockLoginBox(onClick: () -> Unit, enabled: Boolean = true, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OutlinedPillButton(
            text = "Entrar em modo teste",
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
        )
        Text(
            text = "Sem backend: gera uma sessão local para testar o app no celular.",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp, lineHeight = 18.sp),
            color = ClaramenteColors.TextSecondary,
            textAlign = TextAlign.Center,
        )
    }
}
