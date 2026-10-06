package com.claramente.feature.login.component

import com.claramente.core.designsystem.component.BrandTextField
import com.claramente.core.designsystem.component.PrimaryButton
import com.claramente.core.designsystem.theme.ButtonTone
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.feature.login.state.LoginActions
import com.claramente.feature.login.state.LoginUiState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun LoginCard(state: LoginUiState, actions: LoginActions, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(ClaramenteColors.Cream, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .navigationBarsPadding()
            .padding(start = 28.dp, end = 28.dp, top = 32.dp, bottom = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium,
            color = ClaramenteColors.Sky,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = "Preencha seus dados para começar.",
            style = MaterialTheme.typography.bodyMedium,
            color = ClaramenteColors.TextSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 24.dp),
        )
        Column(
            modifier = Modifier.widthIn(max = 420.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            state.error?.let { LoginErrorBox(message = it) }
            BrandTextField(
                value = state.email,
                onValueChange = actions.onEmail,
                placeholder = "E-mail",
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Email,
            )
            BrandTextField(
                value = state.password,
                onValueChange = actions.onPassword,
                placeholder = "Senha",
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                password = true,
                imeAction = ImeAction.Done,
                onImeAction = { if (state.canSubmit) actions.onSubmit() },
            )
            PrimaryButton(
                text = if (state.loading) "Entrando..." else "Entrar",
                onClick = actions.onSubmit,
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                tone = ButtonTone.SUCCESS,
                enabled = state.canSubmit,
                loading = state.loading,
            )
            if (state.mockEnabled) {
                MockLoginBox(onClick = actions.onMockLogin)
            }
        }
    }
}
