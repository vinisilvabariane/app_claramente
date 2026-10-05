package com.claramente.feature.lesson.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.claramente.core.ar.helper.ArCoreProbe
import com.claramente.core.ar.model.ArMode
import com.claramente.core.designsystem.component.CenterMessage
import com.claramente.core.model.lesson.Lesson
import kotlinx.coroutines.delay

@Composable
internal fun LessonModeGate(lesson: Lesson, onBack: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var detected by remember { mutableStateOf(ArMode.CHECKING) }
    var forceViewer by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        var mode = ArCoreProbe.mode(context)
        while (mode == ArMode.CHECKING) {
            delay(300)
            mode = ArCoreProbe.mode(context)
        }
        detected = mode
    }

    val mode = if (forceViewer) ArMode.VIEWER else detected
    when (mode) {
        ArMode.CHECKING -> CenterMessage(text = "Verificando realidade aumentada…", modifier = modifier)
        ArMode.AR -> ArLessonContent(lesson = lesson, onBack = onBack, onUseViewer = { forceViewer = true }, modifier = modifier)
        ArMode.VIEWER -> ViewerLessonContent(lesson = lesson, onBack = onBack, modifier = modifier)
    }
}
