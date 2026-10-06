package com.claramente.screenshot

import com.android.tools.screenshot.PreviewTest
import com.claramente.core.designsystem.theme.ClaramenteTheme
import com.claramente.feature.ar.state.ArActions
import com.claramente.feature.ar.state.ArModels
import com.claramente.feature.ar.state.ArUiState
import com.claramente.feature.ar.view.ArScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@PreviewTest
@Preview(showBackground = true, device = PreviewDevices.PHONE)
@Composable
fun ArPreview() {
    ClaramenteTheme {
        ArScreen(
            state = ArUiState(
                models = ArModels.all,
                selected = ArModels.default,
                arWebUrl = "https://exemplo.pages.dev",
            ),
            actions = ArActions({}, {}, {}, {}),
        )
    }
}
