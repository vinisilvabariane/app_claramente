package com.claramente.feature.ar.policy

import com.google.ar.core.Frame
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.core.Pose

internal object QrPlacementPolicy {
    private const val SURFACE_WAIT_MS = 1500L
    private const val MAX_SURFACE_DISTANCE_METERS = 2f

    fun surfaceHit(frame: Frame, centerX: Float, centerY: Float): HitResult? =
        frame.hitTest(centerX, centerY).firstOrNull { hit ->
            val plane = hit.trackable as? Plane
            plane != null &&
                plane.type == Plane.Type.HORIZONTAL_UPWARD_FACING &&
                plane.isPoseInPolygon(hit.hitPose) &&
                hit.distance <= MAX_SURFACE_DISTANCE_METERS
        }

    fun shouldFloat(requestedAtMs: Long, nowMs: Long): Boolean = nowMs - requestedAtMs >= SURFACE_WAIT_MS

    fun floatingPose(cameraPose: Pose): Pose {
        val ahead = cameraPose.compose(Pose.makeTranslation(0f, -0.1f, -0.5f))
        return Pose(ahead.translation, floatArrayOf(0f, 0f, 0f, 1f))
    }
}
