package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.utility.mParameters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMark(
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
    val limitedText = text.take(maxLength)
    val conf = LocalConfiguration.current
    val widthdp = conf.screenWidthDp.dp
    val dnsty = conf.densityDpi

    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont1 = 18.sp
    var width = 240.dp
    var height = 64.dp
    var startPadding = 16.dp


    Log.d("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {


        textFont1 = 10.sp
        width = 180.dp
        height = 64.dp
        startPadding = 8.dp


        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont1 = 16.sp
        width = 240.dp
        height = 64.dp
        startPadding = 16.dp

        Log.d("Desktop: ", wd.toString())
    }

    Box(
        modifier = modifier
            .padding(start = startPadding),
        contentAlignment = Alignment.CenterStart,

        ) {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = lightGrey,
                unfocusedBorderColor = lightGrey,
                focusedLabelColor = lightBlack
                ),
//            modifier = Modifier.background(color= pureWhite),
            value = text,
            onValueChange = {
                val newText = it.take(maxLength)
                onTextChange(newText)
            },
            shape= shape,
            maxLines = maxLine,
            label = {
                    Text(text = label,
                        fontSize = textFont1)
                     },
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .size(width = width, height = height ),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize = textFont1,

            )
        )
    }
}