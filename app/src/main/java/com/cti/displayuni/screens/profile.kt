package com.cti.displayuni.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.CONFIGURE_NEW
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents

@Composable
fun ProfileText(name:String,value:String){

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont1 = 18.sp
    var height = 36.dp


    Log.d("dwinsize: ", wd.toString())

    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {
        textFont1 = 14.sp
        height = 20.dp

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {
        textFont1 = 24.sp
        height = 36.dp
        Log.d("Desktop: ", wd.toString())
    }

    Spacer(modifier = Modifier.height(height))
        Row {
            Text(text = name,
                style = TextStyle(
                    fontSize = textFont1,
                    fontFamily = mFont.nk
                )
            )
            Text(text = value,
                style = TextStyle(
                    fontSize = textFont1,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.nkbold
                )
            )
        }
}

//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun Profile(){

    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp
    val dnsty = conf.densityDpi

    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont = 36.sp
    var textFont1 = 18.sp
    var width = 180.dp
    var height = 40.dp
    var paddingTop = 56.dp
    var paddingStart = 36.dp
    var btnPadding = 9.dp
    var textFont2 = 24.sp

    Log.d("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        textFont = 22.sp
        textFont1 = 11.sp
        width = 130.dp
        height = 30.dp
        paddingTop = 36.dp
        paddingStart = 20.dp
        btnPadding = 4.dp
        textFont2 = 12.sp

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont = 36.sp
        textFont1 = 24.sp
        width = 210.dp
        height = 50.dp
        paddingTop = 56.dp
        paddingStart = 36.dp
        btnPadding = 9.dp
        textFont2 = 24.sp

        Log.d("Desktop: ", wd.toString())
    }

    Column (modifier = Modifier.padding(top = paddingTop, start = paddingStart)
        .fillMaxWidth()){
    Text(text = "Profile Information",
        style = TextStyle(
            fontSize = textFont,
            fontWeight = FontWeight.Bold,
            fontFamily = mFont.nkbold
        )
    )
        ProfileText(name = "Employee Id:  ", value = myComponents.mainViewModel.employeeId)
        ProfileText(name = "Name:  ", value = myComponents.mainViewModel.name)
        ProfileText(name = "Date of Birth:  ", value = myComponents.mainViewModel.dob)
        ProfileText(name = "E-mail:  ", value = myComponents.mainViewModel.email)
        ProfileText(name = "Skill:  ", value = myComponents.mainViewModel.skill)
        ProfileText(name = "Mobile Number:  ", value = myComponents.mainViewModel.mobileNum)

        Spacer(modifier = Modifier.height(paddingStart))

        var passwordVisible by remember { mutableStateOf(false) }
        val password = myComponents.mainViewModel.password

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Password:  ",
                style = TextStyle(
                    fontSize = textFont2,
                    fontFamily = mFont.nk
                )
            )

            val lockIcon = if (passwordVisible) {
                painterResource(id = R.drawable.ic_lock_open)
            } else {
                painterResource(id = R.drawable.ic_lock_close)
            }

            Text(text = if (passwordVisible) password else "*".repeat(password.length),
                style = TextStyle(
                    fontSize = textFont2,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.nkbold
                ),
                modifier = Modifier.fillMaxWidth(0.15f)

            )
            Image(
                painter = lockIcon,
                contentDescription =if (passwordVisible) "Unlock" else "Lock",
                modifier = Modifier.clickable {
                    passwordVisible = !passwordVisible // Toggle visibility
                }
            )
        }


        Surface(
            modifier = Modifier
                .padding(top = paddingStart)
                .size(width = width, height = height)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    myComponents.navController.navigate(CONFIGURE_NEW)

                },
            color = darkBlue,
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            border = BorderStroke(width = 1.dp, color = darkBlue)
        ) {
            Text(
                text = AnnotatedString("Configure"),
                style = TextStyle(
                    color = pureWhite,
                    fontSize = textFont1,
                    fontFamily = mFont.poppinsregular,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(btnPadding)
                    .align(Alignment.CenterHorizontally),

            )
        }

    }
}