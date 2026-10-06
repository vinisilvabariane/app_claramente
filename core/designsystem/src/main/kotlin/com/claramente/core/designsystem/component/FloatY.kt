package com.claramente.core.designsystem.component

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FloatY(
    modifier: Modifier = Modifier,
    distance: Dp = 6.dp,
    durationMs: Int = 2600,
    content: @Composable () -> Unit,
) {
    val distancePx = with(LocalDensity.current) { distance.toPx() }
    val transition = rememberInfiniteTransition(label = "floatY")
    val offsetY by transition.animateFloat(
        initialValue = 0f,
        targetValue = -distancePx,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMs, easing = CubicBezierEasing(0.37f, 0f, 0.63f, 1f)),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "translationY",
    )
    Box(modifier.graphicsLayer { translationY = offsetY }) {
        content()
    }
}
