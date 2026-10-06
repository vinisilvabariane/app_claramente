package com.claramente.screenshot

import com.android.tools.screenshot.PreviewTest
import com.claramente.core.designsystem.component.Mascot
import com.claramente.core.designsystem.state.MascotPose
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.core.designsystem.theme.ClaramenteTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@PreviewTest
@Preview(showBackground = true)
@Composable
fun MascotPreview() {
    ClaramenteTheme {
        Row(
            modifier = Modifier.background(ClaramenteColors.Cream).padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Mascot(pose = MascotPose.HAPPY, size = 120.dp)
            Mascot(pose = MascotPose.WAVE, size = 120.dp)
            Mascot(pose = MascotPose.CELEBRATE, size = 120.dp)
        }
    }
}
