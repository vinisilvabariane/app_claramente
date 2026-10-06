package com.claramente.feature.hub.component

import com.claramente.core.designsystem.component.Eyebrow
import com.claramente.core.designsystem.component.FloatY
import com.claramente.core.designsystem.component.Mascot
import com.claramente.core.designsystem.state.MascotPose
import com.claramente.core.designsystem.theme.ClaramenteColors
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun HubHeader(firstName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Eyebrow(text = "INÍCIO")
            Text(
                "Bom te ver, $firstName.",
                style = MaterialTheme.typography.headlineMedium,
                color = ClaramenteColors.Ink,
                modifier = Modifier.padding(top = 2.dp),
            )
        }
        FloatY(distance = 3.dp, durationMs = 2200) {
            Mascot(pose = MascotPose.HAPPY, size = 56.dp, tapReactions = true)
        }
    }
}
