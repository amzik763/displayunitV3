package com.cti.displayuni.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.R
import com.cti.displayuni.components.CustomRoundedButton
import com.cti.displayuni.components.ItemListScreen
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.dimens
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.FILL_PARAMETERS
import com.cti.displayuni.utility.LOGIN
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mFont.poppinsregular
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.myComponents.navController
import com.cti.displayuni.utility.showLogs

@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun Checksheet2() {
    val conf = LocalConfiguration.current

    val currentDateTime = myComponents.mUiViewModel.currentDateTime.observeAsState("")

    var isSupOkPresent = false


    var employeID = 7832
    val dnsty = conf.densityDpi

//    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables
    var textFont1 = 18.sp
    var textFont2 = 18.sp
    var width = 180.dp
    var topPadding = 64.dp
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

    showLogs("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        topPadding = 20.dp
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
        sWidth = 12.dp

        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        topPadding = 24.dp
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

        showLogs("Desktop: ", wd.toString())
    }

    Column(
        modifier = Modifier.fillMaxSize(),
//          verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .background(color = lightGrey)
                .padding(
                    end = MaterialTheme.dimens.smallPadding,  //headerpadding
                    bottom = MaterialTheme.dimens.smallPadding
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
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = pureBlack,
                            fontFamily = poppinsregular
                        )
                    )
                    Text(
                        text = myComponents.mainViewModel.name,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
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
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = pureBlack,
                            fontFamily = poppinsregular
                        )
                    )
                    Text(
                        text = myComponents.mainViewModel.skill,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
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
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = pureBlack,
                            fontFamily = poppinsregular
                        )
                    )
                    Text(
                        text = myComponents.mainViewModel.deviceId,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
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
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = pureBlack,
                            fontFamily = poppinsregular
                        )
                    )
                    Text(
                        text = myComponents.mainViewModel.employeeId,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = pureBlack,
                            fontWeight = FontWeight.Bold,
                            fontFamily = mFont.poppinsbold
                        )
                    )
                }
                Text(
                    text = currentDateTime.value,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = pureBlack,
                        fontFamily = poppinsregular
                    )
                )


                    Image(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = "Logout",
                        modifier = Modifier
                            .size(MaterialTheme.dimens.logoSize)
                            .clickable {
                                navController.popBackStack()
                                navController.navigate("Login")
                            }
                    )

            }
        }

        Column(
            modifier = Modifier
                .padding(top = 0.dp)
                .background(color = lightGrey)
                .fillMaxHeight(0.9f)
        ) {
            Box(
                modifier = Modifier
                    .background(color = lightGrey)
                    .fillMaxWidth()
                    .fillMaxHeight()
//                    .padding(padding)
            ) {
                ItemListScreen()
            }
        }
        Row(
            modifier = Modifier
                .padding(start = MaterialTheme.dimens.endPadding,
                    end = MaterialTheme.dimens.endPadding, top = toppadding2)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Fill Up The Start Up Check Sheet To Start Testing",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        color = lightOrange,
                        fontFamily = mFont.nkmedium
                    )
                )

                    CustomRoundedButton(onClick = {

                        showLogs("LISTT", mainViewModel.checkSheetList.size.toString())

                        mainViewModel.checkItemsInList()
//                                myComponents.navController.navigate(FILL_PARAMETERS)

                    }, text = "Submit")


            }
        }
    }
}

