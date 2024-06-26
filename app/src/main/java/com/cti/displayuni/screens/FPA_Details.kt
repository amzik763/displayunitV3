package com.cti.displayuni.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.components.CardComponent
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.myComponents

//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun FPA_Details(
    onCloseClicked: () -> Unit,
){

    Column (modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .fillMaxHeight()
        .fillMaxWidth()
        .background(color = Color.White)){
        Text(text = "FPA DETAILS",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = mFont.poppinsbold)

        Divider(
            modifier = Modifier.width(150.dp)
                .height(8.dp),
            color = orange
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){

            CardComponent(
                title = "Start Shift 1",
                description = myComponents.mainViewModel.fpa1.value.toString(),
                backgroundColor = Color.White
            )
            CardComponent(
                title = "Start Shift 2",
                description = myComponents.mainViewModel.fpa2.value.toString(),
                backgroundColor = Color.White
            )
            CardComponent(
                title = "Mid Shift 1",
                description = myComponents.mainViewModel.fpa3.value.toString(),
                backgroundColor = Color.White
            )
            CardComponent(
                title = "Mid Shift 2",
                description = myComponents.mainViewModel.fpa4.value.toString(),
                backgroundColor = Color.White
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(top = 40.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = {
                onCloseClicked()
            },
                shape = RoundedCornerShape(9.dp),
                modifier = Modifier.size(width = 140.dp, height = 40.dp),
                border = BorderStroke(3.dp, darkBlue),
                colors = ButtonDefaults.buttonColors(contentColor = pureWhite, containerColor = darkBlue),

                ) {
                Text(
                    text = AnnotatedString("Close"),
                    style = TextStyle(
                        color = pureWhite,
                        fontSize = 16.sp,
                        fontFamily = mFont.poppinsbold,
                        textAlign = TextAlign.Center
                )   )
            }
        }
    }
}

