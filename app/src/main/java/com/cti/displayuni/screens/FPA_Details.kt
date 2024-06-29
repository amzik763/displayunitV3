package com.cti.displayuni.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.components.CardComponent
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs

//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun FPA_Details(
    onCloseClicked: () -> Unit,
){

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont = 50.sp
    var textFont2 = 16.sp
    var height = 8.dp
    var dWidth = 150.dp
    var sWidth = 16.dp
    var sHeight= 12.dp
    var toppadding = 40.dp
    var bottompadding = 16.dp
    var btnWidth = 140.dp
    var btnHeight = 40.dp

    showLogs("dwinsize: ", wd.toString())


    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {


        textFont = 30.sp
        height = 4.dp
        textFont2 = 11.sp
        dWidth = 90.dp
        sWidth = 12.dp
        sHeight= 6.dp
        toppadding = 20.dp
        bottompadding = 8.dp
        btnWidth = 100.dp
        btnHeight = 35.dp


        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont = 50.sp
        textFont2 = 16.sp
        height = 8.dp
        dWidth = 150.dp
        sWidth = 16.dp
        sHeight= 12.dp
        toppadding = 40.dp
        bottompadding = 16.dp
        btnWidth = 140.dp
        btnHeight = 40.dp

        showLogs("Desktop: ", wd.toString())
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .fillMaxHeight()
        .fillMaxWidth()
        .background(color = Color.White)){
        Text(text = "FPA DETAILS",
            fontSize = textFont,
            fontWeight = FontWeight.Bold,
            fontFamily = mFont.poppinsbold)

        Divider(
            modifier = Modifier.width(dWidth)
                .height(height),
            color = orange
        )

        Spacer(modifier = Modifier.height(sHeight))

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(sHeight),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){

            CardComponent(
                title = "Start Shift 1",
                description = myComponents.mainViewModel.fpa1.value.toString(),
                backgroundColor = Color.White
            )
            CardComponent(
                title = "Start Shift 2",
                description = myComponents.mainViewModel.fpa2.value.toString(),
                backgroundColor = Color.White
            )
            CardComponent(
                title = "Mid Shift 1",
                description = myComponents.mainViewModel.fpa3.value.toString(),
                backgroundColor = Color.White
            )
            CardComponent(
                title = "Mid Shift 2",
                description = myComponents.mainViewModel.fpa4.value.toString(),
                backgroundColor = Color.White
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(top = toppadding, bottom = bottompadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = {
                onCloseClicked()
            },
                shape = RoundedCornerShape(9.dp),
                modifier = Modifier.size(width = btnWidth, height = btnHeight),
                border = BorderStroke(3.dp, darkBlue),
                colors = ButtonDefaults.buttonColors(contentColor = pureWhite, containerColor = darkBlue),
                contentPadding = PaddingValues(0.dp)

                ) {
                Text(
                    text = AnnotatedString("Close"),
                    style = TextStyle(
                        color = pureWhite,
                        fontSize = textFont2,
                        fontFamily = mFont.poppinsbold,
                        textAlign = TextAlign.Center
                )   )
            }
        }
    }
}

