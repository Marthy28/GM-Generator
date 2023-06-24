package com.example.dndcharactergenerator.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val CustomTypography = Typography(

    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(fontSize = 8.sp),
    displayLarge = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
    displayMedium = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
    )
