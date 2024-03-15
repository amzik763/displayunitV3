package com.cti.displayuni.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.pureWhite

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
    val limitedText = text.take(maxLength)
    val conf = LocalConfiguration.current
    val widthdp = conf.screenWidthDp.dp


    Box(
        modifier = modifier
            .padding(start = 36.dp, top = 12.dp),
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
                    Text(text = label,modifier = Modifier.padding(2.dp))
                     },
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .width(widthdp/8.5f),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.SemiBold,
                color = color,
                fontSize = 24.sp
            ),

        )
    }
}