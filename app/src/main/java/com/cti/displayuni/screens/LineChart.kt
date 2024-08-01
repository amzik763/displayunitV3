package com.cti.displayuni.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawContext
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cti.displayuni.R
import com.cti.displayuni.components.ChartDropDown
import com.cti.displayuni.components.CustomRoundedButton
import com.cti.displayuni.ui.theme.dimens
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.showLogs


//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun LineChart(
    onCloseClicked: () -> Unit
){
    val wd = mParameters.mWidthinPx

    var toppadding = 40.dp
    var bottompadding = 16.dp

    showLogs("dwinsize: ", wd.toString())
    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {
        bottompadding = 8.dp
        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {
        toppadding = 40.dp
        bottompadding = 16.dp
        showLogs("Desktop: ", wd.toString())
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .fillMaxHeight()
        .fillMaxWidth()
        .background(color = Color.White),
        verticalArrangement = Arrangement.SpaceBetween){

        Row (modifier = Modifier.fillMaxWidth()){
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.62f)
                    .padding(end = MaterialTheme.dimens.endPadding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                ChartDropDown()

                Row(
                    modifier = Modifier
                        .border(3.dp, color = lightBlack)
                        .padding(16.dp)
                )  {
                    CommonText(name = "R: ", value = "14.2")

                    Spacer(modifier = Modifier.width(MaterialTheme.dimens.topPadding))
                    CommonText(name = "CFK: ", value = "14")

                    Spacer(modifier = Modifier.width(MaterialTheme.dimens.topPadding))
                    CommonText(name = "X: ", value = "142")
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(MaterialTheme.dimens.topPadding),
                horizontalAlignment = Alignment.End
            ) {
                ChartCircle()
            }
        }


            LineChartDemo()

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(top = toppadding, bottom = bottompadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            CustomRoundedButton(onClick = {
                onCloseClicked()
            }, text = "Close")
        }

    }
}

@Composable
fun LineChartDemo() {
    val dateData = mapOf(
        "28-10-24" to 12f,
        "29-10-24" to 6f,
        "30-10-24" to 1f,
        "31-10-24" to 22f,
        "01-11-24" to 8f,
        "02-11-24" to 9f,
        "03-11-24" to 10f,
        "04-11-24" to 11f,
        "05-11-24" to 13f,
        "06-11-24" to 15f,
        "07-11-24" to 29f,
        "08-11-24" to 17f,
        "09-11-24" to 18f,
        "10-11-24" to 9f,
        "11-11-24" to 20f,
        "12-11-24" to 21f,
        "13-11-24" to 13f,
        "14-11-24" to 24f,
        "15-11-24" to 25f,
        "16-11-24" to 26f,
        "17-11-24" to 7f,
        "18-11-24" to 28f,
        "19-11-24" to 29f,
        "20-11-24" to 30f,
        "21-11-24" to 16f,
        "22-11-24" to 6f,
        "23-11-24" to 1f,
        "24-11-24" to 0f,
        "25-11-24" to 16f,

    )

    val pointsData = dateData.entries.mapIndexed { index, entry ->
        index.toFloat() to entry.value
    }

    val labels = dateData.keys.toList()
    Column(
        modifier = Modifier
            .padding(MaterialTheme.dimens.startPadding) // Container padding
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LineChart(
            points = pointsData,
            labels = labels,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun LineChart(points: List<Pair<Float, Float>>, labels: List<String>, modifier: Modifier = Modifier) {
    var scale by remember { mutableFloatStateOf(1f) }
    val padding = 0.dp
    val topPadding = 32.dp // Increased top padding for X-axis labels
    val pointLabelPadding = 2.dp // Padding for point labels
    val roundedRectangleRadius = 2.dp // Radius for rounded rectangle
    val maxValue = points.maxOf { it.second }
    val minValue = points.minOf { it.second }

    val state = rememberTransformableState { zoomChange, _, _ ->
        scale *= zoomChange
    }

    Canvas(
        modifier = modifier
            .padding(start = 24.dp)
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    scale *= zoom
                }
            }
            .transformable(state)
    ) {
        val xAxisSpacing = (size.width - 2 * padding.toPx()) / (points.size - 1) * scale
        val yAxisHeight = size.height - 2 * padding.toPx()
        val yRange = maxValue - minValue

        // Draw Horizontal Grid Lines and Y-Axis Values
        val stepCount = 6
        val stepSize = (yRange / stepCount).toInt().coerceAtLeast(1)
        val startYValue = (minValue / stepSize).toInt() * stepSize
        val endYValue = (maxValue / stepSize).toInt() * stepSize

        for (value in startYValue..endYValue step stepSize) {
            val y = size.height - padding.toPx() - (value - minValue) / yRange * yAxisHeight
            drawLine(
                color = lightGrey,
                start = Offset(padding.toPx(), y),
                end = Offset(size.width - padding.toPx(), y),
                strokeWidth = 2f
            )
            drawContext.canvas.nativeCanvas.drawText(
                value.toString(),
                padding.toPx() - 40f,
                y + 10f, // Offset to center the text with the line
                android.graphics.Paint().apply {
                    textSize = 16f
                    textAlign = android.graphics.Paint.Align.RIGHT
                }
            )
        }

        // Draw Vertical Grid Lines
        for (i in points.indices) {
            val x = padding.toPx() + i * xAxisSpacing
            drawLine(
                color = lightGrey,
                start = Offset(x, padding.toPx()),
                end = Offset(x, size.height - padding.toPx()),
                strokeWidth = 2f
            )
        }

        // Draw Line and Point Values
        for (i in 0 until points.size - 1) {
            val x1 = padding.toPx() + i * xAxisSpacing
            val y1 = size.height - padding.toPx() - (points[i].second - minValue) / yRange * yAxisHeight
            val x2 = padding.toPx() + (i + 1) * xAxisSpacing
            val y2 = size.height - padding.toPx() - (points[i + 1].second - minValue) / yRange * yAxisHeight

            drawLine(
                color = Color.Blue,
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = 3f
            )

            drawCircle(
                color = Color.Red,
                center = Offset(x1, y1),
                radius = 6f
            )

            if (i == points.size - 2) {
                drawCircle(
                    color = Color.Red,
                    center = Offset(x2, y2),
                    radius = 6f
                )
            }

            // Draw Value Labels Above Points Inside Rounded Rectangle
            drawPointLabel(
                drawContext = drawContext,
                value = points[i].second.toInt().toString(),
                x = x1,
                y = y1 - 20f, // Position above the point
                padding = pointLabelPadding
            )
        }

        // Draw X-axis Labels with increased top padding and rotation
        for (i in labels.indices) {
            val x = padding.toPx() + i * xAxisSpacing

            // Save canvas state before rotating
            drawContext.canvas.save()

            // Rotate canvas for text drawing
            drawContext.canvas.rotate(-40f, x, size.height - padding.toPx() + topPadding.toPx()) // Rotate the text

            drawContext.canvas.nativeCanvas.drawText(
                labels[i],
                x,
                size.height - padding.toPx() + topPadding.toPx(),
                android.graphics.Paint().apply {
                    textSize = 16f
                    textAlign = android.graphics.Paint.Align.CENTER
                }
            )

            // Restore canvas state after drawing
            drawContext.canvas.restore()
        }
    }
}



fun DrawScope.drawPointLabel(
    drawContext: DrawContext,
    value: String,
    x: Float,
    y: Float,
    padding: Dp
) {
    val textPaint = android.graphics.Paint().apply {
        color = android.graphics.Color.BLACK
        textSize = 16f
        textAlign = android.graphics.Paint.Align.CENTER
        isAntiAlias = true
    }

    val textBounds = android.graphics.Rect().apply {
        textPaint.getTextBounds(value, 0, value.length, this)
    }

    val boxHeight = textBounds.height() + 2 * padding.toPx()

    drawContext.canvas.nativeCanvas.drawText(
        value,
        x,
        y - (boxHeight / 2 - textBounds.height() / 2),
        textPaint
    )
}


@Composable
fun CommonText(
    name: String,
    value:String = "",
    style: TextStyle = TextStyle.Default
) {
    Column(verticalArrangement = Arrangement.SpaceAround) {
        Row {
            Text(
                text = name,
                style = style.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
            )
            Text(
                text = value,
                style = style.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
            )
        }
    }
}

@Composable
fun ChartCircle() {

    Image(painter = painterResource(id = R.drawable.circle),
        contentDescription ="Circle1",
        modifier = Modifier
            .size(MaterialTheme.dimens.chartCircleSize)
    )
}




