package com.claramente.core.ar.policy

import com.claramente.core.ar.model.ArHint
import org.junit.Assert.assertEquals
import org.junit.Test

class ArHintPolicyTest {
    @Test
    fun `sem superficie detectada pede para procurar`() {
        assertEquals(ArHint.SEARCHING_SURFACE, ArHintPolicy.hint(planesDetected = false, trackingLost = false))
    }

    @Test
    fun `com superficie detectada pede o toque`() {
        assertEquals(ArHint.TAP_TO_PLACE, ArHintPolicy.hint(planesDetected = true, trackingLost = false))
    }

    @Test
    fun `rastreamento perdido tem prioridade`() {
        assertEquals(ArHint.TRACKING_LOST, ArHintPolicy.hint(planesDetected = true, trackingLost = true))
        assertEquals(ArHint.TRACKING_LOST, ArHintPolicy.hint(planesDetected = false, trackingLost = true))
    }
}
