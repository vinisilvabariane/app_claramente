package com.claramente.feature.ar.component

import io.github.sceneview.SceneScope
import io.github.sceneview.math.Position
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
internal fun SceneScope.SolarSystemScene(cameraPosition: () -> Position) {
    val sun = remember(materialLoader) { materialLoader.createColorInstance(0xFFFFB020.toInt(), 0f, 0.9f, 0f) }
    val venus = remember(materialLoader) { materialLoader.createColorInstance(0xFFE8C37A.toInt(), 0f, 0.6f, 0.3f) }
    val earth = remember(materialLoader) { materialLoader.createColorInstance(0xFF2FB6D9.toInt(), 0f, 0.5f, 0.3f) }
    val mars = remember(materialLoader) { materialLoader.createColorInstance(0xFFD96B2F.toInt(), 0f, 0.6f, 0.3f) }
    SphereNode(radius = 0.028f, materialInstance = sun)
    SceneLabel("Sol", Position(y = 0.055f), cameraPosition)
    OrbitingBody("Vênus", 0.009f, 0.055f, 4200L, venus, cameraPosition)
    OrbitingBody("Terra", 0.012f, 0.085f, 6800L, earth, cameraPosition)
    OrbitingBody("Marte", 0.01f, 0.115f, 10500L, mars, cameraPosition)
}
