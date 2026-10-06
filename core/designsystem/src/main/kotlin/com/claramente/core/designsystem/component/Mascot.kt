package com.claramente.core.designsystem.component

import com.claramente.core.designsystem.state.MascotPose
import com.claramente.core.designsystem.state.MascotReaction
import com.claramente.core.designsystem.theme.ClaramenteMotion
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun Mascot(
    modifier: Modifier = Modifier,
    pose: MascotPose = MascotPose.HAPPY,
    size: Dp = 120.dp,
    tapReactions: Boolean = false,
) {
    var reaction by remember { mutableStateOf<MascotReaction?>(null) }
    var taps by remember { mutableIntStateOf(0) }
    val scale = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val view = LocalView.current
    LaunchedEffect(reaction, taps) {
        if (reaction != null) {
            delay(1100)
            reaction = null
        }
    }
    val tapModifier = if (tapReactions) {
        Modifier
            .semantics { contentDescription = "Lumi, a mascote — toque para uma reação" }
            .clickable(interactionSource = interactionSource, indication = null, role = Role.Button) {
                val next = MascotReaction.entries.random(Random)
                reaction = next
                view.announceForAccessibility("Lumi está " + next.label)
                taps++
                scope.launch {
                    scale.snapTo(1f)
                    scale.animateTo(1.12f, tween(ClaramenteMotion.Fast, easing = ClaramenteMotion.Bounce))
                    scale.animateTo(1f, tween(ClaramenteMotion.Fast, easing = ClaramenteMotion.Gentle))
                }
            }
    } else {
        Modifier
    }
    Canvas(
        modifier
            .then(tapModifier)
            .size(size)
            .graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
            },
    ) {
        with(MascotPainter) { draw(pose, reaction) }
    }
}
