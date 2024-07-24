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
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.cti.displayuni.response.getValueForKey
import com.cti.displayuni.ui.theme.dimens
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.CircularProgressBar
import com.cti.displayuni.utility.ProgressTimer
import com.cti.displayuni.utility.mFont.nk
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import kotlin.math.roundToLong

// Not resetting okm button but send notification everytime
/*@Composable
fun DropDown(
    paramId: String,
    index: Int,
    notificationIDState: String,
    progressTimer: ProgressTimer
) {
    Log.d("abc", myComponents.mainViewModel.checkSheetList.size.toString())

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("status") }
    val items = listOf("OK", "NG")

    val progressState by progressTimer.getProgress(paramId).collectAsState()
    val value by progressTimer.getCountdownProgress(paramId).collectAsState()
    val timerCompleted = progressState == 0f
    val timerStarted = progressTimer.hasTimerStarted(paramId)

    var timerStartedAt by rememberSaveable { mutableStateOf<Long?>(null) }
    val currentTime = System.currentTimeMillis()

    LaunchedEffect(selectedItem) {
        selectedItem = myComponents.mainViewModel.checkSheetList[index]
    }

    // Effect to disable OK 10 seconds after timer starts
    LaunchedEffect(timerStartedAt) {
        timerStartedAt?.let { startTime ->
            val elapsed = System.currentTimeMillis() - startTime
            if (elapsed < 10_000) {
                delay(10_000 - elapsed)
            }
            // Mark the timer as completed after 10 seconds
            if (selectedItem == "NG") {
                progressTimer.stopTimer(paramId)
                // Notify after the timer completes
                myComponents.mainViewModel.notify(
                    myComponents.mainViewModel.getStationValue(),
                    paramId,
                    myComponents.mainViewModel.getStationValue().split(" ").take(2).joinToString(" ")
                ) { result ->
                    result.onSuccess { notificationId ->
                        println("Notification ID: $notificationId")
                        myComponents.mainViewModel.myChecksheetNotificationMap[paramId] = notificationId
                    }.onFailure { exception ->
                        println("Notification failed: ${exception.message}")
                    }
                }
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth(MaterialTheme.dimens.dropdownMaxW)
            ) {
                Text(selectedItem)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                items.forEach { item ->
                    val isOkDisabled = item == "OK" && timerStartedAt != null && currentTime - timerStartedAt!! >= 10_000
                    DropdownMenuItem(
                        onClick = {
                            if (!isOkDisabled) {
                                selectedItem = item
                                if (selectedItem == "NG") {
                                    timerStartedAt = System.currentTimeMillis()
                                    progressTimer.startTimer(paramId) {
                                        showLogs("Timer finished for paramId:", paramId)
                                    }
                                    myComponents.mainViewModel.checkSheetList[index] = item
                                } else if (selectedItem == "OK") {
                                    timerStartedAt = null
                                    progressTimer.stopTimer(paramId)
                                    myComponents.mainViewModel.checkSheetList[index] = item
                                }
                                expanded = false
                            }
                        },
                        text = {
                            Text(
                                text = item,
                                style = TextStyle(
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    color = if (isOkDisabled) Color.Gray else pureBlack,
                                    textAlign = TextAlign.Center
                                )
                            )
                        },
                        enabled = !isOkDisabled
                    )
                }
            }
        }

        Text(
            modifier = Modifier
                .width(70.dp)
                .padding(start = MaterialTheme.dimens.startPadding),
            text = notificationIDState ?: "00",
            color = pureBlack,
            fontFamily = nk,
            textAlign = TextAlign.Start,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
        )

        val temp = myComponents.mainViewModel.dataStatus.value?.let {
            getValueForKey(
                it, myComponents.mainViewModel.myChecksheetNotificationMap[paramId].toString()
            )
        }

        if (temp == true) {
            myComponents.mainViewModel.checkSheetList[index] = "SUP_OK"
            selectedItem = "SUP_OK"
            showLogs("STOPPING", "timer")
            progressTimer.stopTimer(paramId)
        }

        IconButton(
            modifier = Modifier.padding(start = MaterialTheme.dimens.startPadding),
            onClick = {
                myComponents.mainViewModel.getCheckSheetStatusBack(myComponents.mainViewModel.myChecksheetNotificationMap[paramId]) { result ->
                    result.onSuccess {
                        if (it == "true") {
                            showLogs("NO NOTE: ", "true")
                            myComponents.mainViewModel.checkSheetList[index] = "SUP_OK"
                            selectedItem = "SUP_OK"

                        } else {
                            showLogs("NO NOTE: ", "false")
                            myComponents.mainViewModel.checkSheetList[index] = "NG"

                            selectedItem = "NOT_OK"
                        }
                    }.onFailure {
                        //show dialouge error
                        showLogs("NONOTE: ", "failed")
                        selectedItem = "failed"
                    }
                }
            },
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                modifier = Modifier.size(MaterialTheme.dimens.logoSize),
                contentDescription = "Refresh"
            )
        }

        Spacer(modifier = Modifier.width(MaterialTheme.dimens.topPadding))

        if (progressState > 0) {
            CircularProgressBar(
                percentage = progressState,
                value = value.toString(),
                duration = (progressState.roundToInt() * 10_000).toInt() / 1000,
                onTimeEnd = {
                    // Handle completion actions here if needed
                }
            )
        }
    }
}*/

@Composable
fun DropDown(paramId: String, index: Int,notificationIDState:String,
             progressTimer: ProgressTimer
) {
    Log.d("abc", myComponents.mainViewModel.checkSheetList.size.toString())

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("status") }
    val items = listOf("OK", "NG")

    val progressState by progressTimer.getProgress(paramId).collectAsState()
    val value by progressTimer.getCountdownProgress(paramId).collectAsState()
    val timerCompleted = progressState == 0f
    val timerStarted = progressTimer.hasTimerStarted(paramId)

    var timerStartedAt by remember { mutableStateOf<Long?>(null) }
    val currentTime = System.currentTimeMillis()

    LaunchedEffect(selectedItem) {
        selectedItem = myComponents.mainViewModel.checkSheetList[index]
    }

    // Effect to disable OK 10 seconds after timer starts
    LaunchedEffect(timerStartedAt) {
        timerStartedAt?.let { startTime ->
            val elapsed = System.currentTimeMillis() - startTime
            if (elapsed < 10_000) {
                delay(10_000 - elapsed)
            }
            // Mark the timer as completed after 10 seconds
            if (selectedItem == "NG") {
                progressTimer.stopTimer(paramId)
                // Notify after the timer completes
                myComponents.mainViewModel.notify(
                    myComponents.mainViewModel.getStationValue(),
                    paramId,
                    myComponents.mainViewModel.getStationValue().split(" ").take(2).joinToString(" ")
                ) { result ->
                    result.onSuccess { notificationId ->
                        println("Notification ID: $notificationId")
                        myComponents.mainViewModel.myChecksheetNotificationMap[paramId] = notificationId
                    }.onFailure { exception ->
                        println("Notification failed: ${exception.message}")
                    }
                }
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth(MaterialTheme.dimens.dropdownMaxW)
            ) {
                Text(selectedItem)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                items.forEach { item ->
                    val isOkDisabled = item == "OK" && timerStartedAt != null && currentTime - timerStartedAt!! >= 10_000
                    DropdownMenuItem(
                        onClick = {
                            if (!isOkDisabled) {
                                selectedItem = item
                                if (selectedItem == "NG") {
                                    timerStartedAt = System.currentTimeMillis()
                                    progressTimer.startTimer(paramId) {
                                        showLogs("Timer finished for paramId:", paramId)
                                    }
                                    myComponents.mainViewModel.checkSheetList[index] = item
                                } else if (selectedItem == "OK") {
                                    timerStartedAt = null
                                    progressTimer.stopTimer(paramId)
                                    myComponents.mainViewModel.checkSheetList[index] = item
                                }
                                expanded = false
                            }
                        },
                        text = {
                            Text(
                                text = item,
                                style = TextStyle(
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    color = if (isOkDisabled) Color.Gray else pureBlack,
                                    textAlign = TextAlign.Center
                                )
                            )
                        },
                        enabled = !isOkDisabled
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

        val temp = myComponents.mainViewModel.dataStatus.value?.let {
            getValueForKey(
                it, myComponents.mainViewModel.myChecksheetNotificationMap[paramId].toString()
            )
        }

        if (temp == true) {
            myComponents.mainViewModel.checkSheetList[index] = "SUP_OK"
            selectedItem = "SUP_OK"
            showLogs("STOPPING","timer")
            progressTimer.stopTimer(paramId)
        }

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

        // Display CircularProgressBar if progressState is greater than 0

        Spacer(modifier = Modifier.width(MaterialTheme.dimens.topPadding))

        if (progressState > 0) {
            CircularProgressBar(
                percentage = progressState,
                value = value.toString(),
                duration = (progressState.roundToInt() * 10_000).toInt() / 1000,
                onTimeEnd = {
                    // Handle completion actions here if needed
                }
            )
        }
    }
}
