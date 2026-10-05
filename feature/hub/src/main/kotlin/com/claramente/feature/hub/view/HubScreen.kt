package com.claramente.feature.hub.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.feature.hub.component.HubHeader
import com.claramente.feature.hub.component.HubTile
import com.claramente.feature.hub.state.HubModule

@Composable
fun HubScreen(modules: List<HubModule>, onOpen: (HubModule) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ClaramenteColors.Background)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HubHeader()
        Spacer(Modifier.height(8.dp))
        modules.forEach { module ->
            HubTile(module = module, onClick = { onOpen(module) })
        }
    }
}
