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

    Box(
        modifier = modifier
            .padding(start = 36.dp, top = 12.dp),
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
                    Text(text = label)
                     },
            modifier = Modifier
                .size(width = 150.dp, height = 64.dp ),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize = 16.sp,
            ),
//            enabled = enabled
        )
    }
}