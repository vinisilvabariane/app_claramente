package com.claramente.core.designsystem.theme

import androidx.compose.animation.core.CubicBezierEasing

object ClaramenteMotion {
    const val Fast = 240
    const val Medium = 480
    const val Slow = 720

    val Gentle = CubicBezierEasing(0.22f, 0.61f, 0.36f, 1f)
    val Bounce = CubicBezierEasing(0.34f, 1.56f, 0.64f, 1f)
}
