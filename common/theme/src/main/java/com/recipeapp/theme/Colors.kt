package com.recipeapp.theme

import androidx.compose.ui.graphics.Color

internal val lightColors = RecipeAppColors(
    isDarkTheme = false,
    neutral100 = Color(0xff181818),
    neutral90 = Color(0xff303030),
    neutral80 = Color(0xff484848),
    neutral70 = Color(0xff606060),
    neutral60 = Color(0xff797979),
    neutral50 = Color(0xff919191),
    neutral40 = Color(0xffa9a9a9),
    neutral30 = Color(0xffc1c1c1),
    neutral20 = Color(0xffd9d9d9),
    neutral10 = Color(0xfff1f1f1),
    white0 = Color(0xffffffff),
    primary100 = Color(0xff711F1F),
    primary90 = Color(0xff882525),
    primary80 = Color(0xff9e2b2b),
    primary70 = Color(0xffb53232),
    primary60 = Color(0xffCB3838),
    primary50 = Color(0xffe23e3e),
    primary40 = Color(0xffe86565),
    primary30 = Color(0xffee8b8b),
    primary20 = Color(0xfff3b2b2),
    primary10 = Color(0xfff9d8d8),
    primary0 = Color(0xfffcecec),
    secondary100 = Color(0xff804e00),
    secondary90 = Color(0xff995e00),
    secondary80 = Color(0xffb36d00),
    secondary70 = Color(0xffcc7d00),
    secondary60 = Color(0xffd58200),
    secondary50 = Color(0xffff9c00),
    secondary40 = Color(0xffffa61a),
    secondary30 = Color(0xffffba4d),
    secondary20 = Color(0xffff9c00),
    secondary10 = Color(0xffffdeaa),
    secondary0 = Color(0xfffff7eb),
    rating100 = Color(0xffffb661),
    error100 = Color(0xffee1133),
    error10 = Color(0xfff7f0f1),
    success100 = Color(0xff31b057),
    success10 = Color(0xffceecd7)
)

internal val darkColors = lightColors.copy(isDarkTheme = true)