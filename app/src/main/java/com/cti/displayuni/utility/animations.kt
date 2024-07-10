package com.cti.displayuni.utility

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun animateDPInfinite(
    initialOffset: Dp = 0.dp,
    finalOffset: Dp,
    duration: Int
): Dp {

    // Infinite transition
    val infiniteTransition = rememberInfiniteTransition(label = "")

    // Define an animated DP state that will oscillate between initialOffset and finalOffset
    val animatedOffset by infiniteTransition.animateValue(
        initialValue = initialOffset,
        targetValue = finalOffset,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Icon Animation"
    )

    return animatedOffset
}