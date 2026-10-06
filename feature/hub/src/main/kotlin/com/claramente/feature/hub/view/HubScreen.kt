package com.claramente.feature.hub.view

import com.claramente.core.auth.model.UserSession
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.feature.hub.component.ContinueCard
import com.claramente.feature.hub.component.FeedCard
import com.claramente.feature.hub.component.HubHeader
import com.claramente.feature.hub.state.FeedItem
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HubScreen(user: UserSession?, feed: List<FeedItem>, onContinue: () -> Unit) {
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
                .widthIn(max = 760.dp)
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            HubHeader(firstName = user?.firstName ?: "Você")
            ContinueCard(onContinue = onContinue)
            Column {
                Text(
                    "Novidades da sua turma",
                    style = MaterialTheme.typography.titleMedium,
                    color = ClaramenteColors.Ink,
                    modifier = Modifier.padding(bottom = 12.dp),
                )
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    feed.forEach { item -> FeedCard(item = item) }
                }
            }
        }
    }
}
