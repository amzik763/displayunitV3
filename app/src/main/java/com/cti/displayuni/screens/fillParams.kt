package com.cti.displayuni.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cti.displayuni.R
import com.cti.displayuni.components.ActualLazyList
import com.cti.displayuni.components.ParametersLazyList
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.extraLightGrey
import com.cti.displayuni.ui.theme.green
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.ui.theme.red
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

var fontSmall = 11.sp
var fontMedium = 12.sp
var fontLarge = 22.sp
var paddingSmall = 4.dp
var paddingMedium = 8.dp
var paddingLarge = 12.dp
var heightSmall = 40.dp
var heightMedium = 60.dp
var heightLarge = 80.dp
var heightinFSmall = 0.06f
var heightinFMedium = 0.073f
var heightinFLarge = 80.dp

@Composable
fun ActualParams() {
    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
        Text(
            text = "Actual Parameters",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontLarge
            )
        )

        Spacer(modifier = Modifier.height(36.dp))
        ActualLazyList(
            myComponents.mainViewModel.dataListActual
        )
    }
}

@Composable
fun SettingParams() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Process Setting Parameters",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontLarge
            )
        )

        Spacer(modifier = Modifier.height(36.dp))
        ParametersLazyList(myComponents.mainViewModel.dataListSetting)
    }
}

@Composable
fun FPACircles(fpa: String?) {
    val wd = mParameters.mWidthinPx

    var width = 12.dp
    var size = 30.dp
    showLogs("dwinsize: ", wd.toString())
    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        width = 6.dp
        size = 16.dp
        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        size = 30.dp
        width = 12.dp
        showLogs("Desktop: ", wd.toString())
    }

    Image(painter = painterResource(id = R.drawable.circle),
        contentDescription ="Circle1",
        modifier = Modifier
            .size(size)
        .clickable {
            myComponents.mUiViewModel.showFpaDetails.value = true
        },

        colorFilter = if (fpa.isNullOrEmpty()) {
            showLogs("FPAFPAFPA: ","null")
            ColorFilter.tint(lightGrey)
        } else {
            showLogs("FPAFPAFPA: ","not null")

            ColorFilter.tint(green)
        }
    )
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun FPADetails(){

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont2 = 24.sp
    showLogs("dwinsize: ", wd.toString())
    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        textFont2 = 14.sp
        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont2 = 24.sp
        showLogs("Desktop: ", wd.toString())
    }


    Row{
        Text(
            text = "FPA Details",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = textFont2,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.width(4.dp))

        FPACircles(myComponents.mainViewModel.fpa1.value)
        FPACircles(myComponents.mainViewModel.fpa2.value)
        FPACircles(myComponents.mainViewModel.fpa3.value)
        FPACircles(myComponents.mainViewModel.fpa4.value)
    }
}

@Composable
fun ReadingUI(){

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont2 = 24.sp
    showLogs("dwinsize: ", wd.toString())
    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        textFont2 = 14.sp
        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont2 = 24.sp
        showLogs("Desktop: ", wd.toString())
    }

    Row{
        Text(
            text = "Readings",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = textFont2,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.width(4.dp))

        ReadingCircles(myComponents.mainViewModel.isCompleted1.get(0))
        ReadingCircles(myComponents.mainViewModel.isCompleted1.get(1))
        ReadingCircles(myComponents.mainViewModel.isCompleted1.get(2))
        ReadingCircles(myComponents.mainViewModel.isCompleted1.get(3))
        ReadingCircles(myComponents.mainViewModel.isCompleted1.get(4))
    }
}

@Composable
fun ReadingCircles(r: Boolean) {
    val wd = mParameters.mWidthinPx

    var width = 12.dp
    var size = 30.dp
    showLogs("dwinsize: ", wd.toString())
    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        width = 6.dp
        size = 16.dp
        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        size = 30.dp
        width = 12.dp
        showLogs("Desktop: ", wd.toString())
    }

    Image(painter = painterResource(id = R.drawable.circle),
        contentDescription ="Circle1",
        modifier = Modifier.size(size)
        .clickable {
            myComponents.mUiViewModel.showCustomPopup.value = true
        },
        colorFilter = if(!r){

            ColorFilter.tint(lightGrey)

        }
        else{

            ColorFilter.tint(green)

        }
    )
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun CheckingParts(checking:String, total:String, pass:String, fail:String){
    Row {
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = checking,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontMedium
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = total,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontMedium,
                color = darkBlue
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = pass,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontMedium,
                color = green
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = fail,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontMedium,
                color = red
            )
        )
    }
}


@Composable
fun MyImageButton(icon: Int, onClick: () -> Unit) {

    val wd = mParameters.mWidthinPx
    //myUI variables

    var size = 48.dp
    showLogs("dwinsize: ", wd.toString())
    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        size = 24.dp
        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        size = 48.dp
        showLogs("Desktop: ", wd.toString())
    }


    IconButton(
        onClick = onClick,
        modifier = Modifier.size(size),
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "ImageButton"
        )
    }
}
@Composable
fun OrangeText(
    name: String,
    value:String = "",
    style: TextStyle = TextStyle.Default
){

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont2 = 18.sp
    showLogs("dwinsize: ", wd.toString())
    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        textFont2 = 11.sp
        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont2 = 20.sp
        showLogs("Desktop: ", wd.toString())
    }

    Column(verticalArrangement = Arrangement.SpaceAround) {
        Row {
            Text( text = name,
                style = style.copy(
                    fontSize = textFont2
                )
            )
            Text( text = value,
                style = style.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = textFont2
                )
            )
        }
    }
}


@Composable
fun Header(){

    val currentDateTime = myComponents.mUiViewModel.currentDateTime.observeAsState("")

    val ct = LocalContext.current

    val wd = mParameters.mWidthinPx
    //myUI variables
    var textFont1 = 18.sp
    var textFont2 = 18.sp
    var width = 180.dp
    var topPadding = 4.dp
    var padding = 24.dp
    var endPadding = 48.dp
    var bottomPadding = 48.dp
    var height = 40.dp
    var imgSize = 50.dp
    var toppadding2 = 16.dp
    var startpadding2 = 52.dp
    var endpadding2 = 52.dp
    var textFont3 = 20.sp
    var interfaceW = 180.dp
    var interfaceH = 50.dp
    var btnpadding = 9.dp
    var sWidth = 16.dp
    var sWidth2 = 48.dp
    var rowHeight = 0.07f
    var rowHeight2 = 0.073f
    var padding1 = 16.dp
    var hPadding = 30.dp
    var fpa = "First Part Approval"

    showLogs("dwinsize: ", wd.toString())

    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        topPadding = 2.dp
        endPadding = 6.dp
        bottomPadding = 12.dp
        padding = 16.dp
        textFont1 = 15.sp
        textFont2 = 11.sp
        textFont3 = 12.sp
        topPadding = 20.dp
        width = 120.dp
        height = 30.dp
        imgSize = 26.dp
        toppadding2 = 10.dp
        startpadding2 = 40.dp
        endpadding2 = 30.dp
        interfaceW = 140.dp
        interfaceH = 45.dp
        btnpadding = 6.dp
        sWidth = 14.dp
        rowHeight = 0.05f
        rowHeight2 = 0.063f
        padding1 = 4.dp
        sWidth2 = 16.dp
        hPadding = 16.dp
        fpa = "FPA"

        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        topPadding = 4.dp
        endPadding = 48.dp
        bottomPadding = 24.dp
        padding = 24.dp
        textFont1 = 30.sp
        textFont2 = 20.sp
        textFont3 = 22.sp
        toppadding2 = 14.dp
        startpadding2 = 64.dp
        endpadding2 = 52.dp
        width = 240.dp
        height = 52.dp
        imgSize = 50.dp
        toppadding2 = 16.dp
        interfaceW = 180.dp
        interfaceH = 50.dp
        btnpadding = 9.dp
        sWidth = 16.dp
        rowHeight = 0.07f
        rowHeight2 = 0.073f
        padding1 = 16.dp
        sWidth2 = 48.dp
        hPadding = 30.dp
        fpa = "First Part Approval"

        showLogs("Desktop: ", wd.toString())
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
//        .scale(0.75f)
    ) {

        TopHeader()

        //First Row4
        Row(modifier = Modifier
            .background(color = extraLightGrey)
            .fillMaxWidth()
            .fillMaxHeight(rowHeight)
//            .padding(paddingSmall)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //subRow
            Row(modifier = Modifier
                .fillMaxHeight()
                .padding(padding1),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
//                Column(
//                    modifier = Modifier
//                        .padding(padding1)
////                        .fillMaxHeight()
//                ) {
                    OrangeText(name = "Process Name: ", value = myComponents.mainViewModel.mProcessName)
                    /*Box(
                        modifier = Modifier
                            .padding(top = topPadding)
                            .width(65.dp)
                            .height(4.dp)
                            .background(color = lightOrange)
                    )*/
//                }

                Spacer(modifier = Modifier.width(sWidth))
                /*Column(
                    modifier = Modifier
                        .padding(padding1)
//                        .fillMaxHeight()
                ) {*/
                    OrangeText(name = "Part Name: ", value = myComponents.mainViewModel.mPartName)
                   /* Box(
                        modifier = Modifier
                            .padding(top = topPadding)
                            .width(65.dp)
                            .height(4.dp)
                            .background(color = lightOrange)
                    )*/
//                }

                Spacer(modifier = Modifier.width(sWidth))

               /* Column (
                    modifier = Modifier
                        .padding(padding1)
//                        .fillMaxHeight()
                ){*/

                    OrangeText(name = "Device Id: ", value = myComponents.mainViewModel.deviceId)

                   /* Box(
                        modifier = Modifier
                            .padding(top = topPadding)
                            .width(65.dp)
                            .height(4.dp)
                            .background(color = lightOrange)
                    )*/
//                }

                Spacer(modifier = Modifier.width(sWidth2))

                OrangeText(name = "${myComponents.mainViewModel.startShiftTime} to ${myComponents.mainViewModel.endShiftTime}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = orange
                    ))

                Spacer(modifier = Modifier.width(sWidth2))

                OrangeText(name = currentDateTime.value,
                    style = TextStyle(
                        color = pureBlack
                    )
                )
            }

            Spacer(Modifier.width(sWidth))

            //secondSubRow
            Row {
                Button(
                    onClick = {myComponents.mainViewModel.showZoomableImage = !myComponents.mainViewModel.showZoomableImage},
                    shape = RoundedCornerShape(9.dp),
                    border = BorderStroke(3.dp, darkBlue),
                    colors = ButtonDefaults.buttonColors(contentColor = pureWhite, containerColor =  darkBlue),
                    contentPadding = PaddingValues(0.dp),
                    ) {
                    Text(
                        text = fpa,
                        fontSize = fontMedium,
//                        modifier = Modifier.padding(horizontal = hPadding)
                    )
                }

                Spacer(Modifier.width(sWidth))

                MyImageButton(R.drawable.ic_logout) {
                    val a = ct as? Activity
                    a?.finishAffinity()
                    showLogs("GOING:","Just Going there")

                    myComponents.navController.popBackStack()
                    myComponents.navController.navigate("Login")
                }

                Spacer(Modifier.width(sWidth))

                MyImageButton(icon = R.drawable.fullscreen) {
                    myComponents.mUiViewModel.showFullImage.value = true
                }

                Spacer(Modifier.width(sWidth))

            }
        }
        //Second Row
        Row(modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .fillMaxHeight(rowHeight2)
            .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CheckingParts(checking = "Checking: ${myComponents.mainViewModel.pass.intValue + myComponents.mainViewModel.fail.intValue}", total = "Total: ${myComponents.mainViewModel.totalAssigned.intValue}", pass = "Pass: ${myComponents.mainViewModel.pass.intValue}", fail = "Fail: ${myComponents.mainViewModel.fail.intValue}")

            FPADetails()

            showLogs("DATA LIST CHART", myComponents.mainViewModel.dataListChart.value?.size.toString())
            if (myComponents.mainViewModel.dataListChart.value?.size  != 0){
                ReadingUI()
            }

            //Pass fail buttons
            Row {
                Button(
                    onClick = {
                        showLogs("PASS", myComponents.mainViewModel.pass.intValue.toString())
                        showLogs("FAIL", myComponents.mainViewModel.fail.intValue.toString())
                        showLogs("STATION VALUE", myComponents.mainViewModel.getStationValue())

                        val passFail = myComponents.mainViewModel.pass.intValue + myComponents.mainViewModel.fail.intValue
                        if(passFail >= myComponents.mainViewModel.totalAssigned.intValue){

                            myComponents.mUiViewModel.showThanksDialog()
                            return@Button
                        }

                        if(myComponents.mainViewModel.isShiftOver(myComponents.mainViewModel.endShiftTime)){
                            myComponents.mUiViewModel.showThanksDialog()
                            return@Button
                        }

                        val actualParamsFilled = myComponents.mainViewModel.areActualParamsFilled(myComponents.mainViewModel.dataListActual)
                        showLogs("Actual Param", actualParamsFilled.toString())

                        val settingParamsFilled = myComponents.mainViewModel.areSettingParamsFilled(myComponents.mainViewModel.dataListSetting)
                        showLogs("Setting Param", settingParamsFilled.toString())

                        if (passFail < 2){
                            myComponents.mainViewModel.isFPATime = true
                            if(myComponents.mainViewModel.showZoomableImage){
                                myComponents.mUiViewModel.setDialogDetails("PENDING FPA", "", "Click on FPA button and then fill all parameter values", R.drawable.ic_notest)
                                myComponents.mUiViewModel.showMessageDialog()
                                showLogs("PASS FAIL", "Pass Fail sum is less then 2")
                                return@Button
                            }
                            else if(!actualParamsFilled || !settingParamsFilled){
                                myComponents.mUiViewModel.setDialogDetails("Fill Values - FPA", "", "Please fill all the parameter values", R.drawable.ic_notest )
                                myComponents.mUiViewModel.showMessageDialog()
                                return@Button
                            }
                        }
                        if (myComponents.mainViewModel.FPACounter==5){

                        }
                        else if(myComponents.mainViewModel.isCurrentTimeExceedsMidTime(myComponents.mainViewModel.startShiftTime,myComponents.mainViewModel.endShiftTime)){
                            if(myComponents.mainViewModel.fpa3.value.isNullOrEmpty() || myComponents.mainViewModel.fpa4.value.isNullOrEmpty())
                            {   myComponents.mainViewModel.isFPATime = true

                                if(myComponents.mainViewModel.showZoomableImage){
                                    myComponents.mUiViewModel.setDialogDetails("PENDING FPA", "", "Click on FPA button and then fill all parameter values", R.drawable.ic_notest)
                                    myComponents.mUiViewModel.showMessageDialog()
                                    showLogs("PASS FAIL 2", "FILL FPA")
                                    myComponents.mainViewModel.overrideDontAddData = false
                                    return@Button
                                }
                                else if(!actualParamsFilled || !settingParamsFilled){
                                    myComponents.mUiViewModel.setDialogDetails("Fill Values - FPA", "", "Please fill all the parameter values", R.drawable.ic_notest )
                                    myComponents.mUiViewModel.showMessageDialog()
                                    showLogs("PASS FAIL 222", "FILL FPA")

                                    return@Button
                                }
                            }else{
                                myComponents.mainViewModel.isFPATime = false
                            }
                        }

                        showLogs("PASS FAIL", passFail.toString())

                        if (myComponents.mainViewModel.itemsInRange()) {
                            //API CALL
                            GlobalScope.launch {

                                if(myComponents.mainViewModel.isFPATime){
//                                    myComponents.mainViewModel.submitPartInfoWithParams(1)
                                      myComponents.mainViewModel.checkFPA(myComponents.mainViewModel.precedency_no.value, myComponents.mainViewModel.mPartName ,myComponents.mainViewModel.temp_task_id.value)
                                }else{
                                    myComponents.mainViewModel.submitPartInfo(1)
                                }
                            }
//                            return@Button
                        } else {
                            //showdialogbox that process is not eligible for pass
                        }

                    },
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(29.dp),
                    border = BorderStroke(3.dp, green),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = pureWhite,
                        containerColor = green
                    ),

                    ) {
                    Text(
                        text = "PASS",
                        fontSize = fontMedium,
                        modifier = Modifier
//                            .padding(horizontal = 30.dp)

                    )
                }
                Spacer(modifier = Modifier.width(sWidth))

                Button(
                    onClick = {
                        showLogs("PASS", myComponents.mainViewModel.pass.intValue.toString())
                        showLogs("FAIL", myComponents.mainViewModel.fail.intValue.toString())
                        showLogs("STATION VALUE", myComponents.mainViewModel.getStationValue())

                        val passFail = myComponents.mainViewModel.pass.intValue + myComponents.mainViewModel.fail.intValue

                        val actualParamsFilled = myComponents.mainViewModel.areActualParamsFilled(myComponents.mainViewModel.dataListActual)
                        showLogs("Actual Param", actualParamsFilled.toString())

                        val settingParamsFilled = myComponents.mainViewModel.areSettingParamsFilled(myComponents.mainViewModel.dataListSetting)
                        showLogs("Setting Param", settingParamsFilled.toString())

                        if(passFail < 2){
                            myComponents.mainViewModel.isFPATime = true
                            showLogs("FPA FAILED: ","FPA SHOULD BE PASS TO PROCEED")
                            //show DIALOG BOX
//                            myComponents.mUiViewModel.setDialogDetails("FPA FAILED", "FPA SHOULD BE PASS TO PROCEED", "Ask Floor-In-Charge for necessary help", R.drawable.ic_notest )
//                            myComponents.mUiViewModel.showMessageDialog()

                            myComponents.mUiViewModel.showFailedDialog()

                        }else if(myComponents.mainViewModel.isCurrentTimeExceedsMidTime(myComponents.mainViewModel.startShiftTime,myComponents.mainViewModel.endShiftTime)){
                            showLogs("FAILED: ","time exceed")

                            if(myComponents.mainViewModel.FPACounter ==5){
                                showLogs("FAILED: ","counter 5")

                                myComponents.mainViewModel.isFPATime = false
                                //show loading dialog
                                if(myComponents.mainViewModel.isReasonRetrieved) {
                                    showLogs("FAILED: ","reason retrieved")

                                    myComponents.mUiViewModel.showRejectReasonDialog()
                                }
                                else{
                                    showLogs("FAILED: ","getting reason")
                                    myComponents.mainViewModel.getReasonData()

                                }
                            }
                            else if(myComponents.mainViewModel.fpa3.value.isNullOrEmpty() || myComponents.mainViewModel.fpa4.value.isNullOrEmpty())
                            {   myComponents.mainViewModel.isFPATime = true

                                myComponents.mainViewModel.isFPATime = true
                                showLogs("FPA FAILED: ","FPA SHOULD BE PASS TO PROCEED")
                                //show DIALOG BOX WITH BUTTON
//                                myComponents.mUiViewModel.setDialogDetails("FPA FAILED", "FPA SHOULD BE PASS TO PROCEED", "Ask Floor-In-Charge for necessary help", R.drawable.ic_notest )
//                                myComponents.mUiViewModel.showMessageDialog()

                                myComponents.mUiViewModel.showFailedDialog()

                            }
                        }
                        else {
                            myComponents.mainViewModel.isFPATime = false
                            //show loading dialog
                            if(myComponents.mainViewModel.isReasonRetrieved)

                                myComponents.mUiViewModel.showRejectReasonDialog()
                            else
                                myComponents.mainViewModel.getReasonData()
                        }
                    },
                    shape = RoundedCornerShape(29.dp),
                    border = BorderStroke(3.dp, red),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = pureWhite,
                        containerColor = red
                    ),
                ) {
                    Text(
                        text = "FAIL",
                        fontSize = fontMedium,
//                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(sWidth))
            }
        }
        if (myComponents.mainViewModel.showZoomableImage) {
            ZoomableImage()
        }
        else {
            //Third Element - Column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = paddingMedium, bottom = paddingMedium)
            ) {
                Text(
                    text = "First Part Approval",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontLarge
                    ),
                    modifier = Modifier.fillMaxWidth(1f)
                )
                Row(modifier = Modifier.padding(24.dp)) {
                    //1.actual param list
                    ActualParams()
                    Spacer(modifier = Modifier.width(36.dp))
                    //2.settings param list
                    SettingParams()
                }
            }
        }
    }
}

@Composable
fun ZoomableImage(){
// Define mutable state variables to keep track of the scale and offset.
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }



    var currentIndex by remember { mutableStateOf(0) }

    val painter = rememberAsyncImagePainter(myComponents.mainViewModel.imageUrl[currentIndex])

    fun previousImage() {
        currentIndex = (currentIndex - 1 + myComponents.mainViewModel.imageUrl.size) % myComponents.mainViewModel.imageUrl.size
    }

    fun nextImage() {
        currentIndex = (currentIndex + 1) % myComponents.mainViewModel.imageUrl.size
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Row (modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically){

            Image(painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "leftArrow",
                modifier = Modifier
                    .size(80.dp)
                    .clickable { previousImage() })

            Spacer(modifier = Modifier.width(36.dp))

            Image(
                painter = painter, // Replace 'imagePainter' with your image
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight()
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            // Update the scale based on zoom gestures.
                            scale *= zoom

                            // Limit the zoom levels within a certain range (optional).
                            scale = scale.coerceIn(0.5f, 3f)

                            // Update the offset to implement panning when zoomed.
                            offset = if (scale == 1f) Offset(0f, 0f) else offset + pan
                        }
                    }
                    .graphicsLayer(
                        scaleX = scale, scaleY = scale,
                        translationX = offset.x, translationY = offset.y
                    )
            )

            Spacer(modifier = Modifier.width(36.dp))

            Image(painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "RightArrow",
                modifier = Modifier
                    .size(80.dp)
                    .clickable { nextImage() })
        }
    }
}

@Composable
fun FillParam(){

    val conf = LocalConfiguration.current
    val dnsty = conf.densityDpi
    mParameters.dnsty = dnsty

    val shouldShowCustomPopup = myComponents.mUiViewModel.showCustomPopup.observeAsState()

    val showFpaDetails = myComponents.mUiViewModel.showFpaDetails.observeAsState()

    val showFullImage = myComponents.mUiViewModel.showFullImage.observeAsState()

    if (mParameters.dnsty == 320) {

        fontSmall = 10.sp
        fontMedium = 12.sp
        fontLarge = 17.sp
        paddingSmall = 2.dp
        paddingMedium = 4.dp
        paddingLarge = 8.dp
        heightSmall = 34.dp
        heightMedium = 50.dp
        heightLarge = 70.dp
        heightinFSmall = 0.06f
        heightinFMedium = 0.073f
        heightinFLarge = 80.dp
        showLogs("DENSITY","320")

    } else if (mParameters.dnsty == 160) {

        fontSmall = 12.sp
        fontMedium = 18.sp
        fontLarge = 22.sp
        paddingSmall = 4.dp
        paddingMedium = 8.dp
        paddingLarge = 12.dp
        heightSmall = 40.dp
        heightMedium = 60.dp
        heightLarge = 80.dp
        heightinFSmall = 0.06f
        heightinFMedium = 0.073f
        heightinFLarge = 80.dp
        showLogs("DENSITY","160")
    }
    Header()
    showLogs("Early READINGS UI",myComponents.mUiViewModel.showCustomPopup.value.toString())

    if(shouldShowCustomPopup.value == true){
        showLogs("READINGS UI",myComponents.mUiViewModel.showCustomPopup.value.toString())
        Log.d("XDATA","Android::"+1+1)
        Log.d("XDATA","Android::"+(1+1))
//        Log.d("XDATA",1+1+"Android::")
//        Log.d("XDATA",(1+1)+"Android::")
        CustomPopupContent {
            myComponents.mUiViewModel.showCustomPopup.value = false
        }
    }

    if (showFpaDetails.value == true){

        showLogs("FPA Details",myComponents.mUiViewModel.showFpaDetails.value.toString())

        FPA_Details {
            myComponents.mUiViewModel.showFpaDetails.value = false
        }
    }

    if (showFullImage.value == true){

        showLogs("Full Screen Image",myComponents.mUiViewModel.showFullImage.value.toString())

        DisplayModeImage {
            myComponents.mUiViewModel.showFullImage.value = false
        }
    }
}

@Composable
fun TopHeader(){

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont2 = 18.sp
    var interfaceW = 180.dp
    var interfaceH = 50.dp
    var sWidth = 16.dp
    var endpadding = 36.dp

    showLogs("dwinsize: ", wd.toString())


    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {


        textFont2 = 11.sp
        interfaceW = 140.dp
        interfaceH = 38.dp
        sWidth = 12.dp
        endpadding = 12.dp

        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont2 = 20.sp
        interfaceW = 180.dp
        interfaceH = 50.dp
        sWidth = 16.dp
        endpadding = 36.dp

        showLogs("Desktop: ", wd.toString())
    }

    Box(
        modifier = Modifier
            .background(color = lightGrey)
            .padding(
                end = endpadding,
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(painter = painterResource(id = R.drawable.interfaceblue),
                contentDescription = "Interface Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(interfaceW, interfaceH),
            )

            Row {
                Text(
                    text = "Name: ",
                    style = TextStyle(
                        fontSize = textFont2,
                        color = pureBlack,
                        fontFamily = mFont.poppinsregular
                    )
                )
                Text(
                    text = myComponents.mainViewModel.name,
                    style = TextStyle(
                        fontSize = textFont2,
                        color = pureBlack,
                        fontWeight = FontWeight.Bold,
                        fontFamily = mFont.poppinsbold
                    )
                )

            }

            Row {
                Text(
                    text = "Skill: ",
                    style = TextStyle(
                        fontSize = textFont2,
                        color = pureBlack,
                        fontFamily = mFont.poppinsregular
                    )
                )
                Text(
                    text = myComponents.mainViewModel.skill,
                    style = TextStyle(
                        fontSize = textFont2,
                        color = pureBlack,
                        fontWeight = FontWeight.Bold,
                        fontFamily = mFont.poppinsbold
                    )
                )

                Spacer(modifier = Modifier.width(sWidth))
                Skills()
            }

            Row {
                Text(
                    text = "Device Id: ",
                    style = TextStyle(
                        fontSize = textFont2,
                        color = pureBlack,
                        fontFamily = mFont.poppinsregular
                    )
                )
                Text(
                    text = myComponents.mainViewModel.deviceId,
                    style = TextStyle(
                        fontSize = textFont2,
                        color = pureBlack,
                        fontWeight = FontWeight.Bold,
                        fontFamily = mFont.poppinsbold
                    )
                )
            }
            Row {
                Text(
                    text = "Employee Id: ",
                    style = TextStyle(
                        fontSize = textFont2,
                        color = pureBlack,
                        fontFamily = mFont.poppinsregular
                    )
                )
                Text(
                    text = myComponents.mainViewModel.employeeId,
                    style = TextStyle(
                        fontSize = textFont2,
                        color = pureBlack,
                        fontWeight = FontWeight.Bold,
                        fontFamily = mFont.poppinsbold
                    )
                )
            }
        }
    }
}

@Preview(name = "Tablet", device = "spec:width=1920px, height=1080px, dpi=160, isRound=false, orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun PreviewUi(){
    FillParam()
}

