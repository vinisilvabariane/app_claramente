package com.claramente.feature.ar.policy

internal object SpinPolicy {
    private const val NANOS_PER_MILLI = 1_000_000L
    private const val FULL_TURN = 360f

    fun degrees(frameTimeNanos: Long, periodMs: Long): Float {
        val phase = (frameTimeNanos / NANOS_PER_MILLI) % periodMs
        return phase * FULL_TURN / periodMs
    }
}
