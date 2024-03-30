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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cti.displayuni.R
import com.cti.displayuni.components.ItemListScreen
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mFont.poppinsregular
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs

@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun Checksheet2() {
    val conf = LocalConfiguration.current

    val currentDateTime = myComponents.mUiViewModel.currentDateTime.observeAsState("")

    var isSupOkPresent = false


    val dnsty = conf.densityDpi

//    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables
    var textFont1 = 18.sp
    var textFont2 = 18.sp
    var width = 180.dp
    var topPadding = 64.dp
    var startPadding = 36.dp
    var endPadding = 48.dp
    var bottomPadding = 48.dp
    var height = 40.dp
    var imgSize = 50.dp
    var toppadding2 = 52.dp
    var startpadding2 = 52.dp
    var endpadding2 = 52.dp
    var textFont3 = 20.sp
    showLogs("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        topPadding = 20.dp
        startPadding = 20.dp
        endPadding = 20.dp
        bottomPadding = 20.dp
        startPadding = 16.dp
        textFont1 = 17.sp
        textFont2 = 12.sp
        textFont3 = 12.sp
        topPadding = 20.dp
        width = 185.dp
        height = 40.dp
        imgSize = 30.dp
        toppadding2 = 10.dp
        startpadding2 = 40.dp
        endpadding2 = 30.dp

        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        topPadding = 24.dp
        startPadding = 48.dp
        endPadding = 48.dp
        bottomPadding = 24.dp
        startPadding = 36.dp
        textFont1 = 36.sp
        textFont2 = 20.sp
        textFont3 = 22.sp
        toppadding2 = 14.dp
        startpadding2 = 64.dp
        endpadding2 = 52.dp
        width = 240.dp
        height = 52.dp
        imgSize = 50.dp
        toppadding2 = 52.dp

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
                    top = topPadding,
                    end = endPadding,
                    start = startPadding,
                    bottom = bottomPadding
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Text(
                        text = "Name: ",
                        style = TextStyle(
                            fontSize = textFont2,
                            color = pureBlack,
                            fontFamily = poppinsregular
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
                        text = "Device Id: ",
                        style = TextStyle(
                            fontSize = textFont2,
                            color = pureBlack,
                            fontFamily = poppinsregular
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
                            fontFamily = poppinsregular
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
                Text(
                    text = currentDateTime.value,
                    style = TextStyle(
                        fontSize = textFont2,
                        color = pureBlack,
                        fontFamily = poppinsregular
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(0.2f),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.ic_hindi),
                        contentDescription = "Hindi icon",
                        modifier = Modifier.size(imgSize).clickable {
                            myComponents.mUiViewModel.isHindi.value =
                                !myComponents.mUiViewModel.isHindi.value
                        }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_account),
                        contentDescription = "Account",
                        modifier = Modifier.size(imgSize)

                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = "Logout",
                        modifier = Modifier
                            .size(imgSize)
                            .clickable {
                                myComponents.navController.popBackStack()
                                myComponents.navController.navigate("Login")
                            }
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 0.dp)
                .background(color = lightGrey)
                .fillMaxHeight(0.83f)
        ) {
            Box(
                modifier = Modifier
                    .background(color = lightGrey)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(24.dp)
            ) {
                ItemListScreen()
            }
        }

        Row(
            modifier = Modifier
                .padding(start = startpadding2, end = endpadding2, top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Fill Up The Start Up Check Sheet To Start Testing",
                    style = TextStyle(
                        fontSize = textFont1,
                        color = lightOrange,
                        fontFamily = mFont.nkmedium
                    )
                )

            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Row {

                    Surface(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(width = width, height = height),
                        color = darkBlue,
                        shape = RoundedCornerShape(corner = CornerSize(36.dp)),
                        border = BorderStroke(width = 1.dp, color = darkBlue),
                    ) {
                        ClickableText(
                            text = AnnotatedString("Submit"),
                            style = TextStyle(
                                color = pureWhite,
                                fontSize = textFont3,
                                fontFamily = poppinsregular,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(9.dp),
                            onClick = {
//                                myComponents.mainViewModel.checkSheetList.forEach{ item ->

                                showLogs(
                                    "LISTT",
                                    myComponents.mainViewModel.checkSheetList.size.toString()
                                )

                                myComponents.mainViewModel.checkItemsInList()

                                /*    for(item in myComponents.mainViewModel.checkSheetList){

                                    showLogs("DIALOG","SHOW DIALOG")

                                    if (item == "SUP_OK"){
                                        showLogs("DIALOG","SHOW DIALOG")
                                        myComponents.mUiViewModel.showLoginSupDialog()
                                        break
                                    }else{
                                        showLogs("DIALOG","HIDE DIALOG")
                                        myComponents.mainViewModel.addChecksheetData()
                                        break
                                    }
                                }*/
                            }
                        )
                    }
                }
            }
        }
    }

}
