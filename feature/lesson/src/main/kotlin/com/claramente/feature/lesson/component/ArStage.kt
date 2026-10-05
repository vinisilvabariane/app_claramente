package com.claramente.feature.lesson.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import com.claramente.core.ar.error.ArErrors
import com.claramente.core.ar.model.ArHint
import com.claramente.core.ar.policy.ArHintPolicy
import com.claramente.core.model.lesson.LessonShape
import com.claramente.feature.lesson.state.PlacedShape
import com.google.ar.core.Config
import com.google.ar.core.DepthPoint
import com.google.ar.core.Frame
import com.google.ar.core.Plane
import com.google.ar.core.TrackingFailureReason
import com.google.ar.core.TrackingState
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.arcore.createAnchorOrNull
import io.github.sceneview.math.Direction
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberEnvironmentLoader
import io.github.sceneview.rememberMainLightNode
import io.github.sceneview.rememberOnGestureListener
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference

@Composable
internal fun ArStage(
    shape: LessonShape,
    placed: List<PlacedShape>,
    onPlace: (PlacedShape) -> Unit,
    onHintChanged: (ArHint) -> Unit,
    onStatusChanged: (String) -> Unit,
    onFailed: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val currentOnPlace by rememberUpdatedState(onPlace)
    val currentOnHintChanged by rememberUpdatedState(onHintChanged)
    val currentOnStatusChanged by rememberUpdatedState(onStatusChanged)
    val currentOnFailed by rememberUpdatedState(onFailed)

    val latestFrame = remember { AtomicReference<Frame?>(null) }
    val nextId = remember { AtomicLong(0) }
    val planeCount = remember { AtomicInteger(-1) }
    val trackingLost = remember { AtomicBoolean(false) }
    val lastTap = remember { AtomicReference("nenhum toque") }

    fun publish() {
        val planes = planeCount.get().coerceAtLeast(0)
        currentOnHintChanged(ArHintPolicy.hint(planesDetected = planes > 0, trackingLost = trackingLost.get()))
        currentOnStatusChanged("superfícies: $planes · último toque: ${lastTap.get()}")
    }

    val engine = rememberEngine()
    val environmentLoader = rememberEnvironmentLoader(engine)
    val environment = remember(environmentLoader) {
        environmentLoader.createKTX1Environment(iblAssetFile = "environments/neutral/neutral_ibl.ktx")
    }
    val mainLightNode = rememberMainLightNode(engine) {
        lightDirection = Direction(x = 0.358f, y = -0.894f, z = 0.268f)
        isShadowCaster = false
    }

    ARScene(
        modifier = modifier,
        engine = engine,
        environmentLoader = environmentLoader,
        environment = environment,
        mainLightNode = mainLightNode,
        planeRenderer = true,
        sessionConfiguration = { session, config ->
            config.planeFindingMode = Config.PlaneFindingMode.HORIZONTAL
            config.instantPlacementMode = Config.InstantPlacementMode.LOCAL_Y_UP
            config.lightEstimationMode = Config.LightEstimationMode.DISABLED
            if (session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
                config.depthMode = Config.DepthMode.AUTOMATIC
            }
        },
        onSessionUpdated = { session, frame ->
            latestFrame.set(frame)
            val count = session.getAllTrackables(Plane::class.java)
                .count { it.trackingState == TrackingState.TRACKING }
            if (planeCount.getAndSet(count) != count) publish()
        },
        onTrackingFailureChanged = { reason ->
            val lost = reason != null && reason != TrackingFailureReason.NONE
            if (trackingLost.getAndSet(lost) != lost) publish()
        },
        onSessionFailed = { exception ->
            currentOnFailed(exception.message ?: ArErrors.SESSION_FAILED)
        },
        onGestureListener = rememberOnGestureListener(
            onSingleTapConfirmed = { event, node ->
                if (node == null) {
                    val frame = latestFrame.get()
                    val hits = frame?.let { runCatching { it.hitTest(event.x, event.y) }.getOrNull() }
                    val surfaceHit = hits?.firstOrNull { candidate ->
                        when (val trackable = candidate.trackable) {
                            is Plane -> trackable.type == Plane.Type.HORIZONTAL_UPWARD_FACING &&
                                trackable.isPoseInPolygon(candidate.hitPose)
                            is DepthPoint -> true
                            else -> false
                        }
                    }
                    val instantHit = if (surfaceHit == null && frame != null) {
                        runCatching { frame.hitTestInstantPlacement(event.x, event.y, 1.0f) }
                            .getOrNull()
                            ?.firstOrNull()
                    } else {
                        null
                    }
                    val hit = surfaceHit ?: instantHit
                    val anchor = hit?.createAnchorOrNull()
                    lastTap.set(
                        when {
                            frame == null -> "sem quadro da câmera"
                            hits == null -> "falha no teste de toque"
                            hit == null -> "nenhum ponto sob o dedo"
                            anchor == null -> "falha ao criar âncora"
                            surfaceHit != null -> "objeto colocado (superfície)"
                            else -> "objeto colocado (estimado)"
                        },
                    )
                    if (anchor != null) currentOnPlace(PlacedShape(nextId.incrementAndGet(), shape, anchor))
                } else {
                    lastTap.set("toque caiu em um objeto")
                }
                publish()
            },
        ),
    ) {
        placed.forEach { item ->
            key(item.id) {
                AnchorNode(
                    anchor = item.anchor,
                    visibleTrackingStates = setOf(TrackingState.TRACKING, TrackingState.PAUSED),
                ) {
                    ShapeNode(shape = item.shape)
                }
            }
        }
    }
}
