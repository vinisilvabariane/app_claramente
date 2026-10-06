package com.claramente.feature.ar.component

import io.github.sceneview.SceneScope
import io.github.sceneview.math.Position
import androidx.compose.runtime.Composable

@Composable
internal fun SceneScope.SceneLabel(text: String, position: Position, cameraPosition: () -> Position) {
    TextNode(
        text = text,
        fontSize = 56f,
        textColor = android.graphics.Color.WHITE,
        backgroundColor = 0x990B3B57.toInt(),
        widthMeters = 0.08f,
        heightMeters = 0.02f,
        position = position,
        cameraPositionProvider = cameraPosition,
    )
}
