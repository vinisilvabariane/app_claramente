package com.claramente.feature.login.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp

@Composable
internal fun LoginLayout(
    viewportHeight: Dp,
    modifier: Modifier = Modifier,
    hero: @Composable () -> Unit,
    card: @Composable () -> Unit,
) {
    Layout(contents = listOf(hero, card), modifier = modifier) { measurables, constraints ->
        val heroPlaceable = measurables[0].first().measure(constraints.copy(minHeight = 0))
        val cardMinHeight = (viewportHeight.roundToPx() - heroPlaceable.height).coerceAtLeast(0)
        val cardMaxHeight = if (constraints.hasBoundedHeight) maxOf(constraints.maxHeight, cardMinHeight) else Constraints.Infinity
        val cardPlaceable = measurables[1].first().measure(
            constraints.copy(minHeight = cardMinHeight, maxHeight = cardMaxHeight),
        )
        val width = if (constraints.hasBoundedWidth) constraints.maxWidth else maxOf(heroPlaceable.width, cardPlaceable.width)
        layout(width, heroPlaceable.height + cardPlaceable.height) {
            heroPlaceable.placeRelative(0, 0)
            cardPlaceable.placeRelative(0, heroPlaceable.height)
        }
    }
}
