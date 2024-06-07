package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs

@Composable
fun DropDown(paramId: String, index: Int) {
    Log.d("abc", myComponents.mainViewModel.checkSheetList.size.toString())

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
                    .fillMaxWidth(0.65f)
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
                                    fontSize = 16.sp,
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
                                showLogs("NONOTE: ","true")
                                selectedItem = "SUP_OK"
                            }else{
                                showLogs("NONOTE: ","false")
                                selectedItem = "NOT_OK"

                            }
                    }.onFailure {
                        //show dialouge error
                        showLogs("NONOTE: ","failed")
                        selectedItem = "failed"
                    }

                }
        }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
        }
    }
}