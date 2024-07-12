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
        fontSize = 24.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),

    headlineSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),


    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
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

    headlineSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
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
        fontSize = 54.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 46.sp,
    ),

    headlineSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    )
)

val ExpandedTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 58.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = nkFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp,
    ),

    headlineSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    )
)