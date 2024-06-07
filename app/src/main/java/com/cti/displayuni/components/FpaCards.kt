package com.cti.displayuni.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.utility.mFont

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    backgroundColor: Color = Color.White,
) {

    val formattedDescription = description.replace(",", "\n")

    Card(
        modifier = modifier
            .fillMaxHeight(0.78f)
           .width(450.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Text(text = title,
            fontSize = 25.sp,
            fontFamily = mFont.poppinsbold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp, start = 20.dp))

        LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            formattedDescription.split("\n").forEach { line ->
                item {
                    Text(
                        text = line,
                        fontSize = 20.sp,
                        fontFamily = mFont.poppinsregular
                    )
                }
            }
        }
    }
}

