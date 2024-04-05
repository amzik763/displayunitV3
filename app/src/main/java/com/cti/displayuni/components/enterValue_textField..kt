package com.cti.displayuni.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
    val widthdp = conf.screenWidthDp.dp

    Box(
        modifier = modifier
            .padding(start = if(mParameters.dnsty == 320) {
                14.dp
            } else 28.dp, top = if(mParameters.dnsty == 320) 8.dp else 8.dp),
        contentAlignment = Alignment.CenterStart,

        ) {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = orange,
                unfocusedBorderColor = orange,
                containerColor = lightGrey,
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
                    Text(text = label)
                     },
            modifier = Modifier
                .size(width = if(mParameters.dnsty == 320) 150.dp else 150.dp, height = if(mParameters.dnsty == 320) 64.dp else 64.dp ),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize =  if(mParameters.dnsty == 320) 12.sp else 16.sp,

            )
        )
    }
}