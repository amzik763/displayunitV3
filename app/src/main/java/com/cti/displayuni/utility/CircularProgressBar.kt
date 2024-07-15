package com.cti.displayuni.utility

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.dimens
import kotlinx.coroutines.delay


/*@Composable
fun CircularProgressBar(
    percentage: Float,
    duration: Int,
    radius: Dp = 30.dp,
    color: Color = Color.Blue,
    strokeWidth: Dp = 2.dp,
    animDuration: Int = 10000,
    animDelay: Int = 0,
    onTimeEnd: () -> Unit

) {
    var remainingTime by remember { mutableIntStateOf(duration) }

    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(key1 = true) {
        val stepDuration = animDuration / duration
        for (i in duration downTo 0) {
            delay(1000L)
            remainingTime = i
            progress = (duration - i) / duration.toFloat()
        }
        onTimeEnd()
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 1f)
//            .padding(start = MaterialTheme.dimens.smallPadding)
    ) {
        Canvas(modifier = Modifier.size(radius * 1f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * progress,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = "${remainingTime}s",
            color = Color.Black,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}*/

@Composable
fun CircularProgressBar(
    percentage: Float,
    duration: Int,
    radius: Dp = 30.dp,
    color: Color = Color.Blue,
    strokeWidth: Dp = 2.dp,
    onTimeEnd: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius)
    ) {
        Canvas(modifier = Modifier.size(radius)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * (1 - percentage) ,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = "${(duration * percentage).toInt()}s",
            color = Color.Black,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight
        )
    }

    LaunchedEffect(percentage) {
        if (percentage == 0f) onTimeEnd()
    }
}