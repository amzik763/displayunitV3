package com.cti.displayuni.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cti.displayuni.ui.theme.extraLightGrey


@Composable
fun Header(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
            ) {
        //First Column
        Row(modifier = Modifier
            .background(color = extraLightGrey)
            .fillMaxWidth()
            .height(60.dp)
            .padding(12.dp)
        ) {

        }
        //Second Column
        Row(modifier = Modifier
            .background(color = Color.Blue)
            .fillMaxWidth()
            .height(60.dp)
            .padding(12.dp)
        ) {

        }
    }

}


@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun PreviewUi(){
    Header()
}