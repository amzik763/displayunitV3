package com.cti.displayuni.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.draw.scale
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
    Surface(
        modifier = Modifier
            .padding(start = 38.dp, top = 16.dp)
            .size(width = 150.dp, height = 48.dp),
//        .padding(start = 38.dp, top = 16.dp) uncomment later
//    .size(width = 150.dp, height = 48.dp), uncomment later
        color = green,
        shape = RoundedCornerShape(corner = CornerSize(36.dp)),
        border = BorderStroke(width = 1.dp, color = green)
    ) {
        ClickableText(
            text = AnnotatedString(text),
            style = TextStyle(
                color = pureWhite,
                fontSize = 16.sp,
                fontFamily = mFont.poppinsregular,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(9.dp),
            onClick = {
                onClick()
            }
        )
    }
}


//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun CustomPopupContent(
    onCloseClicked: () -> Unit,
) {

    val mState = myComponents.mainViewModel.mState.observeAsState(false)

    val dataListChart = myComponents.mainViewModel.dataListChart.observeAsState(emptyList())

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
//        .scale(0.75f)
        .padding(top = 20.dp, bottom = 16.dp),
//        .padding(top = 50.dp, bottom = 36.dp), uncomment later
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        if(!mState.value) {
            showLogs("mSTATE: ",mState.value.toString())
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
                .size(width = 150.dp, height = 46.dp),
            color = darkBlue,
            shape = RoundedCornerShape(corner = CornerSize(36.dp)),
            border = BorderStroke(width = 1.dp, color = green)
        ) {
            ClickableText(
                text = AnnotatedString("Close"),
                style = TextStyle(
                    color = pureWhite,
                    fontSize = 16.sp,
                    fontFamily = mFont.poppinsregular,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(9.dp),
                onClick = {
                    onCloseClicked()
                }
            )
        }
    }
}

@Composable
fun ReadingRow1st(dataListChart: State<List<chart_parameter>>) {

    Column {
        var PN = ""
        try{
            PN = myComponents.mainViewModel.dataListChart.value?.get(0)?.parameter_name ?: "none"
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
                        mutableStateOf(dataListChart.value.get(0).values.get(0))
                    }
                    ReadingValue(
                        text =  reading1,
                        label = "Enter Value",
                        onTextChange = {it ->
                            if(!myComponents.mainViewModel.isCompleted1[0]){
                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                            //runReadingAPI
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            if(!myComponents.mainViewModel.isCompleted1[1]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            if(!myComponents.mainViewModel.isCompleted1[2]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted1[3]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            if(!myComponents.mainViewModel.isCompleted1[4]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
        try{
            PN = myComponents.mainViewModel.dataListChart.value?.get(1)?.parameter_name ?: "none"

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
                        label = "Enter Value",
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted2[0]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = {
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted2[1]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted2[2]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted2[3]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted2[4]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
        try{
            PN = myComponents.mainViewModel.dataListChart.value?.get(2)?.parameter_name?:"none"
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted3[0]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted3[1]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted3[2]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted3[3]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                        label = "Enter Value",
                        onTextChange = { it ->
                            // Filter out commas from the input text
                            if(!myComponents.mainViewModel.isCompleted3[4]){

                            val filteredValue = it.filter { it.isLetterOrDigit() }
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
                            myComponents.mainViewModel.runReadingAPI(2, reading53, 4)

                        })
                }
        }
    }
}