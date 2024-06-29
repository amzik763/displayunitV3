package com.cti.displayuni.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.showLogs

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    backgroundColor: Color = Color.White,
) {
    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont = 25.sp
    var textFont2 = 20.sp
    var height = 0.78f
    var dWidth = 150.dp
    var width = widthDP/4f
    var sHeight= 12.dp
    var padding = 8.dp
    var padding2 = 16.dp
    var startpadding = 20.dp
    var topPadding = 24.dp
    var btnWidth = 140.dp
    var btnHeight = 40.dp

    showLogs("dwinsize: ", wd.toString())


    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {


        textFont = 16.sp
        height = 0.72f
        textFont2 = 12.sp
        dWidth = 90.dp
        width = widthDP/4f
        sHeight= 6.dp
        padding = 4.dp
        padding2 = 8.dp
        btnWidth = 100.dp
        btnHeight = 35.dp
        topPadding = 14.dp
        startpadding = 10.dp


        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont = 25.sp
        textFont2 = 20.sp
        height = 0.78f
        dWidth = 150.dp
        width = widthDP/4f
        sHeight= 12.dp
        startpadding = 20.dp
        padding = 8.dp
        padding2 = 16.dp
        btnWidth = 140.dp
        btnHeight = 40.dp
        topPadding = 24.dp

        showLogs("Desktop: ", wd.toString())
    }


    val formattedDescription = description.replace(",", "\n")

    Card(
        modifier = modifier
            .fillMaxHeight(height)
           .width(width)
            .padding(padding),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = padding)
    ) {
        Text(text = title,
            fontSize = textFont,
            fontFamily = mFont.poppinsbold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = topPadding, bottom = padding, start = startpadding))

        LazyColumn(modifier = Modifier.padding(start = padding2, end = padding2)) {
            formattedDescription.split("\n").forEach { line ->
                item {
                    Text(
                        text = line,
                        fontSize = textFont2,
                        fontFamily = mFont.poppinsregular
                    )
                }
            }
        }
    }
}

