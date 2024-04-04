package com.cti.displayuni.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.components.ReadingValue
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.green
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.myComponents

@Composable
fun ReadingComponent(heading: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(modifier = Modifier.padding(start = 16.dp,
            bottom = 16.dp),
            text = heading,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = mFont.poppinsbold
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


@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun CustomPopupContent(
//    onCloseClicked: () -> Unit,
) {


    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(top = 50.dp),
     horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .background(color = Color.White),
//            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Create input text fields with random
            Column {
                ReadingComponent(heading = "Reading One")

                var reading1 by remember { mutableStateOf("0") }

                ReadingValue(
                    text = reading1,
                    label = "Enter Value",
                    onTextChange = { reading1 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
//                    enabled = if( myComponents.mainViewModel.r1.length==0)true else false
                )

                SubmitButton(text = "Submit", onClick = { })


                Spacer(modifier = Modifier.height(50.dp))
                var reading12 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading12,
                    label = "Enter Value",
                    onTextChange = { reading12 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
//                    enabled = if( myComponents.mainViewModel.r1.length==0)true else false
                )

                SubmitButton(text = "Submit", onClick = { })

                Spacer(modifier = Modifier.height(50.dp))
                var reading13 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading13,
                    label = "Enter Value",
                    onTextChange = { reading13 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
//                    enabled = if( myComponents.mainViewModel.r1.length==0)true else false
                )

                SubmitButton(text = "Submit", onClick = { })


            }


            Column {
                ReadingComponent(heading = "Reading Two")
                var reading2 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading2,
                    label = "Enter Value",
                    onTextChange = { reading2 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})


                Spacer(modifier = Modifier.height(50.dp))
                var reading22 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading22,
                    label = "Enter Value",
                    onTextChange = { reading22 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

                Spacer(modifier = Modifier.height(50.dp))
                var reading23 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading23,
                    label = "Enter Value",
                    onTextChange = { reading23 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})
            }

            Column {
                ReadingComponent(heading = "Reading Three")
                var reading3 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading3,
                    label = "Enter Value",
                    onTextChange = { reading3 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

                Spacer(modifier = Modifier.height(50.dp))
                var reading32 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading32,
                    label = "Enter Value",
                    onTextChange = { reading32 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

                Spacer(modifier = Modifier.height(50.dp))
                var reading33 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading33,
                    label = "Enter Value",
                    onTextChange = { reading33 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})
            }

            Column {
                ReadingComponent(heading = "Reading Four")
                var reading4 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading4,
                    label = "Enter Value",
                    onTextChange = { reading4 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})


                Spacer(modifier = Modifier.height(50.dp))
                var reading42 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading42,
                    label = "Enter Value",
                    onTextChange = { reading42 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})

                Spacer(modifier = Modifier.height(50.dp))
                var reading43 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading43,
                    label = "Enter Value",
                    onTextChange = { reading43 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})
            }

            Column {
                ReadingComponent(heading = "Reading Five")
                var reading5 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading5,
                    label = "Enter Value",
                    onTextChange = { reading5 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})


                Spacer(modifier = Modifier.height(50.dp))
                var reading52 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading52,
                    label = "Enter Value",
                    onTextChange = { reading52 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})


                Spacer(modifier = Modifier.height(50.dp))
                var reading53 by remember { mutableStateOf("0") }
                ReadingValue(
                    text = reading53,
                    label = "Enter Value",
                    onTextChange = { reading53 = it },
                    color = pureBlack,
                    maxLength = 15,
                    keyboardOptions = KeyboardOptions(),
                    shape = RoundedCornerShape(8.dp),
                )
                SubmitButton(text = "Submit", onClick = {})
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
//                    onCloseClicked()

                }
            )
        }
    }
}