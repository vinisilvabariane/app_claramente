package com.claramente.feature.login.component

import com.claramente.core.designsystem.component.FloatY
import com.claramente.core.designsystem.component.Mascot
import com.claramente.core.designsystem.state.MascotPose
import com.claramente.core.designsystem.theme.ClaramenteColors
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun LoginHero(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 56.dp, bottom = 36.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        FloatY(distance = 5.dp, durationMs = 2400) {
            Mascot(pose = MascotPose.CELEBRATE, size = 104.dp, tapReactions = true)
        }
        Text(
            text = "Comece sua jornada.",
            style = MaterialTheme.typography.headlineSmall,
            color = ClaramenteColors.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 14.dp),
        )
        Text(
            text = "Comunidade e aprendizado leve.",
            style = MaterialTheme.typography.bodyMedium,
            color = ClaramenteColors.White.copy(alpha = 0.88f),
            textAlign = TextAlign.Center,
        )
    }
}
