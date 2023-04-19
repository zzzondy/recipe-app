package com.recipeapp.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val appFontFamily = FontFamily(
    fonts = listOf(
        Font(resId = R.font.poppins_regular),
        Font(resId = R.font.poppins_bold)
    )
)

val typography = RecipeAppTypography(
    regularHeading = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 56.sp,
    ),
    regularH1 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 48.sp
    ),
    regularH2 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 40.sp
    ),
    regularH3 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 32.sp
    ),
    regularH4 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 24.sp
    ),
    regularH5 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 20.sp
    ),
    regularP = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 16.sp
    ),
    regularLabel = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 14.sp
    ),
    regularSmall = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 12.sp
    ),
    regularTiny = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 10.sp
    ),
    boldHeading = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 56.sp
    ),
    boldH1 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),
    boldH2 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    boldH3 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    boldH4 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    boldH5 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    boldP = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    boldLabel = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    boldSmall = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    boldTiny = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp
    ),
)