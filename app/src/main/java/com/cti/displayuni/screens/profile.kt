package com.cti.displayuni.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
       /* Spacer(modifier = Modifier.height(8.dp))
        Divider(modifier = Modifier
            .fillMaxHeight(0.01f)
            .width(80.dp),
            color = lightOrange)
*/
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
        ProfileText(name = "Employee Id:  ", value = "E01")
        ProfileText(name = "Name:  ", value = "Josh")
        ProfileText(name = "Date of Birth:  ", value = "23-12-2001")
        ProfileText(name = "E-mail:  ", value = "ravi01@gmail.com")
        ProfileText(name = "Skill:  ", value = "2")
        ProfileText(name = "Mobile Number:  ", value = "736827289")

        Spacer(modifier = Modifier.height(36.dp))

        var passwordVisible by remember { mutableStateOf(false) }
        val password = "amitkumar"

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
                )
            )

            Spacer(modifier = Modifier.width(48.dp))
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