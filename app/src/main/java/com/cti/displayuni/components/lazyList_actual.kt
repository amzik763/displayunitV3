package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.Actual_Param
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs


@Composable
fun ActualLazyList(
    dataListActual: MutableList<Actual_Param>
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(dataListActual) { item ->

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.param_name,
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = pureBlack,
                            fontFamily = mFont.nkbold,
                        ),
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Black
                    )
                    var enterValue by remember { mutableStateOf("") }

                    item.param_value = enterValue

                    EnterValue(
                        text = enterValue,
                        label = "Enter Value",
                        onTextChange = { enterValue = it },
                        color = pureBlack,
                        maxLength = 15,
//                    keyboardOptions = ,
                        shape = RoundedCornerShape(8.dp)
                    )

                    LaunchedEffect(myComponents.mUiViewModel.clearFields.value) {
                        enterValue = ""
                        showLogs("val","val cleared: ACTUAL")

                    }
                }

                Divider(
                    modifier = Modifier.padding(top = 16.dp),
                    color = Color.Black
                )


            }

        }
    }
}
/*
@Composable
fun ActualLazyList(dataListActual: MutableList<Actual_Param>) {

    data class CD(val a:String,val b:Int)

    var abc = listOf<CD>(CD("sa",4),CD("gf",8))


    LazyColumn(modifier = Modifier.fillMaxHeight()){
        items(dataListActual){ item ->

            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = item.param_name,
                    modifier = Modifier.padding(top = 8.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = pureBlack,
                        fontFamily = mFont.nkbold,
                    )
                )
                var enterValue by remember { mutableStateOf("") }
                item.param_value = enterValue
                Log.d("par",item.param_name+" "+item.param_id+" "+item.param_value)
                EnterValue(
                    text = enterValue ,
                    label = "Enter Value",
                    onTextChange = { enterValue = it },
                    color = pureBlack,
                    maxLength = 15,
//                    keyboardOptions = ,
                    shape = RoundedCornerShape(8.dp)
                )

//                LaunchedEffect(
//                    myComponents.mUiViewModel.clearFields.value
//                ) {
//                        enterValue = ""
//                    showLogs("val","val cleared: ACTUAL")
//
//                }
            }


        }
    }
}*/
