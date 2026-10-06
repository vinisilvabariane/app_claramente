package com.claramente.core.designsystem.component

import com.claramente.core.designsystem.state.MascotPose
import com.claramente.core.designsystem.state.MascotReaction
import com.claramente.core.designsystem.theme.ClaramenteColors
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate

internal object MascotPainter {
    private const val VIEWBOX = 200f

    fun DrawScope.draw(pose: MascotPose, reaction: MascotReaction?) {
        val side = size.minDimension
        translate((size.width - side) / 2f, (size.height - side) / 2f) {
            scale(side / VIEWBOX, Offset.Zero) {
                val raisedArms = pose == MascotPose.CELEBRATE || reaction != null
                val oneArmUp = pose == MascotPose.WAVE && reaction == null
                drawHalo(raisedArms)
                if (reaction != null) drawSparks()
                drawArms(raisedArms, oneArmUp)
                drawBody()
                drawCheeks(reaction == MascotReaction.LOVE)
                drawEyes(reaction)
                drawMouth(reaction)
            }
        }
    }

    private fun DrawScope.drawHalo(raisedArms: Boolean) {
        drawCircle(ClaramenteColors.Sun, radius = 72f, center = Offset(100f, 108f), alpha = 0.16f)
        val alpha = if (raisedArms) 0.9f else 0.65f
        drawRay(Offset(100f, 26f), Offset(100f, 10f), alpha)
        drawRay(Offset(58f, 38f), Offset(48f, 26f), alpha)
        drawRay(Offset(142f, 38f), Offset(152f, 26f), alpha)
    }

    private fun DrawScope.drawRay(from: Offset, to: Offset, alpha: Float) {
        drawLine(ClaramenteColors.Sun, from, to, strokeWidth = 6f, cap = StrokeCap.Round, alpha = alpha)
    }

    private fun DrawScope.drawSparks() {
        drawSpark(Offset(30f, 60f), Offset(22f, 52f))
        drawSpark(Offset(26f, 70f), Offset(16f, 70f))
        drawSpark(Offset(170f, 60f), Offset(178f, 52f))
        drawSpark(Offset(174f, 70f), Offset(184f, 70f))
    }

    private fun DrawScope.drawSpark(from: Offset, to: Offset) {
        drawLine(ClaramenteColors.Sun, from, to, strokeWidth = 4f, cap = StrokeCap.Round, alpha = 0.85f)
    }

    private fun DrawScope.drawArms(raisedArms: Boolean, oneArmUp: Boolean) {
        val stroke = Stroke(width = 16f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        val left = when {
            raisedArms -> curve(62f, 118f, 40f, 108f, 30f, 84f, 34f, 64f)
            oneArmUp -> curve(62f, 118f, 44f, 118f, 32f, 104f, 30f, 86f)
            else -> curve(60f, 122f, 46f, 128f, 38f, 138f, 40f, 150f)
        }
        val right = if (raisedArms) {
            curve(138f, 118f, 160f, 108f, 170f, 84f, 166f, 64f)
        } else {
            curve(140f, 122f, 154f, 128f, 162f, 138f, 160f, 150f)
        }
        drawPath(left, ClaramenteColors.Sky, style = stroke)
        drawPath(right, ClaramenteColors.Sky, style = stroke)
    }

    private fun DrawScope.drawBody() {
        drawRoundRect(ClaramenteColors.Sky, Offset(46f, 54f), Size(108f, 108f), CornerRadius(40f))
        drawRoundRect(ClaramenteColors.White, Offset(46f, 54f), Size(108f, 54f), CornerRadius(40f, 27f), alpha = 0.14f)
    }

    private fun DrawScope.drawCheeks(love: Boolean) {
        val alpha = if (love) 0.85f else 0.55f
        drawCircle(ClaramenteColors.Sun, radius = 9f, center = Offset(72f, 118f), alpha = alpha)
        drawCircle(ClaramenteColors.Sun, radius = 9f, center = Offset(128f, 118f), alpha = alpha)
    }

    private fun DrawScope.drawEyes(reaction: MascotReaction?) {
        when (reaction) {
            MascotReaction.LOVE -> {
                drawPath(heart(82f, 101f), ClaramenteColors.Sun)
                drawPath(heart(118f, 101f), ClaramenteColors.Sun)
            }
            MascotReaction.LAUGH -> {
                val stroke = Stroke(width = 5f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                drawPath(arc(75f, 104f, 82f, 95f, 89f, 104f), ClaramenteColors.Ink, style = stroke)
                drawPath(arc(111f, 104f, 118f, 95f, 125f, 104f), ClaramenteColors.Ink, style = stroke)
            }
            else -> {
                val surprise = reaction == MascotReaction.SURPRISE
                val radius = if (surprise) 11f else 9f
                val pupil = if (surprise) 4f else 5f
                drawEye(82f, 102f, radius, reaction == MascotReaction.WINK, pupil)
                drawEye(118f, 102f, radius, false, pupil)
            }
        }
    }

    private fun DrawScope.drawEye(cx: Float, cy: Float, radius: Float, closed: Boolean, pupil: Float) {
        if (closed) {
            drawPath(
                arc(cx - 9f, cy, cx, cy + 7f, cx + 9f, cy),
                ClaramenteColors.Ink,
                style = Stroke(width = 4.5f, cap = StrokeCap.Round, join = StrokeJoin.Round),
            )
        } else {
            drawCircle(ClaramenteColors.White, radius = radius, center = Offset(cx, cy))
            drawCircle(ClaramenteColors.Ink, radius = pupil, center = Offset(cx, cy))
        }
    }

    private fun DrawScope.drawMouth(reaction: MascotReaction?) {
        when (reaction) {
            MascotReaction.SURPRISE -> drawCircle(ClaramenteColors.Ink, radius = 6f, center = Offset(100f, 124f))
            MascotReaction.LAUGH -> drawPath(
                Path().apply {
                    moveTo(80f, 120f)
                    quadraticTo(100f, 138f, 120f, 120f)
                    quadraticTo(100f, 130f, 80f, 120f)
                    close()
                },
                ClaramenteColors.Ink,
            )
            else -> drawPath(
                arc(84f, 122f, 100f, 133f, 116f, 122f),
                ClaramenteColors.Ink,
                style = Stroke(width = 5f, cap = StrokeCap.Round, join = StrokeJoin.Round),
            )
        }
    }

    private fun curve(x0: Float, y0: Float, x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float): Path =
        Path().apply {
            moveTo(x0, y0)
            cubicTo(x1, y1, x2, y2, x3, y3)
        }

    private fun arc(x0: Float, y0: Float, cx: Float, cy: Float, x1: Float, y1: Float): Path =
        Path().apply {
            moveTo(x0, y0)
            quadraticTo(cx, cy, x1, y1)
        }

    private fun heart(cx: Float, cy: Float): Path =
        Path().apply {
            moveTo(cx, cy + 6f)
            cubicTo(cx - 10f, cy - 4f, cx - 5f, cy - 11f, cx, cy - 5f)
            cubicTo(cx + 5f, cy - 11f, cx + 10f, cy - 4f, cx, cy + 6f)
            close()
        }
}
