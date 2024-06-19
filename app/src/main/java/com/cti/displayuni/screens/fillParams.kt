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

var fontSmall = 12.sp
var fontMedium = 18.sp
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
    Image(painter = painterResource(id = R.drawable.circle),
        contentDescription ="Circle1",
        modifier = Modifier.clickable {
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
    Spacer(modifier = Modifier.width(12.dp))
}

@Composable
fun FPADetails(){
    Row{
        Text(
            text = "FPA Details",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontLarge,
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
    Row{
        Text(
            text = "Readings",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontLarge,
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
    Image(painter = painterResource(id = R.drawable.circle),
        contentDescription ="Circle1",
        modifier = Modifier.clickable {
            myComponents.mUiViewModel.showCustomPopup.value = true
        },
        colorFilter = if(!r){

            ColorFilter.tint(lightGrey)

        }
        else{

            ColorFilter.tint(green)

        }
    )
    Spacer(modifier = Modifier.width(12.dp))
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
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
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
    value:String
){
    Column(verticalArrangement = Arrangement.SpaceAround) {
        Row {
            Text( text = name,
                style = TextStyle(
                    fontSize = fontMedium
                )
            )
            Text( text = value,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = fontMedium
                )
            )
        }
    }
}


@Composable
fun Header(){

    val currentDateTime = myComponents.mUiViewModel.currentDateTime.observeAsState("")

    val ct = LocalContext.current

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
//        .scale(0.75f)
    ) {

        //First Row
        Row(modifier = Modifier
            .background(color = extraLightGrey)
            .fillMaxWidth()
            .fillMaxHeight(0.07f)
//            .padding(paddingSmall)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //subRow
            Row(modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
//                        .fillMaxHeight()
                ) {
                    OrangeText(name = "Process Name: ", value = myComponents.mainViewModel.mProcessName)
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .width(65.dp)
                            .height(4.dp)
                            .background(color = lightOrange)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
//                        .fillMaxHeight()
                ) {
                    OrangeText(name = "Part Name: ", value = myComponents.mainViewModel.mPartName)
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .width(65.dp)
                            .height(4.dp)
                            .background(color = lightOrange)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {

                    OrangeText(name = "Device Id:", value = myComponents.mainViewModel.deviceId)

                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .width(65.dp)
                            .height(4.dp)
                            .background(color = lightOrange)
                    )
                }

                Spacer(modifier = Modifier.width(48.dp))
                //Shift Timings
                Text(
                    text = "${myComponents.mainViewModel.startShiftTime} to ${myComponents.mainViewModel.endShiftTime}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontMedium,
                        color = orange
                    )
                )

                Spacer(modifier = Modifier.width(38.dp))

                Text(
                    text = currentDateTime.value,
                    style = TextStyle(
                        fontSize = fontMedium,
                        color = pureBlack,
                        fontFamily = mFont.poppinsregular
                    )
                )
            }
            //secondSubRow
            Row {
                Button(
                    onClick = {myComponents.mainViewModel.showZoomableImage = !myComponents.mainViewModel.showZoomableImage},
                    shape = RoundedCornerShape(9.dp),
                    border = BorderStroke(3.dp, darkBlue),
                    colors = ButtonDefaults.buttonColors(contentColor = pureWhite, containerColor =  darkBlue),

                    ) {
                    Text(
                        text = "First Part Approval",
                        fontSize = fontMedium,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }

                Spacer(Modifier.width(30.dp))

                MyImageButton(R.drawable.ic_logout) {
                    val a = ct as? Activity
                    a?.finishAffinity()
                    showLogs("GOING:","Just Going there")

                    myComponents.navController.popBackStack()
                    myComponents.navController.navigate("Login")
                }

                Spacer(Modifier.width(16.dp))

                MyImageButton(icon = R.drawable.fullscreen) {
                    myComponents.mUiViewModel.showFullImage.value = true
                }

                Spacer(Modifier.width(16.dp))

            }
        }
        //Second Row
        Row(modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .fillMaxHeight(0.073f)
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
                            .padding(horizontal = 30.dp)

                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
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
                            myComponents.mUiViewModel.setDialogDetails("FPA FAILED", "FPA SHOULD BE PASS TO PROCEED", "Ask Floor-In-Charge for necessary help", R.drawable.ic_notest )
                            myComponents.mUiViewModel.showMessageDialog()
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
                                //show DIALOG BOX
                                myComponents.mUiViewModel.setDialogDetails("FPA FAILED", "FPA SHOULD BE PASS TO PROCEED", "Ask Floor-In-Charge for necessary help", R.drawable.ic_notest )
                                myComponents.mUiViewModel.showMessageDialog()

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
                    colors = ButtonDefaults.buttonColors(
                        contentColor = pureWhite,
                        containerColor = red
                    ),
                ) {
                    Text(
                        text = "FAIL",
                        fontSize = fontMedium,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
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

@Preview(name = "Tablet", device = "spec:width=1920px, height=1080px, dpi=160, isRound=false, orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun PreviewUi(){
    FillParam()
}

