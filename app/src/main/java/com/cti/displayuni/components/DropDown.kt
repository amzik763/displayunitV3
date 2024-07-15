package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.response.CheckSheetData
import com.cti.displayuni.ui.theme.dimens
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.CircularProgressBar
import com.cti.displayuni.utility.ProgressTimer
import com.cti.displayuni.utility.mFont.nk
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs

@Composable
fun DropDown(paramId: String, index: Int,notificationIDState:String, /*progressState: SnapshotStateMap<String, Boolean>*/
             progressTimer: ProgressTimer
) {
    Log.d("abc", myComponents.mainViewModel.checkSheetList.size.toString())

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("status") }
    val items = listOf("OK", "NG")
    val showProgressBar =  remember { mutableStateOf(false) }


    LaunchedEffect(selectedItem) {
        selectedItem = myComponents.mainViewModel.checkSheetList[index]
    }
    Row  (modifier = Modifier.fillMaxWidth(MaterialTheme.dimens.dropdownRow),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically) {

        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .fillMaxWidth(MaterialTheme.dimens.dropdownMaxW)
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
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    color = pureBlack,
                                    textAlign = TextAlign.Center
                                )
                            )
                        },
                        onClick = {
                            selectedItem = item
                            if (selectedItem == "NG") {

//                                progressState[paramId] = true

                                progressTimer.startTimer(paramId) {
                                    // Timer finished callback
                                    showLogs("Timer finished for paramId:", paramId)
                                }

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
//                                progressState[paramId] = false

                                progressTimer.stopTimer(paramId)
                                myComponents.mainViewModel.checkSheetList.set(index, item)
                                myComponents.mainViewModel.checkSheetList.forEach { println(it) }
                                expanded = false
                            }
                        }
                    )
                }
            }
        }

        Text(
            modifier = Modifier
                .width(70.dp)
                .padding(start = MaterialTheme.dimens.startPadding),
            text = notificationIDState?:"00" ,
            color = pureBlack,
            fontFamily = nk,
            textAlign = TextAlign.Start,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
        )




        IconButton(modifier = Modifier
            .padding(start = MaterialTheme.dimens.startPadding), onClick = {
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
        },
            ) {
            Icon(imageVector = Icons.Default.Refresh,
                modifier = Modifier.size(MaterialTheme.dimens.logoSize),
                contentDescription = "Refresh"
            )

        }

/*        // Conditionally show the CircularProgressBar
        if (progressState[paramId] == true
//            myComponents.mUiViewModel.showProgressBar.value
            ) {
            CircularProgressBar(percentage = 1f, duration = 10,
                    onTimeEnd = {
                        progressState[paramId] = false
                    }
            )
        }*/

    }
}
