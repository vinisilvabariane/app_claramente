package com.claramente.feature.ar.policy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TrackingPoliciesTest {

    @Test
    fun modelStaysVisibleDuringShortTrackingLoss() {
        assertTrue(TrackingGracePolicy.isVisible(lastSeenMs = 1_000L, nowMs = 1_000L))
        assertTrue(TrackingGracePolicy.isVisible(lastSeenMs = 1_000L, nowMs = 2_100L))
    }

    @Test
    fun modelHidesAfterGracePeriod() {
        assertFalse(TrackingGracePolicy.isVisible(lastSeenMs = 1_000L, nowMs = 2_300L))
        assertFalse(TrackingGracePolicy.isVisible(lastSeenMs = null, nowMs = 2_300L))
    }

    @Test
    fun cubeKeepsCurrentFaceWhileItIsVisible() {
        assertEquals(3, CubeFacePolicy.select(setOf(0, 3, 5), current = 3))
    }

    @Test
    fun cubeSwitchesToAnotherVisibleFaceWhenCurrentIsLost() {
        assertEquals(0, CubeFacePolicy.select(setOf(0, 5), current = 3))
        assertNull(CubeFacePolicy.select(emptySet(), current = 3))
    }
}
