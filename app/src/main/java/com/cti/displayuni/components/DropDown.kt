package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs

@Composable
fun DropDown(paramId: String, index: Int) {
    Log.d("abc", myComponents.mainViewModel.checkSheetList.size.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont1 = 16.sp
    var imgSize = 30.dp
    var maxWidth = 0.6f

    Log.d("dwinsize: ", wd.toString())

    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        textFont1 = 10.sp
        imgSize = 16.dp
        maxWidth = 0.412f

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont1 = 16.sp
        imgSize = 30.dp
        maxWidth = 0.6f

        Log.d("Desktop: ", wd.toString())
    }

    var expanded by remember { mutableStateOf(false) }
//  val selectedItem by rememberUpdatedState("Status")
    var selectedItem by remember { mutableStateOf("status") }
    val items = listOf("OK", "NG")
//    val items = listOf("OK", "NG", "SUP_OK")

    LaunchedEffect(selectedItem) {
        selectedItem = myComponents.mainViewModel.checkSheetList[index]
    }
    Row(verticalAlignment = Alignment.CenterVertically) {

        Column(verticalArrangement = Arrangement.Center) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .fillMaxWidth(maxWidth)
                    .wrapContentSize()
            ) {
                Text(selectedItem)
                //Display the selected item
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        {
                            Text(
                                text = item,
                                style = TextStyle(
                                    fontSize = textFont1,
                                    color = pureBlack,
                                    textAlign = TextAlign.Center
                                )
                            )
                        },
                        onClick = {
                            selectedItem = item
                            if (selectedItem == "NG") {

                                myComponents.mainViewModel.checkSheetList.set(index, item)
                                myComponents.mainViewModel.checkSheetList.forEach { println(it) }
                                myComponents.mainViewModel.notify(
                                    myComponents.mainViewModel.getStationValue(),
                                    paramId,
//                        myComponents.mainViewModel.floorNum
                                    myComponents.mainViewModel.getStationValue().split(" ").take(2)
                                        .joinToString(" ")
                                ) { result ->
                                    result.onSuccess { notificationId ->
                                        // Handle success, for example:
                                        println("Notification ID ID: $notificationId")
                                        myComponents.mainViewModel.myChecksheetNotificationMap[paramId] =
                                            notificationId
                                        println("Notification ID ID: ${myComponents.mainViewModel.mChecksheetData.value}")

                                    }.onFailure { exception ->
                                        // Handle failure, for example:
                                        println("Notification failed: ${exception.message}")
                                        // Update UI or state here
                                    }
                                }
                                expanded = false


                            } else if (selectedItem == "OK") {
                                myComponents.mainViewModel.checkSheetList.set(index, item)
                                myComponents.mainViewModel.checkSheetList.forEach { println(it) }
                                expanded = false
                            }
                        }
                    )
                }
            }
        }

        IconButton(onClick = {
                myComponents.mainViewModel.getCheckSheetStatusBack(myComponents.mainViewModel.myChecksheetNotificationMap[paramId]){ result ->
                    result.onSuccess {
                            if(it == "true"){
                                showLogs("NO NOTE: ","true")
                                myComponents.mainViewModel.checkSheetList[index] = "SUP_OK"
                                selectedItem = "SUP_OK"

                            }else{
                                showLogs("NO NOTE: ","false")
                                myComponents.mainViewModel.checkSheetList[index] = "NG"

                                selectedItem = "NOT_OK"
                            }
                    }.onFailure {
                        //show dialouge error
                        showLogs("NONOTE: ","failed")
                        selectedItem = "failed"
                    }
                }
        }) {
            Icon(imageVector = Icons.Default.Refresh,
                modifier = Modifier.size(imgSize),
                contentDescription = "Refresh")
        }
    }
}
