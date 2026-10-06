package com.claramente.feature.profile.component

import com.claramente.core.auth.model.UserSession
import com.claramente.core.designsystem.component.ChunkyCard
import com.claramente.core.designsystem.theme.ClaramenteColors
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun IdentityCard(user: UserSession?) {
    ChunkyCard(
        color = ClaramenteColors.Berry,
        deep = ClaramenteColors.BerryDeep,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 28.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            ProfileAvatar(
                initials = user?.initials ?: "?",
                modifier = Modifier.padding(bottom = 4.dp),
            )
            Text(
                user?.name?.takeIf { it.isNotBlank() } ?: "Explorador(a)",
                style = MaterialTheme.typography.titleLarge,
                color = ClaramenteColors.Ink,
            )
            user?.email?.takeIf { it.isNotBlank() }?.let { email ->
                Text(
                    email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = ClaramenteColors.TextSecondary,
                )
            }
            user?.rolesLabel?.let { roles ->
                RoleBadge(label = roles, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}
