package com.claramente.feature.ar.component

import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.core.designsystem.theme.ClaramenteRadius
import com.claramente.feature.ar.state.ArMode
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ArModeChip(mode: ArMode, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(ClaramenteRadius.Md)
    Column(
        modifier = modifier
            .clip(shape)
            .background(if (selected) ClaramenteColors.SkyMist else ClaramenteColors.White)
            .border(2.dp, if (selected) ClaramenteColors.Sky else ClaramenteColors.NeutralBorder, shape)
            .selectable(
                selected = selected,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(horizontal = 8.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = mode.title,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp),
            color = if (selected) ClaramenteColors.SkyDeep else ClaramenteColors.Ink,
        )
        Text(
            text = mode.caption,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, lineHeight = 15.sp),
            color = ClaramenteColors.TextSecondary,
            modifier = Modifier.padding(top = 2.dp),
        )
    }
}
