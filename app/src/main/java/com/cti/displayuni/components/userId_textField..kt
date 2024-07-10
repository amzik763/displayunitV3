package com.cti.displayuni.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cti.displayuni.ui.theme.dimens
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureWhite

//abc
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserIdInputTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction:() -> Unit = {},
    color: Color,
    iconResId: Int,
    maxLength: Int,
    keyboardOptions: KeyboardOptions,
    shape: RoundedCornerShape,

) {
    val widthDP = LocalConfiguration.current.screenWidthDp.dp
    val focusManager = LocalFocusManager.current

    Box(
        contentAlignment = Alignment.CenterStart,

        ) {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = lightOrange,
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
                                fontSize = MaterialTheme.typography.bodySmall.fontSize
                            ),
                        modifier = Modifier.padding(0.dp))
                     },
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .width(widthDP/MaterialTheme.dimens.textFieldWidth),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.SemiBold,
                color = color,
                fontSize =  MaterialTheme.typography.bodySmall.fontSize
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = "User Icon",
                    modifier = Modifier
                        .size(MaterialTheme.dimens.iconSize)
                        .fillMaxWidth()
                )
            },
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Down) })
        )
    }
}
