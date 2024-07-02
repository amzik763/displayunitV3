package com.cti.displayuni.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.PROFILE
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mFont.nkbold
import com.cti.displayuni.utility.mFont.poppinsbold
import com.cti.displayuni.utility.mFont.poppinsregular
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents

@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GetTask() {
    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp

    val currentDateTime = myComponents.mUiViewModel.currentDateTime.observeAsState("")

    val dnsty = conf.densityDpi

    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables
    var mainHeaderFont = 58.sp
    var semiHeaderFont = 36.sp
    var textFont1 = 18.sp
    var textFont2 = 18.sp
    var textFont3 = 18.sp
    var maxWidth = widthDP/3.5f
    var startPadding = 24.dp
    var width = 180.dp
    var padding = 24.dp
    var height = 40.dp
    var spacerHeight = 56.dp
    var imgSize = 50.dp
    var interfaceW = 300.dp
    var interfaceH = 70.dp
    var start = 36.dp
    var top = 48.dp
    var bottom = 36.dp
    var btnpadding = 9.dp
    var sWidth = 16.dp

    Log.d("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {
        maxWidth = widthDP/4.3f
        startPadding = 16.dp
        mainHeaderFont = 32.sp
        semiHeaderFont = 20.sp
        textFont1 = 12.sp
        textFont3 = 14.sp
        textFont2 = 11.sp
        padding = 14.dp
        width = 120.dp
        height = 30.dp
        imgSize = 26.dp
        spacerHeight = 20.dp
        interfaceW = 180.dp
        interfaceH = 50.dp
        start = 28.dp
        top = 36.dp
        bottom = 28.dp
        btnpadding = 6.dp
        sWidth = 12.dp

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {
        maxWidth = widthDP/3.5f
        startPadding = 36.dp
        mainHeaderFont = 56.sp
        semiHeaderFont = 36.sp
        textFont1 = 24.sp
        textFont2 = 20.sp
        textFont3 = 18.sp
        padding = 24.dp
        width = 210.dp
        height = 50.dp
        imgSize = 50.dp
        spacerHeight = 56.dp
        interfaceW = 180.dp
        interfaceH = 50.dp
        start = 36.dp
        top = 48.dp
        bottom = 36.dp
        btnpadding = 9.dp
        sWidth = 16.dp

        Log.d("Desktop: ", wd.toString())
    }

    Row{
      Box(modifier = Modifier.width(maxWidth)){
      Image(painter = painterResource(id = R.drawable.bg_background),
          contentDescription = "Blue Background",
          contentScale = ContentScale.Crop,
      )
      Column(modifier = Modifier
          .fillMaxHeight()
          .fillMaxSize()
          .padding(start = start, top = top, bottom = bottom),
          verticalArrangement = Arrangement.SpaceBetween){

       Column {
           Image(painter = painterResource(id = R.drawable.interfacelogo),
               contentDescription = "Interface Logo",
               modifier = Modifier.size(interfaceW, interfaceH),
           )
       }
          Text(
              text = "Developed by Cellus Tech India",
              style = TextStyle(fontSize = textFont3,
                  fontWeight = FontWeight.Bold,
                  color = pureWhite,
                  fontFamily = mFont.nk
              )
          )
      } }

      Column(verticalArrangement = Arrangement.SpaceBetween){
          Box(modifier = Modifier
              .background(color = lightGrey)
              .padding(padding)){
          Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
          ) {
              Row {
                  Text(
                      text = "Name: ",
                      style = TextStyle(
                          fontSize = textFont2,
                          color = pureBlack,
                          fontFamily = poppinsregular
                      )
                  )
                  Text(
                      text = myComponents.mainViewModel.name,
                      style = TextStyle(
                          fontSize = textFont2,
                          color = pureBlack,
                          fontWeight = FontWeight.Bold,
                          fontFamily = poppinsbold
                      )
                  )
              }

              Row {
                  Text(
                      text = "Skill: ",
                      style = TextStyle(
                          fontSize = textFont2,
                          color = pureBlack,
                          fontFamily = poppinsregular
                      )
                  )
                  Text(
                      text = myComponents.mainViewModel.skill,
                      style = TextStyle(
                          fontSize = textFont2,
                          color = pureBlack,
                          fontWeight = FontWeight.Bold,
                          fontFamily = poppinsbold
                      )
                  )

                  Spacer(modifier = Modifier.width(sWidth))
                  Skills()
              }

              Row {
                  Text(
                      text = "Device Id: ",
                      style = TextStyle(
                          fontSize = textFont2,
                          color = pureBlack,
                          fontFamily = poppinsregular
                      )
                  )

                  Text(
                      text = myComponents.mainViewModel.deviceId,
                      style = TextStyle(
                          fontSize = textFont2,
                          color = pureBlack,
                          fontWeight = FontWeight.Bold,
                          fontFamily = poppinsbold
                      )
                  )
              }
              Row {
                  Text(
                      text = "Employee Id: ",
                      style = TextStyle(
                          fontSize = textFont2,
                          color = pureBlack,
                          fontFamily = poppinsregular
                      )
                  )
                  Text(
                      text = myComponents.mainViewModel.employeeId,
                      style = TextStyle(
                          fontSize = textFont2,
                          color = pureBlack,
                          fontWeight = FontWeight.Bold,
                          fontFamily = poppinsbold
                      )
                  )
              }
              Text(
                  text = currentDateTime.value,
                  style = TextStyle(
                      fontSize = textFont2,
                      color = pureBlack,
                      fontFamily = poppinsregular
                  )
              )

              Row(modifier = Modifier.fillMaxWidth(0.27f),
                  horizontalArrangement = Arrangement.SpaceBetween)
              {

                  Image(
                      painter = painterResource(id = R.drawable.ic_account),
                      contentDescription = "Account",
                      modifier = Modifier
                          .size(imgSize)
                          .clickable {
                              myComponents.navController.navigate(PROFILE)
                          }
                  )

              }
          }
      }
          Column(modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
          ) {
              Text(text = "Welcome To",
                  style = TextStyle(fontSize = mainHeaderFont,
                      fontWeight = FontWeight.Bold,
                      color = pureBlack,
                      fontFamily = nkbold)
              )
              Row {
                  Text(text = "INTERFACE",
                      style = TextStyle(fontSize = mainHeaderFont,
                          fontWeight = FontWeight.Bold,
                          color = orange,
                          fontFamily = poppinsbold)
                  )

              }
              Spacer(modifier = Modifier.height(spacerHeight))
              Surface(
                  modifier = Modifier
                      .padding(top = 16.dp)
                      .size(width = width, height = height)
                      .clickable {
                          myComponents.mainViewModel.getTask(myComponents.mainViewModel.getStationValue())

                      },
                  color = darkBlue,
                  shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                  border = BorderStroke(width = 1.dp, color = darkBlue)
              ) {
                  Text(
                      text = AnnotatedString("Get Task"),
                      style = TextStyle(
                          color = pureWhite,
                          fontSize = textFont1,
                          fontFamily = poppinsregular,
                          fontWeight = FontWeight.Bold,
                          textAlign = TextAlign.Center
                      ),
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(btnpadding)
                          .align(Alignment.CenterHorizontally),

                  )
              }
          }
      }
    }
}
