package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.dimens
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.Actual_Param
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.showLogs
import kotlinx.coroutines.delay

@Composable
fun ActualLazyList(
    dataListActual: MutableList<Actual_Param>
) {

    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp
    val dnsty = conf.densityDpi

    Log.d("mdpi density: ", dnsty.toString())

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

    mParameters.dnsty = dnsty
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

    val enterValues = rememberSaveable { mutableStateOf(mutableMapOf<Int, String>()) }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(dataListActual) { index,item ->

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = paddingEndSmall),
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
                        modifier = Modifier.padding(top = top)
                            .fillMaxWidth(paramnamewidth),
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(gap))
                    Row (verticalAlignment = Alignment.CenterVertically){
                        // Assigning the remembered enterValue to the item's param_value
                        var enterValue by rememberSaveable { mutableStateOf(enterValues.value[index] ?: "") }

                        if (item.min == "OK" || item.min == "NG" || item.min == "A" || item.min == "B") {
                            // Render DropDownOK when the label is OK, NG, A, B

                            Row(modifier = Modifier.width(enterValueWidth)) {

                                // Ensure min and max are non-null by providing a fallback value, e.g., empty string
                                val range = Pair(item.min ?: "", item.max ?: "")

                                enterValue.ifEmpty {
                                    if (item.min == item.max) item.min else "${item.min} - ${item.max}"
                                }?.let {
                                    DropDownActual(
                                        initialSelection = it,
                                        itemsRange = range,
                                        onItemSelected = { selected ->
                                            enterValue = selected
                                            enterValues.value = enterValues.value.toMutableMap().apply {
                                                this[index] = selected
                                            }

                                            item.param_value = selected
                                            showLogs("SELECTED ITEM", selected)
                                        }
                                    )
                                }
                            }
                        }

                        else {
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
                                    shape = RoundedCornerShape(8.dp)
                                )
                            }
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
                    }
                }
                Divider(modifier = Modifier.padding(top = top))
            }
        }
    }

    LaunchedEffect(myComponents.mUiViewModel.clearFields.value) {
        enterValues.value =  mutableMapOf()
//        enterValue = ""

        mainViewModel.dataListSetting.forEach {
            it.param_value = ""
        }

        mainViewModel.dataListActual.forEach {
            it.param_value = ""
        }
        mainViewModel.showZoomableImage = !mainViewModel.showZoomableImage
        delay(1500)
        mainViewModel.showZoomableImage = !mainViewModel.showZoomableImage
        showLogs("val","val cleared: ACTUAL")
    }
}


@Composable
fun DropDownActual(
    initialSelection: String, // This will show the min-max range initially
    itemsRange: Pair<String, String>,
    onItemSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by rememberSaveable { mutableStateOf(initialSelection) }

    var isItemSelected by rememberSaveable { mutableStateOf(false) }

    // Dynamically determine the items list based on the range
    val items = listOf(itemsRange.first, itemsRange.second)


    Column(
        modifier = Modifier.padding(0.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .padding(MaterialTheme.dimens.smallPadding)
                .border(width = if (isItemSelected) 2.dp else 1.dp,
                    color = if (isItemSelected) orange else Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .width(MaterialTheme.dimens.smallTextField)
//                .wrapContentSize(Alignment.TopStart)
        ) {
            Row(
                modifier = Modifier
                    .padding(MaterialTheme.dimens.headerPadding)
                    .clickable { expanded = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedItem,
                    fontSize = 14.sp,
                    color = if (isItemSelected) pureBlack else Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                items.forEach { item ->
                    DropdownMenuItem(text = { Text(text = item) }, onClick = {
                        selectedItem = item
                        isItemSelected = true // Set to true after an item is selected
                        onItemSelected(item)
                        expanded = false
                    })
                }
            }
        }
    }
}

