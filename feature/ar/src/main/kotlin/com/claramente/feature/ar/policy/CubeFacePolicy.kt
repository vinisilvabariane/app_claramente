package com.claramente.feature.ar.policy

internal object CubeFacePolicy {
    fun select(visibleFaces: Set<Int>, current: Int?): Int? =
        current?.takeIf { it in visibleFaces } ?: visibleFaces.minOrNull()
}
