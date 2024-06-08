package com.cti.displayuni.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.cti.displayuni.R

@Composable
fun DisplayModeImage(
    onCloseClicked: () -> Unit,
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            Icon(painter = painterResource(id = R.drawable.baseline_cancel_24),
                contentDescription = "cancel",
                modifier = Modifier.clickable { onCloseClicked() })
        }

            ZoomableImage()
        }

}