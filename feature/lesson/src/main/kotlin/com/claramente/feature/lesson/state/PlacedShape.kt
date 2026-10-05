package com.claramente.feature.lesson.state

import com.claramente.core.model.lesson.LessonShape
import com.google.ar.core.Anchor

class PlacedShape(
    val id: Long,
    val shape: LessonShape,
    val anchor: Anchor,
)
