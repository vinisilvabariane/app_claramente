package com.claramente.feature.lesson.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.claramente.core.ar.model.ArHint
import com.claramente.core.designsystem.theme.ClaramenteColors

@Composable
internal fun HintChip(hint: ArHint, modifier: Modifier = Modifier) {
    val text = when (hint) {
        ArHint.SEARCHING_SURFACE -> "Mova o aparelho devagar. Já dá para tocar no chão e tentar colocar a forma."
        ArHint.TAP_TO_PLACE -> "Superfície encontrada. Toque nela para colocar a forma."
        ArHint.TRACKING_LOST -> "Rastreamento perdido. Mova o aparelho devagar e ilumine o ambiente."
    }
    val color = when (hint) {
        ArHint.TAP_TO_PLACE -> ClaramenteColors.Accent
        ArHint.SEARCHING_SURFACE -> ClaramenteColors.Text
        ArHint.TRACKING_LOST -> ClaramenteColors.Warning
    }
    NoticeChip(text = text, color = color, modifier = modifier)
}
