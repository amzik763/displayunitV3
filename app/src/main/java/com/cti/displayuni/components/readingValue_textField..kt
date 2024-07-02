package com.cti.displayuni.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.showLogs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingValue(
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
//  enabled : Boolean
    ) {
    val conf = LocalConfiguration.current
    val widthdp = conf.screenWidthDp.dp

    val wd = mParameters.mWidthinPx
    //myUI variables


    var textFont2 = 16.sp
    var textFont3 = 16.sp
    var startpadding = 36.dp
    var width = 150.dp
    var height = 64.dp

    showLogs("dwinsize: ", wd.toString())


    showLogs("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        height = 4.dp
        textFont2 = 12.sp
        textFont3 = 10.sp
        startpadding = 18.dp
        width = 100.dp
        height = 60.dp

        showLogs("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont2 = 16.sp
        height = 8.dp
        startpadding = 36.dp
        width = 150.dp
        height = 64.dp
        textFont3 = 16.sp

        showLogs("Desktop: ", wd.toString())
    }


    Box(
        modifier = modifier
//            .padding(start = 36.dp, top = 12.dp), uncomment later
        .padding(start = startpadding),
        contentAlignment = Alignment.CenterStart,

        ) {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = orange,
                unfocusedBorderColor = orange,
                containerColor = lightGrey,
                focusedLabelColor = lightBlack
                ),
            keyboardOptions = keyboardOptions ,
            value = text,
            onValueChange = {
                val newText = it.take(maxLength)
                onTextChange(newText)
            },
            shape= shape,
            maxLines = maxLine,
            label = {
                    Text(text = label,
                        fontSize = textFont3)
                     },
            modifier = Modifier
                    .size(width = width, height = height ),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize = textFont2,
            ),
        )
    }
}