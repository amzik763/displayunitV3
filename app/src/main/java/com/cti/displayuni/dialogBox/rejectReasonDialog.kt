package com.cti.displayuni.dialogBox

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cti.displayuni.R
import com.cti.displayuni.components.AddMark
import com.cti.displayuni.components.PartId
import com.cti.displayuni.components.ReasonDropdown
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.mFont.nkbold
import com.cti.displayuni.utility.mFont.nkmedium
import com.cti.displayuni.utility.mFont.poppinsregular
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.mUiViewModel
import com.cti.displayuni.utility.myComponents.mainViewModel

//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RejectReasonDialog(
//    uiviewModel: UiViewModel,
    onDismiss: () -> Unit,
//    onConfirm: () -> Unit,
//    onRetry: () -> Unit,
//    dialogText: String,
//    onClick() -> Unit
){
    val context = LocalContext.current
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
    var textFont2 = 18.sp
    var maxWidth = 0.3f
    var width = 180.dp
    var maxHeight = 0.3f
    var topPadding = 64.dp
    var startPadding = 36.dp
    var endPadding = 48.dp
    var bottomPadding = 48.dp
    var height = 40.dp
    var startP = 8.dp
    var imgSize = 50.dp
    var padding = 24.dp
    var topPadding2 = 64.dp
    var startPadding2 = 36.dp


    Log.d("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        topPadding = 40.dp
        startPadding = 20.dp
        endPadding = 20.dp
        bottomPadding = 16.dp
        fillMaxWidth = 0.63f
        fillMaxHeight = 0.45f
        maxWidth = 0.2f
        startPadding = 16.dp
        mainHeaderFont = 25.sp
        semiHeaderFont = 16.sp
        textFont1 = 15.sp
        textFont2 = 12.sp
        topPadding = 20.dp
        width = 180.dp
        height = 40.dp
        imgSize = 60.dp
        startP = 4.dp
        maxHeight = 0.18f
        padding = 12.dp
        topPadding2 = 8.dp
        startPadding2 = 16.dp

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
        textFont2 = 20.sp
        topPadding = 24.dp
        width = 248.dp
        height = 50.dp
        imgSize =200.dp
        startP = 4.dp
        maxHeight = 0.3f
        padding = 24.dp
        topPadding2 = 16.dp
        startPadding2 = 36.dp




        Log.d("Desktop: ", wd.toString())
    }

    var mark by remember { mutableStateOf("") }


//    val isConnected by rememberUpdatedState(newValue = isNetworkAvailable(context))
    Dialog(
        onDismissRequest = {
            onDismiss()
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
                    Image(painter = painterResource(id = R.drawable.ic_notest),
                        contentDescription = "wifiOff",
//                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(imgSize)
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
                        .padding(
                            top = topPadding,
                            start = startPadding,
                            end = endPadding,
                            bottom = bottomPadding
                        )
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "REJECT REASON",
                            style = TextStyle(
                                fontSize = mainHeaderFont,
                                color = lightBlack,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontFamily = nkbold
                            )
                        )


                        Row ( modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween){

                            Text(modifier = Modifier.padding(top = padding),
                                text = "Please Select The Reason",
                                style = TextStyle(
                                    fontSize = semiHeaderFont,
                                    color = lightBlack,
                                    textAlign = TextAlign.Center,
                                    fontFamily = nkmedium
                                )
                            )

                            var partId by remember { mutableStateOf("") }
                            PartId(
                                text = partId,
                                label = "Part ID",
                                onTextChange = { partId = it
                                    mainViewModel.partID = it},
                                color = pureBlack,
                                maxLength = 15,
                                shape = RoundedCornerShape(8.dp)
                            )
                        }

                    }

                    Box(modifier = Modifier
                        .padding(start = startP)
                        .border(width = 3.dp, color = lightGrey, shape = RoundedCornerShape(8.dp))){
                        Row(modifier = Modifier
                            .padding(padding)){
                            ReasonDropdown()

                            Column(modifier = Modifier
                                .padding(start = startPadding2)
                                .width(2.dp)) {
                                Divider(
                                    Modifier
                                        .fillMaxHeight(maxHeight)
                                        .padding(
                                            top = 2.dp,
                                        ),
                                    color = lightGrey
                                )
                            }

                            AddMark(
                                text = mark,
                                label = "Add Remark(Optional)",
                                onTextChange = { mark = it
                                               mainViewModel.mark = it},
                                color = pureBlack,
                                maxLength = 20,
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text) ,
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                    }

                    Column(modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End){
                        Surface(
                            modifier = Modifier
                                .padding(top = topPadding2)
                                .size(width = width, height = height)
                                .clickable {

                                    val passFail =
                                        myComponents.mainViewModel.pass.intValue + myComponents.mainViewModel.fail.intValue
                                    if (passFail >= myComponents.mainViewModel.totalAssigned.intValue) {
                                        myComponents.mUiViewModel.showThanksDialog()
                                        return@clickable
//                                        return@Button
                                    }

                                    if (myComponents.mainViewModel.isShiftOver(myComponents.mainViewModel.endShiftTime)) {
                                        myComponents.mUiViewModel.showThanksDialog()
//                                        return@Button
                                        return@clickable

                                    }
                                    if (mainViewModel.mSelectedReason.isNullOrEmpty()) {
                                        mUiViewModel.setDialogDetails(
                                            "Please Select Reason",
                                            "Select reason, After selecting it click SUBMIT button",
                                            " ",
                                            R.drawable.ic_notest
                                        )
                                        mUiViewModel.showMessageDialog()
                                    } else {

                                        var a = mark
                                        mainViewModel.submitFailedPartInfo()
//                                        mainViewModel.addWorkV2(0,a)
                                        mark = ""
                                    }
                                },
                            color = darkBlue,
                            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                            border = BorderStroke(width = 1.dp, color = darkBlue)
                        ) {
                            Text(
                                text = AnnotatedString("Submit"),
                                style = TextStyle(
                                    color = pureWhite,
                                    fontSize = textFont2,
                                    fontFamily = poppinsregular,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(9.dp)
                                    .align(Alignment.CenterHorizontally),

                            )
                        }
                    }

                }
            }
        }
    }
}

