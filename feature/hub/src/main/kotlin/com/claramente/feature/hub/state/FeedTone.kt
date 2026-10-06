package com.claramente.feature.hub.state

import com.claramente.core.designsystem.theme.ClaramenteColors
import androidx.compose.ui.graphics.Color

enum class FeedTone(val color: Color) {
    NOTICE(ClaramenteColors.Berry),
    ACTIVITY(ClaramenteColors.Sun),
    ACHIEVEMENT(ClaramenteColors.Sprout),
    REMINDER(ClaramenteColors.Sky),
}
