package com.cti.displayuni.screens

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
import androidx.compose.runtime.getValue
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
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.readingStatusEnum
import com.cti.displayuni.utility.showLogs

@Composable
fun ParamName(heading: String) {
    Column(
        modifier = Modifier.padding(start = 36.dp, bottom = 10.dp)
    ) {
        Text(
            text = heading,
            style = TextStyle(
                fontSize = 36.sp,
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

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(top = 50.dp , bottom = 36.dp),
     horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
        ) {

        if (myComponents.mainViewModel.dataListChart.size == 1){
            ReadingRow1st()
        }

        if (myComponents.mainViewModel.dataListChart.size == 2){
            ReadingRow1st()

            ReadingRow2nd()
        }

        if (myComponents.mainViewModel.dataListChart.size == 3){
            ReadingRow1st()

            ReadingRow2nd()

            ReadingRow3rd()
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
fun ReadingRow1st(){
    Column {
        var PN = ""
            try{
                PN = myComponents.mainViewModel.dataListChart[0].parameter_name
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
                var reading1 by remember { mutableStateOf("0") }

                ReadingValue(
                    text = reading1,
                    label = "Enter Value",
                    onTextChange = {
                            // Filter out commas from the input text
                            val filteredValue = it.replace(",", "")
                            // Update the state with the filtered value
                            reading1 = filteredValue
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

                SubmitButton(text = "Submit", onClick = { })

            }

            if(myComponents.mainViewModel.readingStatusList[1].readingStatusE != readingStatusEnum.available)
            Column {

                var reading2 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading2,
                    label = "Enter Value",
                    onTextChange = {
                            // Filter out commas from the input text
                            val filteredValue = it.replace(",", "")
                            // Update the state with the filtered value
                            reading2 = filteredValue
                                   },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

            Column {

                var reading3 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading3,
                    label = "Enter Value",
                    onTextChange = {
                            // Filter out commas from the input text
                            val filteredValue = it.replace(",", "")
                            // Update the state with the filtered value
                            reading3 = filteredValue
                                   },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

            Column {

                var reading4 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading4,
                    label = "Enter Value",
                    onTextChange = {
                            // Filter out commas from the input text
                            val filteredValue = it.replace(",", "")
                            // Update the state with the filtered value
                            reading4 = filteredValue
                                   },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

            Column {

                var reading5 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading5,
                    label = "Enter Value",
                    onTextChange = {
                            // Filter out commas from the input text
                            val filteredValue = it.replace(",", "")
                            // Update the state with the filtered value
                            reading5 = filteredValue
                                   },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

        }
    }
}

@Composable
fun ReadingRow2nd(){
    Column {
        var PN = ""
        try{
            PN = myComponents.mainViewModel.dataListChart[1].parameter_name
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

            if(myComponents.mainViewModel.readingStatusList[0].readingStatusE != readingStatusEnum.available)
            Column {
                var reading12 by remember { mutableStateOf("0") }

                ReadingValue(
                    text = reading12,
                    label = "Enter Value",
                    onTextChange = {
                            // Filter out commas from the input text
                            val filteredValue = it.replace(",", "")
                            // Update the state with the filtered value
                            reading12 = filteredValue
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

                SubmitButton(text = "Submit", onClick = { })

            }

            if(myComponents.mainViewModel.readingStatusList[1].readingStatusE != readingStatusEnum.available)
            Column {

                var reading22 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading22,
                    label = "Enter Value",
                    onTextChange = {
                            // Filter out commas from the input text
                            val filteredValue = it.replace(",", "")
                            // Update the state with the filtered value
                            reading22 = filteredValue
                                   },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

            Column {

                var reading32 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading32,
                    label = "Enter Value",
                    onTextChange = {
                            // Filter out commas from the input text
                            val filteredValue = it.replace(",", "")
                            // Update the state with the filtered value
                            reading32 = filteredValue
                                   },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

            Column {

                var reading42 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading42,
                    label = "Enter Value",
                    onTextChange = {
                            // Filter out commas from the input text
                            val filteredValue = it.replace(",", "")
                            // Update the state with the filtered value
                            reading42 = filteredValue
                                   },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

            Column {

                var reading52 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading52,
                    label = "Enter Value",
                    onTextChange = {
                            // Filter out commas from the input text
                            val filteredValue = it.replace(",", "")
                            // Update the state with the filtered value
                            reading52 = filteredValue
                                   },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

        }
    }
}

@Composable
fun ReadingRow3rd(){
    Column {
        var PN = ""
        try{
            PN = myComponents.mainViewModel.dataListChart[2].parameter_name
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

            Column {
                var reading13 by remember { mutableStateOf("0") }

                ReadingValue(
                    text = reading13,
                    label = "Enter Value",
                    onTextChange = {
                        // Filter out commas from the input text
                        val filteredValue = it.replace(",", "")
                        // Update the state with the filtered value
                        reading13 = filteredValue
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

                SubmitButton(text = "Submit", onClick = { })

            }

            Column {

                var reading23 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading23,
                    label = "Enter Value",
                    onTextChange = {
                        // Filter out commas from the input text
                        val filteredValue = it.replace(",", "")
                        // Update the state with the filtered value
                        reading23 = filteredValue
                    },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

            Column {

                var reading33 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading33,
                    label = "Enter Value",
                    onTextChange = {
                        // Filter out commas from the input text
                        val filteredValue = it.replace(",", "")
                        // Update the state with the filtered value
                        reading33 = filteredValue
                    },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

            Column {

                var reading43 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading43,
                    label = "Enter Value",
                    onTextChange = {
                        // Filter out commas from the input text
                        val filteredValue = it.replace(",", "")
                        // Update the state with the filtered value
                        reading43 = filteredValue
                    },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

            Column {

                var reading53 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading53,
                    label = "Enter Value",
                    onTextChange = {
                        // Filter out commas from the input text
                        val filteredValue = it.replace(",", "")
                        // Update the state with the filtered value
                        reading53 = filteredValue
                    },
                    color = pureBlack,
                    maxLength = 5,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

            }

        }
    }
}