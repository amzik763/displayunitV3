package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DropDown(paramId: String) {
    Log.d("abc",myComponents.mainViewModel.checkSheetList.size.toString())

    var expanded by remember { mutableStateOf(false) }
//  val selectedItem by rememberUpdatedState("Status")
    var selectedItem by remember { mutableStateOf("Status") }
    val items = listOf("OK", "NG", "SUP_OK")

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
                    { Text(text = item,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = pureBlack,
                            textAlign = TextAlign.Center
                        )
                    ) },
                    onClick = {
                            selectedItem = item
                            myComponents.mainViewModel.checkSheetList.set(Integer.parseInt(paramId) - 1, item)
                            myComponents.mainViewModel.checkSheetList.forEach { println(it) }
                            expanded = false
                    }
                )
            }
        }
    }
}