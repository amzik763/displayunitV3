package com.cti.displayuni.components

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.dimens
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureWhite

//abc
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputTextField(
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
    shape: RoundedCornerShape

) {
    val focusManager = LocalFocusManager.current
    val widthDP = LocalConfiguration.current.screenWidthDp.dp


    var passwordVisible by remember { mutableStateOf(false) }

    val lockIcon = if (passwordVisible) {
        painterResource(id = R.drawable.ic_lock_open)
    } else {
        painterResource(id = R.drawable.ic_lock_close)
    }

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
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                .width(widthDP/ MaterialTheme.dimens.textFieldWidth),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.SemiBold,
                color = color,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = "Password Icon",
                    modifier = Modifier
                        .size(MaterialTheme.dimens.iconSize)
                        .fillMaxWidth()
                )
            },
            trailingIcon = {
                Icon(
                painter = lockIcon,
                contentDescription = "Password Icon",
                modifier = Modifier
                    .size(MaterialTheme.dimens.iconSize)
                    .fillMaxWidth()
                    .clickable {
                        passwordVisible = !passwordVisible // Toggle visibility
                    }
            )   },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()
                onImeAction()}
            )
        )
    }
}