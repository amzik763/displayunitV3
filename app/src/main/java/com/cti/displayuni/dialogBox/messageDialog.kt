package com.cti.displayuni.dialogBox

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cti.displayuni.components.CustomRoundedButton
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.DialogModel
import com.cti.displayuni.utility.animateDPInfinite
import com.cti.displayuni.utility.mFont.nk
import com.cti.displayuni.utility.mFont.nkbold
import com.cti.displayuni.utility.mFont.nkmedium
import com.cti.displayuni.utility.mFont.poppinsregular
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents

//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun MessageDialog(
    dialogModel: DialogModel,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
){

    val conf = LocalConfiguration.current
    val dnsty = conf.densityDpi

    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables
    var fillMaxWidth = 0.65f
    var fillMaxHeight = 0.45f
    var mainHeaderFont = 58.sp
    var semiHeaderFont = 36.sp
    var textFont1 = 18.sp
    var maxWidth = 0.3f
    var width = 180.dp
    var topPadding = 64.dp
    var startPadding = 36.dp
    var endPadding = 48.dp
    var bottomPadding = 48.dp
    var height = 40.dp
    var imgSize = 50.dp
    var fpaTopPadding = 36.dp
    var floorTopPadding = 16.dp
    var btnpadding = 9.dp


    Log.d("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        topPadding = 34.dp
        startPadding = 20.dp
        endPadding = 20.dp
        bottomPadding = 10.dp
        fillMaxWidth = 0.6f
        fillMaxHeight = 0.45f
        maxWidth = 0.24f
        startPadding = 16.dp
        mainHeaderFont = 26.sp
        semiHeaderFont = 14.sp
        textFont1 = 11.sp
        topPadding = 16.dp
        width = 100.dp
        height = 28.dp
        imgSize = 80.dp
        fpaTopPadding = 20.dp
        floorTopPadding = 7.dp
        btnpadding = 4.dp

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        topPadding = 64.dp
        startPadding = 36.dp
        endPadding = 48.dp
        bottomPadding = 48.dp
        fillMaxWidth = 0.65f
        fillMaxHeight = 0.45f
        maxWidth = 0.3f
        startPadding = 36.dp
        mainHeaderFont = 56.sp
        semiHeaderFont = 36.sp
        textFont1 = 24.sp
        topPadding = 24.dp
        width = 210.dp
        height = 50.dp
        imgSize =200.dp
        fpaTopPadding = 36.dp
        floorTopPadding = 16.dp
        btnpadding = 9.dp

        Log.d("Desktop: ", wd.toString())
    }


    Dialog(
        onDismissRequest = {
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .border(1.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(fillMaxWidth)
              .fillMaxHeight(fillMaxHeight)
              .background(pureWhite)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(maxWidth)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    val animatedOffset = animateDPInfinite(finalOffset = 20.dp, duration = 1000)

                    Image(painter = painterResource(id = myComponents.mUiViewModel.dialogModel.imageResource),
                        contentDescription = "message",
                        modifier = Modifier.size(imgSize)
                            .padding(animatedOffset)
                    )
                }

                Column(modifier = Modifier
                    .padding(8.dp)
                    .width(8.dp)) {
                    Divider(
                        Modifier
                            .fillMaxHeight()
                            .padding(
                                top = 4.dp,
                                bottom = 10.dp
                            ),
                        color = lightOrange
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(top = topPadding, start = startPadding, end = endPadding, bottom = bottomPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = dialogModel.dialogHeaderText,
                            style = TextStyle(
                                fontSize = mainHeaderFont,
                                color = lightBlack,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontFamily = nkbold
                            )
                        )

                        Text(modifier = Modifier.padding(
                            top = fpaTopPadding),
                            text = dialogModel.dialogSubHeaderText,
                            style = TextStyle(
                                fontSize = semiHeaderFont,
                                color = lightBlack,
                                textAlign = TextAlign.Center,
                                fontFamily = nkmedium
                            )
                        )
                        Text(modifier = Modifier.padding(top = floorTopPadding),
                            text = dialogModel.dialogText,
                            style = TextStyle(
                                fontSize = textFont1,
                                color = lightBlack,
                                textAlign = TextAlign.Center,
                                fontFamily = nk
                            )
                        )
                    }

                    Column(modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End){

                        CustomRoundedButton(onClick = {
                            myComponents.mUiViewModel.hideMessageDialog()
                        }, text = "OK")
                    }
                }
            }
        }
    }
}