package com.claramente.feature.lesson.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.claramente.core.model.lesson.LessonShape
import io.github.sceneview.SceneScope
import io.github.sceneview.math.Position
import io.github.sceneview.math.Size

@Composable
internal fun SceneScope.ShapeNode(shape: LessonShape) {
    val material = remember(materialLoader, shape) {
        val color = when (shape) {
            LessonShape.CUBE -> Color(0xFF6EE7B7)
            LessonShape.SPHERE -> Color(0xFFFFB86B)
            LessonShape.CYLINDER -> Color(0xFF7C9CFF)
        }
        materialLoader.createColorInstance(color = color, metallic = 0f, roughness = 0.4f)
    }
    when (shape) {
        LessonShape.CUBE -> CubeNode(
            size = Size(0.15f),
            position = Position(y = 0.075f),
            materialInstance = material,
        )
        LessonShape.SPHERE -> SphereNode(
            radius = 0.08f,
            position = Position(y = 0.08f),
            materialInstance = material,
        )
        LessonShape.CYLINDER -> CylinderNode(
            radius = 0.06f,
            height = 0.15f,
            position = Position(y = 0.075f),
            materialInstance = material,
        )
    }
}
