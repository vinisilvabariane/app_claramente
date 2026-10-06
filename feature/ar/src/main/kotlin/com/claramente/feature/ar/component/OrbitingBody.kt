package com.claramente.feature.ar.component

import com.claramente.feature.ar.policy.SpinPolicy
import io.github.sceneview.SceneScope
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import androidx.compose.runtime.Composable
import com.google.android.filament.MaterialInstance

@Composable
internal fun SceneScope.OrbitingBody(
    label: String,
    radius: Float,
    distance: Float,
    periodMs: Long,
    material: MaterialInstance,
    cameraPosition: () -> Position,
) {
    Node(
        apply = {
            onFrame = { frameTimeNanos -> rotation = Rotation(y = SpinPolicy.degrees(frameTimeNanos, periodMs)) }
        },
    ) {
        Node(position = Position(x = distance)) {
            SphereNode(radius = radius, materialInstance = material)
            SceneLabel(label, Position(y = radius + 0.022f), cameraPosition)
        }
    }
}
