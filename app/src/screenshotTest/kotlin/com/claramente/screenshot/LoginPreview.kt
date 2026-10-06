package com.claramente.screenshot

import com.android.tools.screenshot.PreviewTest
import com.claramente.core.designsystem.theme.ClaramenteTheme
import com.claramente.feature.login.state.LoginActions
import com.claramente.feature.login.state.LoginUiState
import com.claramente.feature.login.view.LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@PreviewTest
@Preview(showBackground = true, device = PreviewDevices.PHONE)
@Composable
fun LoginPreview() {
    ClaramenteTheme {
        LoginScreen(
            state = LoginUiState(mockEnabled = true),
            actions = LoginActions({}, {}, {}, {}),
        )
    }
}
