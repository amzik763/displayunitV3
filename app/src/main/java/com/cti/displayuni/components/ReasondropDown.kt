package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents

@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun ReasonDropdown() {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Select Reason") }
//  val items = listOf("1", "2", "3", "4", "5")
    val mItems by myComponents.mainViewModel.mReasonList.observeAsState()
    val items = mItems?.reasons
    val conf = LocalConfiguration.current
    val widthdp = conf.screenWidthDp.dp
    val dnsty = conf.densityDpi

    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables

    var textFont1 = 18.sp
    var width = 180.dp
    var height = 48.dp

    Log.d("dwinsize: ", wd.toString())
    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        textFont1 = 12.sp
        width = 180.dp
        height = 48.dp
        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        textFont1 = 16.sp
        width = 230.dp
        height = 48.dp
        Log.d("Desktop: ", wd.toString())
    }

    Column {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier
                .padding(top = 8.dp)
                .size(width = width, height = height)
                .border(1.dp, color = orange, shape = RoundedCornerShape(34.dp))
        ) {
            Text(selectedItem) // Display the selected item
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items?.forEach { item ->
                DropdownMenuItem(
                    { Text(text = item.reason,
                        style = TextStyle(
                            fontSize = textFont1,
                            color = pureBlack,
                            textAlign = TextAlign.Center
                        )
                    ) },
                    onClick = {
                        selectedItem = item.reason_id.toString()
                        myComponents.mainViewModel.mSelectedReason = selectedItem
                        expanded = false
                    })
            }
        }
    }
}