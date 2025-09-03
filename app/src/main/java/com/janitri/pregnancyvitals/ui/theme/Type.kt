package com.janitri.pregnancyvitals.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.janitri.pregnancyvitals.R

// 1. Define Inter FontFamily
val InterFont = FontFamily(
    Font(R.font.inter_light, FontWeight.Light),   // 300
    Font(R.font.inter_regular, FontWeight.Normal), // 400
    Font(R.font.inter_medium, FontWeight.Medium),  // 500
    Font(R.font.inter_bold, FontWeight.Bold)       // 700
)

// 2. Define Typography styles
val Typography = Typography(
    // Figma spec: font-family: Inter, weight: 300, size: 12px, line-height: 100%
    bodySmall = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        lineHeight = 12.sp, // 100%
        letterSpacing = 0.sp
    ),

    // Example bodyLarge (can adjust as per your Figma)
    bodyLarge = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    // Example title style
    titleLarge = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    // Example label style
    labelSmall = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
