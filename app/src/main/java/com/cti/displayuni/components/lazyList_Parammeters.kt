package com.cti.displayuni.components

import android.util.Log
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.Setting_Param
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.showLogs
import kotlinx.coroutines.delay

@Composable
fun ParametersLazyList(
    dataListSetting: MutableList<Setting_Param>
) {


    val wd = mParameters.mWidthinPx
    //myUI variables
    var textFont = 15.sp
    var textFont1 = 15.sp
    var top = 8.dp
    var paddingEndSmall = 10.dp
    var gap = 30.dp
    var unitWidth = 60.dp
    var enterValueWidth = 180.dp
    var unitstart = 4.dp
    var paramnamewidth = 0.7f

    Log.d("dwinsize: ", wd.toString())

    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        textFont = 12.sp
        textFont1 = 10.sp
        top = 4.dp
        paddingEndSmall = 4.dp
        gap = 10.dp
        unitWidth = 40.dp
        enterValueWidth = 87.dp
        unitstart = 1.dp
        paramnamewidth = 0.6f



    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont = 15.sp
        textFont1 = 15.sp
        top = 8.dp
        unitstart = 4.dp
        paramnamewidth = 0.7f
        Log.d("Desktop: ", wd.toString())
    }


    // Create a map to store enterValue for each item
    // Map to store enterValue for each item
    val enterValues = rememberSaveable { mutableStateOf(mutableMapOf<Int, String>()) }

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        itemsIndexed(dataListSetting) {index, item ->
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = paddingEndSmall),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = item.param_name.toString(),
                        style = TextStyle(
                            fontSize = textFont,
                            color = pureBlack,
                            fontFamily = mFont.nkbold,
                        ),
                        modifier = Modifier
                            .padding(top = top)
                            .fillMaxWidth(paramnamewidth),
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(gap))

                    Row (verticalAlignment = Alignment.CenterVertically){
                        // Assigning the remembered enterValue to the item's param_value
                        var enterValue by rememberSaveable { mutableStateOf(enterValues.value[index] ?: "") }

                        Row(modifier = Modifier.width(enterValueWidth)){
                            CustomOutlinedTextField(
                                text = enterValue,
                                label =  if(item.min == item.max) {item.min + ""} else {item.min + " - " + item.max},
                                onTextChange = { newValue ->
                                    enterValue = newValue
                                    enterValues.value = enterValues.value.toMutableMap().apply {
                                        this[index] = newValue
                                    }
                                    item.param_value = newValue
                                },
                                color = pureBlack,

                                maxLength = 15,
                                shape = RoundedCornerShape(8.dp),

                            )
                        }
                        Text(
                            text = item.param_unit.toString(),
                            style = TextStyle(
                                fontSize = textFont1,
                                color = pureBlack,
                                fontFamily = mFont.nk,
                            ),
                            modifier = Modifier
                                .padding(top = top, start = unitstart)
                                .width(unitWidth),
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                        /*LaunchedEffect(myComponents.mUiViewModel.clearFields.value) {
                            enterValues.value = enterValues.value.toMutableMap().apply {
                                this[index] = ""

                            }
                            enterValue = ""
                            showLogs("val","val cleared: ACTUAL")
                            myComponents.mainViewModel.dataListSetting.forEach {
                                it.param_value = ""
                            }

                            myComponents.mainViewModel.dataListActual.forEach {
                                it.param_value = ""
                            }

                        }*/
                    }
                }
                Divider(
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
        }
    }

    LaunchedEffect(myComponents.mUiViewModel.clearFields.value) {
        enterValues.value =  mutableMapOf()
//        enterValue = ""

        myComponents.mainViewModel.dataListSetting.forEach {
            it.param_value = ""
        }

        myComponents.mainViewModel.dataListActual.forEach {
            it.param_value = ""
        }

        mainViewModel.showZoomableImage = !mainViewModel.showZoomableImage
        delay(1500)
        mainViewModel.showZoomableImage = !mainViewModel.showZoomableImage

        showLogs("val","val cleared: ACTUAL")
    }
}

