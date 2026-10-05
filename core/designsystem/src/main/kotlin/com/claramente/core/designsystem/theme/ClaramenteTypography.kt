package com.claramente.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object ClaramenteTypography {
    val typography = Typography(
        headlineMedium = TextStyle(fontSize = 28.sp, lineHeight = 34.sp, fontWeight = FontWeight.Bold),
        titleLarge = TextStyle(fontSize = 22.sp, lineHeight = 28.sp, fontWeight = FontWeight.SemiBold),
        titleMedium = TextStyle(fontSize = 17.sp, lineHeight = 22.sp, fontWeight = FontWeight.SemiBold),
        bodyLarge = TextStyle(fontSize = 16.sp, lineHeight = 22.sp),
        bodyMedium = TextStyle(fontSize = 14.sp, lineHeight = 20.sp),
        labelMedium = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold),
    )
}
