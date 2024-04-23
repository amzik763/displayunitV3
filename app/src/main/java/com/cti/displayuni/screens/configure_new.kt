package com.cti.displayuni.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.R
import com.cti.displayuni.components.EnterHereTextField
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.GETTASK
import com.cti.displayuni.utility.LOGIN
import com.cti.displayuni.utility.mFont.nk
import com.cti.displayuni.utility.mFont.nkbold
import com.cti.displayuni.utility.mFont.poppinsregular
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.myComponents.navController

//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun ConfigureNew(){
    val wd = mParameters.mWidthinPx
    //myUI variables
    var mainHeaderFont = 58.sp
    var semiHeaderFont = 36.sp
    var textFont = 18.sp
    var width = 180.dp
    var height = 40.dp
    Log.d("dwinsize: ", wd.toString())

    val conf = LocalConfiguration.current
    val dnsty = conf.densityDpi

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {
        mainHeaderFont = 40.sp
        semiHeaderFont = 24.sp
        textFont = 16.sp
        width = 180.dp
        height = 40.dp

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {
        mainHeaderFont = 56.sp
        semiHeaderFont = 40.sp
        textFont = 24.sp
        width = 210.dp
        height = 50.dp
        Log.d("Desktop: ", wd.toString())
    }

    var location by remember { mutableStateOf("") }
    var floorValue by remember { mutableStateOf("") }
    var lineValue by remember { mutableStateOf("") }
    var stationValue by remember { mutableStateOf("") }
    var G0F0L0S0Value by remember { mutableStateOf(" F L S") }



        val widthDP = conf.screenWidthDp.dp
        Row {
            Box(modifier = Modifier.width(widthDP/3f)){
                Image(painter = painterResource(id = R.drawable.bg_background),
                    contentDescription = "Blue Background",
                    contentScale = ContentScale.Crop,
                )
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxSize()
                    .padding(start = 36.dp, top = 48.dp, bottom = 36.dp),
                    verticalArrangement = Arrangement.SpaceBetween){
                    Column {
                        Text(text = "INTERFACE",
                            style = TextStyle(
                                fontSize = mainHeaderFont,
                                fontWeight = FontWeight.Bold,
                                color = pureWhite,
                                fontFamily = nkbold)
                        )

                    }
                    Text(
                        text = "Developed by Cellus Tech India",
                        style = TextStyle(fontSize = semiHeaderFont,
                            fontWeight = FontWeight.Bold,
                            color = pureWhite,
                            fontFamily = nk)
                    )
                }
            }
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(
                        text = "Enter Station ID",
                        style = TextStyle(
                            fontSize = mainHeaderFont,
                            fontWeight = FontWeight.Bold,
                            color = lightBlack,
                            fontFamily = nkbold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                LaunchedEffect(location, floorValue, lineValue, stationValue) {
                    val newG = location.takeIf { it.isNotBlank() } ?: "0"
                    val newF = floorValue.takeIf { it.isNotBlank() } ?: "0"
                    val newL = lineValue.takeIf { it.isNotBlank() } ?: "0"
                    val newS = stationValue.takeIf { it.isNotBlank() } ?: "0"
                    val newValue = "$newG F$newF L$newL S$newS"
                    G0F0L0S0Value = newValue

                }

                Text(
                    text = G0F0L0S0Value,
                    style = TextStyle(
                        fontSize = semiHeaderFont,
                        fontWeight = FontWeight.Bold,
                        color = orange,
                        fontFamily = nk
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row {
                    EnterHereTextField(
                        text = location,
                        label = "Enter Location",
                        onTextChange = { newText ->
                            location = newText
                        },
                        color = pureBlack,
                        maxLength = 3,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        shape = RoundedCornerShape(8.dp)
                    )

                    EnterHereTextField(
                        text = floorValue,
                        label = "Enter Floor No.",
                        onTextChange = { newText ->
                            floorValue = newText.filter { it.isDigit() }
                        },
                        color = pureBlack,
                        maxLength = 2,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(8.dp)
                    )

                    EnterHereTextField(
                        text = lineValue,
                        label = "Enter line No.",
                        onTextChange = { newText ->
                            lineValue = newText.filter { it.isDigit() }
                        },
                        color = pureBlack,
                        maxLength = 2,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(8.dp)
                    )

                    EnterHereTextField(
                        text = stationValue,
                        label = "Enter Station No.",
                        onTextChange = { newText ->
                            stationValue = newText.filter { it.isDigit() }
                        },
                        color = pureBlack,
                        maxLength = 2,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(8.dp)
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(0.4f),
                    horizontalAlignment = Alignment.End
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .size(width = width, height = height),
                        color = darkBlue,
                        shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                        border = BorderStroke(width = 1.dp, color = darkBlue)
                    ) {
                        ClickableText(
                            text = AnnotatedString("Configure"),
                            style = TextStyle(
                                color = pureWhite,
                                fontSize = textFont,
                                fontFamily = poppinsregular,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(9.dp)
                                .align(Alignment.CenterHorizontally),
                            onClick = {
                                mainViewModel.saveStationValue(G0F0L0S0Value)
                                Log.d("Shared Value", G0F0L0S0Value)

                                mainViewModel.floorNum =
                                    G0F0L0S0Value.split(" ").take(2).joinToString(" ")
                                Log.d("FLOOR VALUE", mainViewModel.floorNum)

                                navController.navigate(GETTASK)
                            }
                        )
                    }
                }
            }
        }
    }

