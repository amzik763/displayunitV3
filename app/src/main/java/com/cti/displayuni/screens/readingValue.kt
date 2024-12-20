package com.cti.displayuni.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.components.ReadingValue
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.green
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.chart_parameter
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.readingStatusEnum
import com.cti.displayuni.utility.showLogs

@Composable
fun ParamName(heading: String) {
    Column(
//        modifier = Modifier.padding(start = 36.dp, bottom = 10.dp) uncomment later
                modifier = Modifier.padding(start = 6.dp, bottom = 2.dp)
    ) {
        Text(
            text = heading,
            style = TextStyle(
//                fontSize = 36.sp, uncomment later
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = mFont.nkbold
            )
        )
    }
}

@Composable
fun SubmitButton(text: String, onClick: () -> Unit) {
    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont2 = 16.sp
    var toppadding = 16.dp
    var startpadding = 38.dp
    var btnWidth = 140.dp
    var btnHeight = 40.dp
    var btnPadding = 9.dp

    showLogs("dwinsize: ", wd.toString())


    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        textFont2 = 11.sp
        toppadding = 10.dp
        startpadding = 20.dp
        btnWidth = 100.dp
        btnHeight = 28.dp
        btnPadding = 4.dp


        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont2 = 16.sp
        toppadding = 16.dp
        startpadding = 38.dp
        btnWidth = 140.dp
        btnHeight = 40.dp
        btnPadding = 9.dp

        showLogs("Desktop: ", wd.toString())
    }

    Surface(
        modifier = Modifier
            .padding(start = startpadding, top = toppadding)
            .size(width = btnWidth, height = btnHeight)
            .clickable {
                onClick()
               },
        color = green,
        shape = RoundedCornerShape(corner = CornerSize(36.dp)),
        border = BorderStroke(width = 1.dp, color = green)
    ) {
        Text(
            text = AnnotatedString(text),
            style = TextStyle(
                color = pureWhite,
                fontSize = textFont2,
                fontFamily = mFont.poppinsregular,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(btnPadding),

        )
    }
}


//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun CustomPopupContent(
    onCloseClicked: () -> Unit,
) {

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont2 = 16.sp
    var toppadding = 20.dp
    var bottompadding = 16.dp
    var btnWidth = 140.dp
    var btnHeight = 40.dp
    var btnPadding = 9.dp

    showLogs("dwinsize: ", wd.toString())


    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {


        textFont2 = 11.sp
        toppadding = 10.dp
        bottompadding = 6.dp
        btnWidth = 100.dp
        btnHeight = 28.dp
        btnPadding = 4.dp


        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont2 = 16.sp
        toppadding = 20.dp
        bottompadding = 16.dp
        btnWidth = 140.dp
        btnHeight = 40.dp
        btnPadding = 9.dp

        showLogs("Desktop: ", wd.toString())
    }


    val mState = myComponents.mainViewModel.mState.observeAsState(false)

    val dataListChart = myComponents.mainViewModel.dataListChart.observeAsState(emptyList())

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
//        .scale(0.75f)
        .padding(top = toppadding, bottom = bottompadding),
//        .padding(top = 50.dp, bottom = 36.dp), uncomment later
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        if(!mState.value) {
            showLogs("mSTATE: ",mState.value.toString())
            showLogs("NEW READING SIZE: ",myComponents.mainViewModel.dataListChart.value?.size.toString())
            if(myComponents.mainViewModel.dataListChart.value?.size == 1) {
                ReadingRow1st(dataListChart)
            }

            if(myComponents.mainViewModel.dataListChart.value?.size == 2) {
                ReadingRow1st(dataListChart)
                ReadingRow2nd(dataListChart)
            }

            if(myComponents.mainViewModel.dataListChart.value?.size == 3) {
                ReadingRow1st(dataListChart)
                ReadingRow2nd(dataListChart)
                ReadingRow3rd(dataListChart)
            }
        }

        Surface(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(width = btnWidth, height = btnHeight)
                .clickable {
                    onCloseClicked()

                },
            color = darkBlue,
            shape = RoundedCornerShape(corner = CornerSize(36.dp)),
            border = BorderStroke(width = 1.dp, color = green)
        ) {
            Text(
                text = AnnotatedString("Close"),
                style = TextStyle(
                    color = pureWhite,
                    fontSize = textFont2,
                    fontFamily = mFont.poppinsregular,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(btnPadding),

            )
        }
    }
}

@Composable
fun ReadingRow1st(dataListChart: State<List<chart_parameter>>) {

    Column {
        var PN = ""
        var min=""
        var max = ""
        try{
            min = myComponents.mainViewModel.dataListChart.value?.get(0)?.min ?: "none"
            max = myComponents.mainViewModel.dataListChart.value?.get(0)?.max ?: "none"
            PN = myComponents.mainViewModel.dataListChart.value?.get(0)?.parameter_name + " :" + myComponents.mainViewModel.dataListChart.value?.get(0)?.min + " - "+ myComponents.mainViewModel.dataListChart.value?.get(0)?.max
        }catch (_:Exception){

        }
        ParamName(heading = PN)

        Row (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            showLogs("READING STATUS DATA: ",myComponents.mainViewModel.readingStatusList[0].readingStatusE.toString() + " ")
            if(myComponents.mainViewModel.readingStatusList[0].readingStatusE != readingStatusEnum.notAvailable)
                Column {
                    var reading1 by remember {
                        mutableStateOf(dataListChart.value.get(0). values.get(0))
                    }
                    ReadingValue(
                        text =  reading1,
                        label = min + " - " + max,
                        onTextChange = {it ->
                            if(!myComponents.mainViewModel.isCompleted1[0]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                reading1 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(0)?.values?.set(0,filteredValue)}
                        },

                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),

                        shape = RoundedCornerShape(8.dp),
//                    enabled = if( myComponents.mainViewModel.r1.length==0)true else false
                    )
                    if(!myComponents.mainViewModel.isCompleted1[0])
                        SubmitButton(text = "Submit", onClick = {

                           if(myComponents.mainViewModel.readingInRange(min,max,reading1))
                            myComponents.mainViewModel.runReadingAPI(0,reading1,0)
                        })
                }
            if(myComponents.mainViewModel.readingStatusList[1].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading2 by remember {
                        mutableStateOf(dataListChart.value.get(0).values.get(1))
                    }
                    ReadingValue(
                        text = reading2,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            if(!myComponents.mainViewModel.isCompleted1[1]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                reading2 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(0)?.values?.set(1,filteredValue)}
                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )

                    if(!myComponents.mainViewModel.isCompleted1[1])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading2))
                             myComponents.mainViewModel.runReadingAPI(0, reading2, 1)

                        })

                }

            if(myComponents.mainViewModel.readingStatusList[2].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading3 by remember {
                        mutableStateOf(dataListChart.value.get(0).values.get(2))
                    }

                    ReadingValue(
                        text = reading3,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            if(!myComponents.mainViewModel.isCompleted1[2]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                reading3 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(0)?.values?.set(2,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )

                    if(!myComponents.mainViewModel.isCompleted1[2])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading3))
                            myComponents.mainViewModel.runReadingAPI(0, reading3, 2)

                        })

                }

            if(myComponents.mainViewModel.readingStatusList[3].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading4 by remember {
                        mutableStateOf(dataListChart.value.get(0).values.get(3))
                    }

                    ReadingValue(
                        text = reading4,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted1[3]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                // Update the state with the filtered value
                            reading4 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(0)?.values?.set(3,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )

                    if(!myComponents.mainViewModel.isCompleted1[3])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading4))
                            myComponents.mainViewModel.runReadingAPI(0, reading4, 3)

                        })

                }

            if(myComponents.mainViewModel.readingStatusList[4].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading5 by remember {
                        mutableStateOf(dataListChart.value.get(0).values.get(4))
                    }

                    ReadingValue(
                        text = reading5,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            if(!myComponents.mainViewModel.isCompleted1[4]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                reading5 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(0)?.values?.set(4,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )

                    if(!myComponents.mainViewModel.isCompleted1[4])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading5))
                             myComponents.mainViewModel.runReadingAPI(0, reading5, 4)

                        })

                }

        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun ReadingRow2nd(dataListChart: State<List<chart_parameter>>) {
    Column {
        var PN = ""
        var min=""
        var max = ""
        try{
            min = myComponents.mainViewModel.dataListChart.value?.get(0)?.min ?: "none"
            max = myComponents.mainViewModel.dataListChart.value?.get(0)?.max ?: "none"
            PN = myComponents.mainViewModel.dataListChart.value?.get(0)?.parameter_name + " :" + myComponents.mainViewModel.dataListChart.value?.get(0)?.min + " - "+ myComponents.mainViewModel.dataListChart.value?.get(0)?.max

        }catch (_:Exception){

        }
        ParamName(heading = PN)

        Row (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.SpaceAround
        ){

            if(myComponents.mainViewModel.readingStatusList[0].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading12 by remember {
                        mutableStateOf(dataListChart.value.get(1).values.get(0))
                    }

                    ReadingValue(
                        text = reading12,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted2[0]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                // Update the state with the filtered value
                            reading12 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(1)?.values?.set(0,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
//                    enabled = if( myComponents.mainViewModel.r1.length==0)true else false
                    )

                    if (!myComponents.mainViewModel.isCompleted2[0])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading12))
                            myComponents.mainViewModel.runReadingAPI(1, reading12, 0)

                        })

                }

            if(myComponents.mainViewModel.readingStatusList[1].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading22 by remember {
                        mutableStateOf(dataListChart.value.get(1).values.get(1))
                    }
                    ReadingValue(
                        text = reading22,
                        label = min + " - " + max,
                        onTextChange = {
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted2[1]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                // Update the state with the filtered value
                            reading22 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(1)?.values?.set(1,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )

                    if (!myComponents.mainViewModel.isCompleted2[1])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading22))
                            myComponents.mainViewModel.runReadingAPI(1, reading22, 1)

                        })

                }

            if (myComponents.mainViewModel.readingStatusList[2].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading32 by remember {
                        mutableStateOf(dataListChart.value.get(1).values.get(2))
                    }
                    ReadingValue(
                        text = reading32,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted2[2]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                // Update the state with the filtered value
                            reading32 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(1)?.values?.set(2,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )

                    if (!myComponents.mainViewModel.isCompleted2[2])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading32))
                            myComponents.mainViewModel.runReadingAPI(1, reading32, 2)

                        })

                }
            if (myComponents.mainViewModel.readingStatusList[3].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading42 by remember {
                        mutableStateOf(dataListChart.value.get(1).values.get(3))
                    }

                    ReadingValue(
                        text = reading42,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted2[3]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                // Update the state with the filtered value
                            reading42 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(1)?.values?.set(3,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )

                    if (!myComponents.mainViewModel.isCompleted2[3])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading42))
                            myComponents.mainViewModel.runReadingAPI(1, reading42, 3)
                        })

                }

            if (myComponents.mainViewModel.readingStatusList[4].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading52 by remember {
                        mutableStateOf(dataListChart.value.get(1).values.get(4))
                    }

                    ReadingValue(
                        text = reading52,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted2[4]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                // Update the state with the filtered value
                            reading52 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(1)?.values?.set(4,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )

                    if (!myComponents.mainViewModel.isCompleted2[4])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading52))
                            myComponents.mainViewModel.runReadingAPI(1, reading52, 4)
                        })

                }

        }
    }
}

@Composable
fun ReadingRow3rd(dataListChart: State<List<chart_parameter>>) {
    Column {
        var PN = ""
        var min=""
        var max = ""
        try{
            min = myComponents.mainViewModel.dataListChart.value?.get(0)?.min ?: "none"
            max = myComponents.mainViewModel.dataListChart.value?.get(0)?.max ?: "none"
            PN = myComponents.mainViewModel.dataListChart.value?.get(0)?.parameter_name + " :" + myComponents.mainViewModel.dataListChart.value?.get(0)?.min + " - "+ myComponents.mainViewModel.dataListChart.value?.get(0)?.max

        }catch (_:Exception){

        }
        ParamName(heading = PN)

        Row (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            if (myComponents.mainViewModel.readingStatusList[0].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading13 by remember {
                        mutableStateOf(dataListChart.value.get(2).values.get(0))
                    }

                    ReadingValue(
                        text = reading13,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted3[0]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                // Update the state with the filtered value
                            reading13 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(2)?.values?.set(0,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
//                    enabled = if( myComponents.mainViewModel.r1.length==0)true else false
                    )


                    if (!myComponents.mainViewModel.isCompleted3[0])
                        SubmitButton(text = "Submit", onClick = {
                           if(myComponents.mainViewModel.readingInRange(min,max,reading13))
                            myComponents.mainViewModel.runReadingAPI(2, reading13, 0)

                        })

                }

            if (myComponents.mainViewModel.readingStatusList[1].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading23 by remember {
                        mutableStateOf(dataListChart.value.get(2).values.get(1))
                    }

                    ReadingValue(
                        text = reading23,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted3[1]){

                            val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }
                            // Update the state with the filtered value
                            reading23 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(2)?.values?.set(1,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )

                    if (!myComponents.mainViewModel.isCompleted3[1])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading23))
                            myComponents.mainViewModel.runReadingAPI(2, reading23, 1)

                        })

                }

            if (myComponents.mainViewModel.readingStatusList[2].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading33 by remember {
                        mutableStateOf(dataListChart.value.get(2).values.get(2))
                    }

                    ReadingValue(
                        text = reading33,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted3[2]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                // Update the state with the filtered value
                            reading33 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(2)?.values?.set(2,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )

                    if (!myComponents.mainViewModel.isCompleted3[2])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading33))
                            myComponents.mainViewModel.runReadingAPI(2, reading33, 2)

                        })

                }

            if (myComponents.mainViewModel.readingStatusList[3].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading43 by remember {
                        mutableStateOf(dataListChart.value.get(2).values.get(3))
                    }

                     ReadingValue(
                        text = reading43,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted3[3]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                // Update the state with the filtered value
                            reading43 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(2)?.values?.set(3,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )
                    if (!myComponents.mainViewModel.isCompleted3[3])
                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading43))
                            myComponents.mainViewModel.runReadingAPI(2, reading43, 3)

                        })
                }

            if (myComponents.mainViewModel.readingStatusList[4].readingStatusE != readingStatusEnum.notAvailable)
                Column {

                    var reading53 by remember {
                        mutableStateOf(dataListChart.value.get(2).values.get(4))
                    }

                    ReadingValue(
                        text = reading53,
                        label = min + " - " + max,
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted3[4]){

                                val filteredValue = it.filter { it.isLetterOrDigit() || it == '.' }

                                // Update the state with the filtered value
                            reading53 = filteredValue
                            myComponents.mainViewModel.dataListChart.value?.get(2)?.values?.set(4,filteredValue)}

                        },
                        color = pureBlack,
                        maxLength = 5,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )
                    if (!myComponents.mainViewModel.isCompleted3[4])

                        SubmitButton(text = "Submit", onClick = {
                            if(myComponents.mainViewModel.readingInRange(min,max,reading53))
                            myComponents.mainViewModel.runReadingAPI(2, reading53, 4)

                        })
                }
        }
    }
}