package com.cti.displayuni.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cti.displayuni.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val nkFontFamily = FontFamily(
    Font(R.font.nk, FontWeight.Normal),
    Font(R.font.nkmedium, FontWeight.Medium),
    Font(R.font.nkbold, FontWeight.Bold),
    Font(R.font.nkextrabold, FontWeight.ExtraBold)
)

val poppinsFontFamily = FontFamily(
    Font(R.font.poppinsregular, FontWeight.Normal),
    Font(R.font.poppinsmedium, FontWeight.Medium),
    Font(R.font.poppinsbold, FontWeight.Bold),
    Font(R.font.poppinsextrabold, FontWeight.ExtraBold)
)

val SmallTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    )
)

val  MediumTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 46.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),

    labelMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    )
)

val LargeTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
    )
)

val ExpandedTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
)