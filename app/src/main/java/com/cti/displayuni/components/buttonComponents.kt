package com.cti.displayuni.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.blue
import com.cti.displayuni.ui.theme.paleWhite
import com.cti.displayuni.ui.theme.poppinsFontFamily

@Composable
fun CustomRoundedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 50.dp,
    backgroundColor: Color = blue,
    contentColor: Color = paleWhite,
    padding: Dp = 16.dp,
    height: Dp = 40.dp
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = contentColor),
        contentPadding = PaddingValues(0.dp),

        modifier = modifier
            .padding(top = padding)
            .height(height)
    ) {
        Text(text = text,
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            fontFamily = poppinsFontFamily,
            color = paleWhite,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.mediumPadding)))
    }
}