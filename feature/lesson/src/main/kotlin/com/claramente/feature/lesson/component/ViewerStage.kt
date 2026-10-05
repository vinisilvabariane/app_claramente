package com.claramente.feature.lesson.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.claramente.core.model.lesson.LessonShape
import io.github.sceneview.Scene
import io.github.sceneview.math.Direction
import io.github.sceneview.math.Position
import io.github.sceneview.rememberCameraManipulator
import io.github.sceneview.rememberCameraNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberMainLightNode

@Composable
internal fun ViewerStage(shape: LessonShape, modifier: Modifier = Modifier) {
    val engine = rememberEngine()
    val cameraNode = rememberCameraNode(engine) {
        position = Position(z = 0.6f)
    }
    val mainLightNode = rememberMainLightNode(engine) {
        lightDirection = Direction(x = 0.358f, y = -0.894f, z = 0.268f)
        isShadowCaster = false
    }
    Scene(
        modifier = modifier,
        engine = engine,
        cameraNode = cameraNode,
        mainLightNode = mainLightNode,
        cameraManipulator = rememberCameraManipulator(
            orbitHomePosition = cameraNode.worldPosition,
            targetPosition = Position(y = 0.075f),
        ),
    ) {
        ShapeNode(shape = shape)
    }
}
