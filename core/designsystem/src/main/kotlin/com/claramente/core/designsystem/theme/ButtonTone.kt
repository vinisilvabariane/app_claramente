package com.claramente.core.designsystem.theme

import androidx.compose.ui.graphics.Color

enum class ButtonTone(val main: Color, val deep: Color) {
    PRIMARY(main = ClaramenteColors.Sky, deep = ClaramenteColors.SkyDeep),
    SUCCESS(main = ClaramenteColors.Sprout, deep = ClaramenteColors.SproutDeep),
}
