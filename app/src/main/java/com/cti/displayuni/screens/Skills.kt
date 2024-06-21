package com.cti.displayuni.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cti.displayuni.ui.theme.green
import com.cti.displayuni.ui.theme.sOrange
import com.cti.displayuni.ui.theme.sRed
import com.cti.displayuni.ui.theme.yellow
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs

@Preview
@Composable
fun Skills(){
    Row(modifier = Modifier.padding(top = 0.dp),
        verticalAlignment = Alignment.CenterVertically) {
        if (myComponents.mainViewModel.skill >= "1"){
            showLogs("Skill 1", myComponents.mainViewModel.skill)
            Box (modifier = Modifier.size(20.dp)
                .background(color = sRed)){}
        }

        if (myComponents.mainViewModel.skill >= "2") {
            showLogs("Skill 2", myComponents.mainViewModel.skill)

            Box(modifier = Modifier.size(20.dp)
                    .background(color = sOrange)
            ) {}
        }

        if (myComponents.mainViewModel.skill >= "3") {
            showLogs("Skill 3", myComponents.mainViewModel.skill)

            Box(
                modifier = Modifier.size(20.dp)
                    .background(color = yellow)
            ) {}
        }

        if (myComponents.mainViewModel.skill >= "4") {
            showLogs("Skill 4", myComponents.mainViewModel.skill)

            Box(
                modifier = Modifier.size(20.dp)
                    .background(color = green)
            ) {}
        }
    }
}