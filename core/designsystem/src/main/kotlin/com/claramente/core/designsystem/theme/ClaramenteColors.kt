package com.claramente.core.designsystem.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object ClaramenteColors {
    val Ink = Color(0xFF0B3B57)
    val InkDeep = Color(0xFF062A40)
    val Sky = Color(0xFF2FB6D9)
    val SkyDeep = Color(0xFF1B8FAE)
    val SkyMist = Color(0xFFE4F6FA)
    val Sun = Color(0xFFFF9224)
    val SunDeep = Color(0xFFDB6E00)
    val SunMist = Color(0xFFFFEEDD)
    val Sprout = Color(0xFF46C26E)
    val SproutDeep = Color(0xFF2E9E52)
    val SproutMist = Color(0xFFE4F8EA)
    val Berry = Color(0xFF7C5CFF)
    val BerryDeep = Color(0xFF5B3FE0)
    val BerryMist = Color(0xFFEEEAFF)
    val Cream = Color(0xFFFFF9EF)
    val TextSecondary = Color(0xFF5B7182)
    val White = Color(0xFFFFFFFF)
    val TabInactive = Color(0xFF9AA7AF)
    val FieldBorder = Color(0xFFE5EBEE)
    val FieldFill = Color(0xFFF4F7F9)
    val NeutralBorder = Color(0xFFE5EBEE)
    val NeutralBorderDeep = Color(0xFFCFD8DD)
    val ErrorFill = Color(0xFFFDECEA)
    val ErrorText = Color(0xFFB3261E)
    val Overlay = Color(0x06, 0x2A, 0x40, 0xB8)

    internal val LightScheme = lightColorScheme(
        primary = Sky,
        onPrimary = White,
        secondary = Sprout,
        onSecondary = White,
        tertiary = Sun,
        background = Cream,
        onBackground = Ink,
        surface = White,
        onSurface = Ink,
        onSurfaceVariant = TextSecondary,
        outline = FieldBorder,
        error = ErrorText,
    )
}
