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
import androidx.compose.material3.MaterialTheme
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
import com.cti.displayuni.components.CustomRoundedButton
import com.cti.displayuni.components.PasswordInputTextField
import com.cti.displayuni.components.UserIdInputTextField
import com.cti.displayuni.ui.theme.blue
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.dimens
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.GETTASK
import com.cti.displayuni.utility.mFont.nk
import com.cti.displayuni.utility.mFont.nkbold
import com.cti.displayuni.utility.mFont.poppinsbold
import com.cti.displayuni.utility.mFont.poppinsregular
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.myComponents.navController
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

    var widthdp = widthDP/3f
    var sHeight = 54.dp
    Log.d("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        widthdp = widthDP/3.7f
        sHeight = 36.dp

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        widthdp = widthDP/3f
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
                .padding(MaterialTheme.dimens.padding),
                verticalArrangement = Arrangement.SpaceBetween){
                Column {

                    Image(painter = painterResource(id = R.drawable.interfacelogo),
                        contentDescription = "Interface Logo",
                        modifier = Modifier.width(widthDP/6),
                        contentScale = ContentScale.FillWidth
                    )

                }
                Text(
                    text = "Developed by Cellus Tech India",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
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
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = lightBlack,
                            fontFamily = nkbold
                        )
                    )

                    Text(
                        text = "Continue",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = lightOrange,
                            fontFamily = nkbold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.largePadding))
                Text(
                    text = "Enter Your Password",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        color = pureBlack,
                        fontFamily = poppinsregular
                    )
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.mediumPadding))

                UserIdInputTextField(
                    text = name,
                    label = "Username",
                    onTextChange = { val filteredText = it.filter { char -> char.isLetterOrDigit() }
                        name = filteredText
                                   },
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
                    modifier = Modifier.fillMaxWidth(0.3f),
                    horizontalAlignment = Alignment.End
                ) {

                    CustomRoundedButton(onClick = {

                        mainViewModel.loginUser(name, password)

                    }, text = "Login")

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
