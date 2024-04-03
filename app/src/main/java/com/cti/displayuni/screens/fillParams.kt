package com.cti.displayuni.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.extraLightGrey
import com.cti.displayuni.ui.theme.green
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.ui.theme.red


@Composable
fun ReadingUI(){

        Row{
            Text(
                text = "Readings   ",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )
            ReadingCircles("r1")
            ReadingCircles("r2")
            ReadingCircles("r3")
            ReadingCircles("r4")
            ReadingCircles("r5")
        }

}

@Composable
fun ReadingCircles(r: String) {
    Image(painter = painterResource(id = R.drawable.circle),
        contentDescription ="Circle1",
        colorFilter = ColorFilter.tint(lightGrey)
        /*if(r.length==0||r.isNullOrEmpty()){
            ColorFilter.tint(lightGrey)

        }else{
            ColorFilter.tint(green)

        }*/
    )
    Spacer(modifier = Modifier.width(12.dp))
}

@Composable
fun CheckingParts(checking:String,total:String,pass:String,fail:String){
    Row {
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = checking,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = total,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = darkBlue
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = pass,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = green
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = fail,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = red
            )
        )
    }
}
@Composable
fun MyImageButton(icon: Int, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "ImageButton"
        )
    }
}
@Composable
fun OrangeText(
    name: String,
    value:String
){
    Column(verticalArrangement = Arrangement.SpaceAround) {
        Row {
            Text( text = name,
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
            Text( text = value,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Composable
fun Header(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
            ) {

        //First Row
        Row(modifier = Modifier
            .background(color = extraLightGrey)
            .fillMaxWidth()
            .fillMaxHeight(0.06f)
            .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //subRow
            Row() {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    OrangeText(name = "Process Name: ", value = "not found")
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .width(65.dp)
                            .height(4.dp)
                            .background(color = lightOrange)
                    ) {

                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    OrangeText(name = "Part Name: ", value = "not found")
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .width(65.dp)
                            .height(4.dp)
                            .background(color = lightOrange)
                    ) {

                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                //Shift Timings
                Text(
                    text = "shiftValue 10:00 AM to 04:00 PM",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = orange
                    )
                )
            }
            //secondSubRow
            Row {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(9.dp),
                    border = BorderStroke(3.dp, darkBlue),
                    colors = ButtonDefaults.buttonColors(contentColor = pureWhite, containerColor =  darkBlue),

                ) {
                    Text(
                        text = "First Part Approval",
                        fontSize = 17.sp,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }

                Spacer(Modifier.width(36.dp))
                MyImageButton(R.drawable.ic_account) {
                    //functionality
                }

                MyImageButton(R.drawable.ic_logout) {
                    //functionality
                }
            }
        }
        //Second Row
        Row(modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .fillMaxHeight(0.073f)
            .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CheckingParts(checking = "Checking: ${4}", total = "Total: ${29}", pass = "Pass: ${5}", fail = "Fail: ${5}")
            ReadingUI()
            OutlinedTextField(
                value = "value",
                label = {
                        Box(modifier = Modifier
                            .background(Color.White)
                            .padding(start = 3.dp, end = 3.dp)){
                            Text(text = "Part ID")

                        }
                        },
                onValueChange = {

                                },
                modifier = Modifier
                    .width(175.dp)
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = darkBlue,
                    unfocusedIndicatorColor = lightGrey,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            //Pass fail buttons
            Row {

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(29.dp),
                    border = BorderStroke(3.dp, green),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = pureWhite,
                        containerColor = green
                    ),

                    ) {
                    Text(
                        text = "PASS",
                        fontSize = 17.sp,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(29.dp),
                    border = BorderStroke(3.dp, red),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = pureWhite,
                        containerColor = red
                    ),

                    ) {
                    Text(
                        text = "FAIL",
                        fontSize = 17.sp,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))

            }

        }
    }

}


@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun PreviewUi(){
    Header()
}