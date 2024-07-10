package com.cti.interfaceassembly.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(

    val smallPadding: Dp = 4.dp,
    val mediumPadding: Dp = 16.dp,
    val largePadding: Dp = 24.dp,
    val largeTextField: Dp = 200.dp,
    val mediumTextField: Dp = 150.dp,
    val smallTextField: Dp = 100.dp,
    val largeSize: Dp = 42.dp,
    val logoSize: Dp = 42.dp,
    val buttonWidth: Dp = 180.dp,
    val buttonHeight: Dp = 40.dp,
    val interfaceW:Dp = 300.dp,
    val interfaceH: Dp = 70.dp,
    val startPadding: Dp = 36.dp,
    val topPadding: Dp = 48.dp,
    val bottomPadding: Dp = 36.dp,
    val padding: Dp = 36.dp,
    val btnPadding: Dp = 9.dp
)

val SmallDimens = Dimens(
    buttonWidth = 120.dp,
    buttonHeight = 30.dp,
    interfaceW = 180.dp,
    interfaceH = 50.dp,
    startPadding = 28.dp,
    topPadding = 36.dp,
    bottomPadding = 28.dp,
    btnPadding = 6.dp
)

val MediumDimens = Dimens(
    startPadding = 16.dp,
    topPadding = 16.dp,
    padding = 10.dp,
    smallPadding = 4.dp,
    mediumPadding = 24.dp,
    largePadding = 24.dp,
    largeTextField = 200.dp,
    mediumTextField = 150.dp,
    smallTextField = 100.dp
)

val LargeDimens = Dimens(
    smallPadding = 12.dp,
    mediumPadding = 24.dp,
    largePadding = 36.dp,
    largeTextField = 200.dp,
    mediumTextField = 150.dp,
    smallTextField = 100.dp
)

val ExpandedDimens = Dimens(
    smallPadding = 12.dp,
    mediumPadding = 24.dp,
    largePadding = 36.dp,
    largeTextField = 300.dp,
    mediumTextField = 220.dp,
    smallTextField = 150.dp
)