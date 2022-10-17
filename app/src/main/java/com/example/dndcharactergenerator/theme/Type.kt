package com.example.dndcharactergenerator.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val CustomTypography = Typography(

    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(fontSize = 8.sp),
    h1 = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
    h2 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
    )