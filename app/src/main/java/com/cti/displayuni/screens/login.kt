package com.cti.displayuni.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.R
import com.cti.displayuni.components.PasswordInputTextField
import com.cti.displayuni.components.UserIdInputTextField
import com.cti.displayuni.ui.theme.blue
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.mFont.nk
import com.cti.displayuni.utility.mFont.poppinsregular
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.showLogs

@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun Login(){

    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp
    val dnsty = conf.densityDpi

    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables
    var mainHeaderFont = 52.sp
    var semiHeaderFont = 36.sp
    var maxWidth = widthDP/3f
    var textFont = 18.sp
    var width = 180.dp
    var textFont1 = 18.sp
    var textFont2 = 18.sp
    var height = 40.dp
    var padding = 24.dp
    var imgSize = 50.dp
    var interfaceW = 300.dp
    var interfaceH = 70.dp
    var widthdp = widthDP/3f
    var start = 36.dp
    var top = 48.dp
    var bottom = 36.dp
    var btnpadding = 9.dp
    var sHeight = 54.dp
    Log.d("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {
        mainHeaderFont = 34.sp
        maxWidth = widthDP/3.5f
        semiHeaderFont = 20.sp
        textFont = 12.sp
        width = 120.dp
        height = 30.dp
        textFont1 = 14.sp
        textFont2 = 12.sp
        padding = 20.dp
        interfaceW = 180.dp
        interfaceH = 50.dp
        widthdp = widthDP/3.7f
        start = 28.dp
        top = 36.dp
        bottom = 28.dp
        btnpadding = 6.dp
        sHeight = 36.dp

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {
        maxWidth = widthDP/3f
        mainHeaderFont = 58.sp
        semiHeaderFont = 36.sp
        textFont = 24.sp
        width = 210.dp
        height = 50.dp
        textFont1 = 24.sp
        textFont2 = 20.sp
        padding = 24.dp
        interfaceW = 300.dp
        interfaceH = 70.dp
        widthdp = widthDP/3f
        start = 36.dp
        top = 48.dp
        bottom = 36.dp
        btnpadding = 9.dp
        sHeight = 54.dp
        Log.d("Desktop: ", wd.toString())
    }

    Row {
        Box(modifier = Modifier.width(widthdp)){
            Image(painter = painterResource(id = R.drawable.bg_background),
                contentDescription = "Blue Background",
                contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize()
                .padding(start = start, top = top, bottom = bottom),
                verticalArrangement = Arrangement.SpaceBetween){
                Column {

                    Image(painter = painterResource(id = R.drawable.interfacelogo),
                        contentDescription = "Interface Logo",
                        modifier = Modifier.size(interfaceW, interfaceH),
                    )

                }
                Text(
                    text = "Developed by Cellus Tech India",
                    style = TextStyle(
                        fontSize = textFont1,
                        fontWeight = FontWeight.Bold,
                        color = pureWhite,
                        fontFamily = nk)
                )
            }
        }
        Column(verticalArrangement = Arrangement.SpaceBetween) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(
                        text = "Login To ",
                        style = TextStyle(
                            fontSize = mainHeaderFont,
                            fontWeight = FontWeight.Bold,
                            color = lightBlack,
                            fontFamily = nk
                        )
                    )

                    Text(
                        text = "Continue",
                        style = TextStyle(
                            fontSize = mainHeaderFont,
                            fontWeight = FontWeight.Bold,
                            color = lightOrange,
                            fontFamily = nk
                        )
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(0.4f),
                    text = "     Enter Your Password",
                    style = TextStyle(
                        fontSize = semiHeaderFont,
                        color = pureBlack,
                        fontFamily = poppinsregular
                    )
                )
                UserIdInputTextField(
                    text = name,
                    label = "Username",
                    onTextChange = { name = it },
                    color = pureBlack,
                    iconResId = R.drawable.usericon,
                    maxLength = 40,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text).copy(imeAction = ImeAction.Next),
                    shape = RoundedCornerShape(8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordInputTextField(
                    text = password,
                    label = "Password",
                    onTextChange = { password = it },
                    color = pureBlack,
                    iconResId = R.drawable.ic_lock,
                    maxLength = 20,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password).copy(imeAction = ImeAction.Done),
                    shape = RoundedCornerShape(8.dp)
                )

                Column(
                    modifier = Modifier.fillMaxWidth(0.4f),
                    horizontalAlignment = Alignment.End
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .size(width = width, height = height)
                            .clickable {
                                mainViewModel.loginUser(name, password)
                            },
                        color = darkBlue,
                        shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                        border = BorderStroke(width = 1.dp, color = darkBlue)
                    ) {
                        Text(
                            text = AnnotatedString("Login"),
                            style = TextStyle(
                                color = pureWhite,
                                fontSize = textFont,
                                fontFamily = poppinsregular,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(btnpadding)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(sHeight))

//                ToggleButton()
            }
        }
    }
}

//@Preview
@Composable
fun ToggleButton() {

    val wd = mParameters.mWidthinPx
    //myUI variables
    var textFont = 25.sp
    var sWidth = 26.dp
    var endpadding = 36.dp
    showLogs("dwinsize: ", wd.toString())
    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {
        textFont = 14.sp
        sWidth = 16.dp
        endpadding = 12.dp
        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {
        textFont = 25.sp
        sWidth = 26.dp
        endpadding = 36.dp
        showLogs("Desktop: ", wd.toString())
    }

    Row (verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Replacement Login",
            style = TextStyle(
                fontFamily = poppinsregular,
                fontSize = textFont
            )
        )

        Spacer(modifier = Modifier.width(sWidth))
        Switch(
            checked = mainViewModel.isReplacementChecked.value,
            onCheckedChange = { mainViewModel.isReplacementChecked.value = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = blue,
                uncheckedThumbColor = blue,
                checkedTrackColor = lightGrey,
                uncheckedTrackColor = lightGrey
            )
        )
    }
}
