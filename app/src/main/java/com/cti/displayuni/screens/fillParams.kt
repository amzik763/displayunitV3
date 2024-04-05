package com.cti.displayuni.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.extraLightGrey
import com.cti.displayuni.ui.theme.green
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.ui.theme.red
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.showLogs


var fontSmall = 12.sp
var fontMedium = 18.sp
var fontLarge = 22.sp
var paddingSmall = 4.dp
var paddingMedium = 8.dp
var paddingLarge = 12.dp
var heightSmall = 40.dp
var heightMedium = 60.dp
var heightLarge = 80.dp
var heightinFSmall = 0.06f
var heightinFMedium = 0.073f
var heightinFLarge = 80.dp

@Composable
fun ActualParams() {
    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
        Text(
            text = "Actual Parameters",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontLarge
            )
        )
    }
}

@Composable
fun SettingParams() {
    Column(modifier = Modifier.fillMaxWidth(1f)) {

        Text(
            text = "Process Setting Parameters",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontLarge
            )
        )
    }
}


@Composable
fun ReadingUI(){

        Row{
            Text(
                text = "   Readings   ",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = fontLarge
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
                fontSize = fontMedium
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = total,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontMedium,
                color = darkBlue
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = pass,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontMedium,
                color = green
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = fail,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontMedium,
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
                    fontSize = fontMedium
                )
            )
            Text( text = value,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = fontMedium
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
            .padding(paddingSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //subRow
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
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
                        fontSize = fontMedium,
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
                        fontSize = fontMedium,
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
                        fontSize = fontMedium,
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
                        fontSize = fontMedium,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))

            }

        }



        //Third Element - Column
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingMedium, bottom = paddingMedium)){

            Text(
                text = "First Part Approval",
                textAlign = TextAlign.Center,
                style = TextStyle(fontWeight = FontWeight.Bold,
                    fontSize = fontLarge),
                modifier = Modifier.fillMaxWidth(1f)
            )

            Row (modifier = Modifier.padding(24.dp)){
                //1.actual param list
                ActualParams()

                //2.settings param list
                SettingParams()



            }




        }

        //Fourth Element - Image
        ZoomableImage()

    }

}

@Composable
fun ZoomableImage(){
// Define mutable state variables to keep track of the scale and offset.
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    val url = "https://imageio.forbes.com/specials-images/imageserve/5f962984fe3282ac81f68758/The-Aston-Martin-DBS-Superleggera---/960x0.jpg?format=jpg&width=1440"
    val url2 = "https://wallpapers.com/images/featured/really-cool-cars-pictures-7gub7gjfes26vk0c.jpg"
    val painter = rememberAsyncImagePainter(url)
    val painter2 = rememberAsyncImagePainter(url2)
// Create an Image composable with zooming and panning.
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painter, // Replace 'imagePainter' with your image
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        // Update the scale based on zoom gestures.
                        scale *= zoom

                        // Limit the zoom levels within a certain range (optional).
                        scale = scale.coerceIn(0.5f, 3f)

                        // Update the offset to implement panning when zoomed.
                        offset = if (scale == 1f) Offset(0f, 0f) else offset + pan
                    }
                }
                .graphicsLayer(
                    scaleX = scale, scaleY = scale,
                    translationX = offset.x, translationY = offset.y
                )
        )
    }
}



@Composable
fun FillParam(){

    val conf = LocalConfiguration.current
    val dnsty = conf.densityDpi
     mParameters.dnsty = dnsty



    if (mParameters.dnsty == 320) {

         fontSmall = 10.sp
         fontMedium = 12.sp
         fontLarge = 17.sp
         paddingSmall = 2.dp
         paddingMedium = 4.dp
         paddingLarge = 8.dp
         heightSmall = 34.dp
         heightMedium = 50.dp
         heightLarge = 70.dp
         heightinFSmall = 0.06f
         heightinFMedium = 0.073f
         heightinFLarge = 80.dp
        showLogs("DENSITY","320")

    } else if (mParameters.dnsty == 160) {

        fontSmall = 12.sp
        fontMedium = 18.sp
        fontLarge = 22.sp
        paddingSmall = 4.dp
        paddingMedium = 8.dp
        paddingLarge = 12.dp
        heightSmall = 40.dp
        heightMedium = 60.dp
        heightLarge = 80.dp
        heightinFSmall = 0.06f
        heightinFMedium = 0.073f
        heightinFLarge = 80.dp
        showLogs("DENSITY","160")


    }

    Header()
}

@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun PreviewUi(){
    FillParam()
}