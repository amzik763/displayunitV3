package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterValue(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction:() -> Unit = {},
    color: Color,
    maxLength: Int,
    shape: RoundedCornerShape,

    ) {
//    val limitedText = text.take(maxLength)
    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp
    val dnsty = conf.densityDpi

    val wd = mParameters.mWidthinPx
    //myUI variables

    var start = 14.dp
    var height = 40.dp
    var width = 150.dp
    var fontSize = 14.sp

    mParameters.dnsty = dnsty

    if (wd <= 2048 && mParameters.dnsty == 320) {

        width = 120.dp
        height = 40.dp
        start = 8.dp
        fontSize = 8.sp


    } else if (wd <= 2048 && mParameters.dnsty == 160) {
        width = 210.dp
        height = 50.dp
        start = 36.dp
        fontSize = 14.sp
    }

    Box(
        modifier = modifier
            .padding(start),
        contentAlignment = Alignment.CenterStart,

        ) {
        OutlinedTextField(

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = lightGrey,
                unfocusedContainerColor = lightGrey,
                disabledContainerColor = lightGrey,
                focusedBorderColor = orange,
                unfocusedBorderColor = orange,
                focusedLabelColor = lightBlack,

            ),
            value = text,
            onValueChange = {
                val newText = it.take(maxLength)
                onTextChange(newText)
            },
            shape= shape,
            maxLines = maxLine,
            label = {
                    Text(text = label)
                     },
            modifier = Modifier
                .size(width = width, height = height),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize =  fontSize,
            ),
        )
    }
}


@Composable
fun CustomOutlinedTextField(

    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction:() -> Unit = {},
    color: Color,
    maxLength: Int,
    shape: RoundedCornerShape,
    paddingValues: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
) {

    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp
    val dnsty = conf.densityDpi

    val wd = mParameters.mWidthinPx
    //myUI variables

    var start = 14.dp
    var height = 40.dp
    var width = 150.dp
    var fontSize = 14.sp

    mParameters.dnsty = dnsty

    if (wd <= 2048 && mParameters.dnsty == 320) {

        width = 120.dp
        height = 40.dp
        start = 8.dp
        fontSize = 11.sp


    } else if (wd <= 2048 && mParameters.dnsty == 160) {
        width = 210.dp
        height = 50.dp
        start = 36.dp
        fontSize = 14.sp
    }

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .size(width = width, height = height)
            .border(
                width = if (text.isEmpty()) 1.dp else 2.dp,
                color = if (text.isEmpty()) Color.Gray else orange,
                shape = shape
            )
            .padding(horizontal = 8.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
              // Adjust this padding to control the space between text and border
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                val newText = it.take(maxLength)
                onTextChange(newText)
            },

            singleLine = true,
            maxLines = 1,
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize =  fontSize,
            ),
            decorationBox = @Composable { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = label,
                        style = TextStyle(
                            fontSize = fontSize,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp) // Match the padding of the text field
                    )
                }
                innerTextField()
            },
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp).size(width = width, height = height) // Adjust this padding as needed
        )
    }
}
