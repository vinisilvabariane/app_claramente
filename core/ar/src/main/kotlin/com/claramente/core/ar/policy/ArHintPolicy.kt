package com.claramente.core.ar.policy

import com.claramente.core.ar.model.ArHint

object ArHintPolicy {
    fun hint(planesDetected: Boolean, trackingLost: Boolean): ArHint = when {
        trackingLost -> ArHint.TRACKING_LOST
        planesDetected -> ArHint.TAP_TO_PLACE
        else -> ArHint.SEARCHING_SURFACE
    }
}
