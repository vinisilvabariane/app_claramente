package com.claramente.feature.login.view

import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.feature.login.component.LoginCard
import com.claramente.feature.login.component.LoginHero
import com.claramente.feature.login.component.LoginLayout
import com.claramente.feature.login.state.LoginActions
import com.claramente.feature.login.state.LoginUiState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(state: LoginUiState, actions: LoginActions) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize().background(ClaramenteColors.Sky)) {
        val viewportHeight = maxHeight
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState()),
        ) {
            LoginLayout(
                viewportHeight = viewportHeight,
                hero = { LoginHero() },
                card = { LoginCard(state = state, actions = actions, modifier = Modifier.fillMaxWidth()) },
            )
        }
    }
}
