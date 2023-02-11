package com.kolanse.tiktaktoe.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kolanse.tiktaktoe.R

// Set of Material typography styles to start with

val MatterBold = FontFamily(
    Font(R.font.matter_700)
)
val MatterMedium = FontFamily(
    Font(R.font.matter_500)
)
val MatterRegular = FontFamily(
    Font(R.font.matter_400)
)
val MatterSemiBold = FontFamily(
    Font(R.font.matter_600)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = MatterBold,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        color = titleTextColor
    ),
    titleMedium = TextStyle(
        fontFamily = MatterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = MatterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = MatterBold,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp
    )
)
