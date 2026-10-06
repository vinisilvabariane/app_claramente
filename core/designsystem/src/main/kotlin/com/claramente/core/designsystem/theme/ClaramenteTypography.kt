package com.claramente.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object ClaramenteTypography {
    val typography = Typography(
        headlineMedium = TextStyle(fontFamily = ClaramenteFonts.display, fontWeight = FontWeight.Bold, fontSize = 26.sp),
        headlineSmall = TextStyle(fontFamily = ClaramenteFonts.display, fontWeight = FontWeight.Bold, fontSize = 22.sp),
        titleLarge = TextStyle(fontFamily = ClaramenteFonts.display, fontWeight = FontWeight.Bold, fontSize = 20.sp),
        titleMedium = TextStyle(fontFamily = ClaramenteFonts.display, fontWeight = FontWeight.Bold, fontSize = 17.sp),
        titleSmall = TextStyle(fontFamily = ClaramenteFonts.display, fontWeight = FontWeight.Bold, fontSize = 16.sp),
        bodyLarge = TextStyle(fontFamily = ClaramenteFonts.body, fontWeight = FontWeight.Normal, fontSize = 15.sp),
        bodyMedium = TextStyle(fontFamily = ClaramenteFonts.body, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
        bodySmall = TextStyle(fontFamily = ClaramenteFonts.body, fontWeight = FontWeight.Normal, fontSize = 13.sp, lineHeight = 19.sp),
        labelLarge = TextStyle(fontFamily = ClaramenteFonts.body, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp),
        labelMedium = TextStyle(fontFamily = ClaramenteFonts.body, fontWeight = FontWeight.ExtraBold, fontSize = 13.sp),
        labelSmall = TextStyle(fontFamily = ClaramenteFonts.body, fontWeight = FontWeight.ExtraBold, fontSize = 11.sp),
    )
}
