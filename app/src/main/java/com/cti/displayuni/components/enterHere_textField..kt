package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.mParameters

//abc
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterHereTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction:() -> Unit = {},
    color: Color,
    maxLength: Int,
    keyboardOptions: KeyboardOptions,
    shape: RoundedCornerShape,

) {
    val wd = mParameters.mWidthinPx

    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp
    //myUI variables
    var textFont = 24.sp
    var widthdp = widthDP/8.5f
    var start = 36.dp
    var labelFont = 12.sp
    Log.d("dwinsize: ", wd.toString())

    val dnsty = conf.densityDpi

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        textFont = 14.sp
        widthdp = widthDP/8.55f
        start = 28.dp
        labelFont = 10.sp

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont = 24.sp
        widthdp = widthDP/8.5f
        start = 36.dp
        labelFont = 12.sp

        Log.d("Desktop: ", wd.toString())
    }

    val limitedText = text.take(maxLength)



    Box(
        modifier = modifier
            .padding(start = start, top = 12.dp),
//            .background(color = pureWhite),
        contentAlignment = Alignment.CenterStart,

        ) {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = lightBlack,
                unfocusedBorderColor = lightGrey,
                containerColor = pureWhite,
                focusedLabelColor = lightBlack
                ),
            value = text,
            onValueChange = {
                val newText = it.take(maxLength)
                onTextChange(newText)
            },
            shape= shape,
            maxLines = maxLine,
            label = {
                    Text(text = label,
                        style = TextStyle(
                            fontSize = labelFont
                        ),
                        modifier = Modifier.padding(0.dp))
                     },
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .width(widthdp),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.SemiBold,
                color = color,
                fontSize = textFont
            ),

        )
    }
}