package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.myComponents

@Composable
fun DropDown(paramId: String) {
    Log.d("abcccc",myComponents.mainViewModel.checkSheetList.size.toString())

    var expanded by remember { mutableStateOf(false) }
//    val selectedItem by rememberUpdatedState("Status")
    var selectedItem by remember { mutableStateOf("Status") }
    val items = listOf("OK", "NG", "SUP OK")

    LaunchedEffect(selectedItem) {
        // Use LaunchedEffect to update selectedItem after recomposition
        selectedItem = myComponents.mainViewModel.checkSheetList[Integer.parseInt(paramId) - 1]
    }

    Column {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentSize()
        ) {
            Text(selectedItem) // Display the selected item
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    { Text(text = item,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = pureBlack,
                            textAlign = TextAlign.Center
                        )
                    ) },
                    onClick = {
                    selectedItem = item
                    myComponents.mainViewModel.checkSheetList.set(Integer.parseInt(paramId) -1,item)
                        myComponents.mainViewModel.checkSheetList.forEach { println(it) }
                    expanded = false
                })
            }
        }
    }
}


