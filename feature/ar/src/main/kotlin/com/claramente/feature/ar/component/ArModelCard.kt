package com.claramente.feature.ar.component

import com.claramente.core.designsystem.component.ArGlyph
import com.claramente.core.designsystem.component.ChunkyCard
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.feature.ar.state.ArModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ArModelCard(model: ArModel, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    ChunkyCard(
        color = if (selected) ClaramenteColors.Sky else ClaramenteColors.NeutralBorder,
        deep = if (selected) ClaramenteColors.SkyDeep else ClaramenteColors.NeutralBorderDeep,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        onClick = onClick,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(ClaramenteColors.Sky.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center,
            ) {
                ArGlyph(color = ClaramenteColors.Sky, modifier = Modifier.size(24.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = model.subject.uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, letterSpacing = 0.5.sp),
                    color = ClaramenteColors.TextSecondary,
                )
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = ClaramenteColors.Ink,
                    modifier = Modifier.padding(top = 1.dp),
                )
                Text(
                    text = model.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = ClaramenteColors.TextSecondary,
                    modifier = Modifier.padding(top = 3.dp),
                )
            }
        }
    }
}
