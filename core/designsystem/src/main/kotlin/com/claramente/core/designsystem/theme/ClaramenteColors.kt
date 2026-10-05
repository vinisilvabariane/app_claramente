package com.claramente.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

object ClaramenteColors {
    val Accent = Color(0xFF6EE7B7)
    val AccentDim = Color(0xFF3FB68A)
    val OnAccent = Color(0xFF04140C)
    val Background = Color(0xFF0B1020)
    val Surface = Color(0xFF141B30)
    val SurfaceAlt = Color(0xFF1B2440)
    val Outline = Color(0xFF2A3558)
    val Text = Color(0xFFE8ECF8)
    val TextMuted = Color(0xFF8E9ABB)
    val Warning = Color(0xFFF5C451)
    val Error = Color(0xFFFF6B6B)

    internal val DarkScheme = darkColorScheme(
        primary = Accent,
        onPrimary = OnAccent,
        secondary = AccentDim,
        tertiary = Warning,
        background = Background,
        surface = Surface,
        surfaceVariant = SurfaceAlt,
        outline = Outline,
        onBackground = Text,
        onSurface = Text,
        onSurfaceVariant = TextMuted,
        error = Error,
    )
}
