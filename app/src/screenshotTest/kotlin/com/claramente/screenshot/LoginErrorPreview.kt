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
fun LoginErrorPreview() {
    ClaramenteTheme {
        LoginScreen(
            state = LoginUiState(
                email = "aluno@claramente.com",
                password = "123",
                error = "E-mail ou senha inválidos.",
                mockEnabled = true,
            ),
            actions = LoginActions({}, {}, {}, {}),
        )
    }
}
