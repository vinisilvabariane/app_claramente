package com.claramente.feature.hub.component

import com.claramente.core.designsystem.component.PrimaryButton
import com.claramente.core.designsystem.theme.ButtonTone
import com.claramente.core.designsystem.theme.ClaramenteColors
import com.claramente.core.designsystem.theme.ClaramenteRadius
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ContinueCard(onContinue: () -> Unit) {
    val shape = RoundedCornerShape(ClaramenteRadius.Xl)
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = 6.dp)
                .background(ClaramenteColors.SproutDeep, shape),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ClaramenteColors.Sprout, shape)
                .padding(22.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                "CONTINUE DE ONDE PAROU",
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp, letterSpacing = 1.sp),
                color = ClaramenteColors.White.copy(alpha = 0.85f),
            )
            Text(
                "Fundamentos de psicologia",
                style = MaterialTheme.typography.titleLarge,
                color = ClaramenteColors.White,
            )
            Text(
                "68% concluído na Sala de aula.",
                style = MaterialTheme.typography.bodyMedium,
                color = ClaramenteColors.White.copy(alpha = 0.92f),
                modifier = Modifier.padding(bottom = 12.dp),
            )
            PrimaryButton(
                text = "Continuar",
                onClick = onContinue,
                tone = ButtonTone.SUCCESS,
                containerColor = ClaramenteColors.White,
                textColor = ClaramenteColors.SproutDeep,
            )
        }
    }
}
