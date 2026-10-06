package com.claramente.core.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.dp

@Composable
fun ArGlyph(color: Color, modifier: Modifier = Modifier.size(24.dp)) {
    Canvas(modifier) {
        scale(size.minDimension / 24f, Offset.Zero) {
            val stroke = Stroke(width = 2.4f, cap = StrokeCap.Round, join = StrokeJoin.Round)
            val top = Path().apply {
                moveTo(7f, 8.2f)
                lineTo(12f, 5f)
                lineTo(17f, 8.2f)
                lineTo(12f, 11.4f)
                close()
            }
            val sides = Path().apply {
                moveTo(7f, 8.2f)
                lineTo(7f, 14.4f)
                lineTo(12f, 17.6f)
                lineTo(17f, 14.4f)
                lineTo(17f, 8.2f)
            }
            val edge = Path().apply {
                moveTo(12f, 11.4f)
                lineTo(12f, 17.6f)
            }
            drawPath(top, color, style = stroke)
            drawPath(sides, color, style = stroke)
            drawPath(edge, color, style = stroke)
        }
    }
}
