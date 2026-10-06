package com.claramente.feature.profile.view

import com.claramente.core.auth.model.UserSession
import com.claramente.core.designsystem.component.Eyebrow
import com.claramente.core.designsystem.component.OutlinedPillButton
import com.claramente.core.designsystem.theme.ButtonTone
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.feature.profile.component.IdentityCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(user: UserSession?, loggingOut: Boolean, onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ClaramenteColors.Cream)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 480.dp)
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Eyebrow(text = "PERFIL", letterSpacing = 0.5.sp)
            IdentityCard(user = user)
            OutlinedPillButton(
                text = "Sair da conta",
                onClick = onLogout,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                tone = ButtonTone.PRIMARY,
                enabled = !loggingOut,
            )
        }
    }
}
