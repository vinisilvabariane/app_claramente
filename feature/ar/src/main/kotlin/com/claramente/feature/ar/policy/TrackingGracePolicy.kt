package com.claramente.feature.ar.policy

internal object TrackingGracePolicy {
    private const val GRACE_MS = 1200L

    fun isVisible(lastSeenMs: Long?, nowMs: Long): Boolean =
        lastSeenMs != null && nowMs - lastSeenMs <= GRACE_MS
}
