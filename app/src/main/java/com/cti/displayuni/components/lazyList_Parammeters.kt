package com.cti.displayuni.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.Setting_Param
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs

@Composable
fun ParametersLazyList(
    dataListSetting: MutableList<Setting_Param>
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(dataListSetting) { item ->
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.param_name.toString(),
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = pureBlack,
                            fontFamily = mFont.nkbold,
                        ),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(0.7f),
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    Row (verticalAlignment = Alignment.CenterVertically){
                        var enterValue by remember { mutableStateOf("") }

                        item.param_value = enterValue

                        Row(modifier = Modifier.width(180.dp)){
                            EnterValue(
                                text = enterValue,
                                label = "Enter Value",
                                onTextChange = { enterValue = it },
                                color = pureBlack,
                                maxLength = 15,
//                    keyboardOptions = ,
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                        Text(
                            text = item.param_unit.toString(),
                            style = TextStyle(
                                fontSize = 15.sp,
                                color = pureBlack,
                                fontFamily = mFont.nk,
                            ),
                            modifier = Modifier
                                .padding(top = 8.dp, start = 4.dp)
                                .width(60.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                        LaunchedEffect(myComponents.mUiViewModel.clearFields.value) {
                            enterValue = ""
                            showLogs("val","val cleared: ACTUAL")

                        }
                    }
                }
                Divider(
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
        }
    }
}

