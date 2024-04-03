package com.cti.displayuni.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.myComponents

@Composable
fun ProfileText(name:String,value:String){

    Spacer(modifier = Modifier.height(36.dp))
        Row {
            Text(text = name,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = mFont.nk
                )
            )
            Text(text = value,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.nkbold
                )
            )
        }

}

@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun Profile(){
    Column (modifier = Modifier.padding(top = 56.dp, start = 36.dp)){
    Text(text = "Profile Information",
        style = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = mFont.nkbold
        )
    )
        ProfileText(name = "Employee Id:  ", value = myComponents.mainViewModel.employeeId)
        ProfileText(name = "Name:  ", value = myComponents.mainViewModel.name)
        ProfileText(name = "Date of Birth:  ", value = myComponents.mainViewModel.dob)
        ProfileText(name = "E-mail:  ", value = myComponents.mainViewModel.email)
        ProfileText(name = "Skill:  ", value = myComponents.mainViewModel.skill)
        ProfileText(name = "Mobile Number:  ", value = myComponents.mainViewModel.mobileNum)

        Spacer(modifier = Modifier.height(36.dp))

        var passwordVisible by remember { mutableStateOf(false) }
        val password = myComponents.mainViewModel.password

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Password:  ",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = mFont.nk
                )
            )

            val lockIcon = if (passwordVisible) {
                painterResource(id = R.drawable.ic_lock_open)
            } else {
                painterResource(id = R.drawable.ic_lock_close)
            }

            Text(text = if (passwordVisible) password else "*".repeat(password.length),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.nkbold
                ),
                modifier = Modifier.fillMaxWidth(0.15f)

            )
            Image(
                painter = lockIcon,
                contentDescription =if (passwordVisible) "Unlock" else "Lock",
                modifier = Modifier.clickable {
                    passwordVisible = !passwordVisible // Toggle visibility
                }
            )
        }

    }
}