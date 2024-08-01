package com.cti.displayuni.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cti.displayuni.ui.theme.dimens

@Composable
fun ChartDropDown(){
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Select") }
    val items = listOf("item1" , "item2", "AIR PRESSURE - 0 - 2 kgf/M2")

    Column(
        modifier = Modifier
            .padding(MaterialTheme.dimens.headerPadding),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .padding(start = MaterialTheme.dimens.padding)
                .width(400.dp)
                .wrapContentSize(Alignment.TopStart)
        ) {

            Row(
                modifier = Modifier
                    .padding(MaterialTheme.dimens.headerPadding)
                    .clickable { expanded = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedItem,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(30.dp))
                Icon(
                    imageVector =  if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }

            DropdownMenu(expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                items.forEach{label ->
                    DropdownMenuItem(text = { Text(text = label) }, onClick = {
                        selectedItem = label
                        expanded = false }
                    )
                }
            }
        }
    }
}
