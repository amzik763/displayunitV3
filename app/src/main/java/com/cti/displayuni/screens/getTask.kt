package com.cti.displayuni.screens

import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
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
import coil.compose.rememberAsyncImagePainter
import com.cti.displayuni.R
import com.cti.displayuni.components.CustomRoundedButton
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.dimens
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.PROFILE
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mFont.nk
import com.cti.displayuni.utility.mFont.nkbold
import com.cti.displayuni.utility.mFont.poppinsbold
import com.cti.displayuni.utility.mFont.poppinsregular
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.mainViewModel

@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GetTask() {


    // Decode the Base64 string into a byte array
    val imageBytes = remember { Base64.decode(myComponents.mainViewModel.profilePic, Base64.DEFAULT) }

    // Convert the byte array into a Bitmap
    val bitmap = remember { BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size) }

    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp

    val currentDateTime = myComponents.mUiViewModel.currentDateTime.observeAsState("")

    val dnsty = conf.densityDpi

    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx

    var maxWidth = widthDP/3.5f
    var spacerHeight = 56.dp
    var sWidth = 16.dp

    Log.d("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {
        maxWidth = widthDP/4.3f
        spacerHeight = 20.dp
        sWidth = 12.dp

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {
        maxWidth = widthDP/3.5f
        spacerHeight = 56.dp
        sWidth = 16.dp

        Log.d("Desktop: ", wd.toString())
    }

    Row{
        Box(modifier = Modifier.width(maxWidth))
        {
              Image(painter = painterResource(id = R.drawable.bg_background),
                  contentDescription = "Blue Background",
                  contentScale = ContentScale.Crop,
              )

            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize()
                .padding(MaterialTheme.dimens.padding),
              verticalArrangement = Arrangement.SpaceBetween)
            {

                Column {
                    Image(
                    painter = painterResource(id = R.drawable.interfacelogo),
                   contentDescription = "Interface Logo",
                   modifier = Modifier.width(widthDP/6),
                   contentScale = ContentScale.FillWidth
                )
            }
            Text(
                text = "Developed by Cellus Tech India",
                style = TextStyle(
                  fontSize = MaterialTheme.typography.labelMedium.fontSize,
                  fontWeight = FontWeight.Bold,
                  color = pureWhite,
                  fontFamily = nk
                )
            )

    }   }

       Column(verticalArrangement = Arrangement.SpaceBetween){
          Box(modifier = Modifier
              .background(color = lightGrey)
              .padding(MaterialTheme.dimens.headerPadding)){
          Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
          ) {
              Row {
                  Text(
                      text = "Name: ",
                      style = TextStyle(
                          fontSize = MaterialTheme.typography.bodySmall.fontSize,
                          color = pureBlack,
                          fontFamily = poppinsregular
                      )
                  )
                  Text(
                      text = mainViewModel.name,
                      style = TextStyle(
                          fontSize = MaterialTheme.typography.bodySmall.fontSize,
                          color = pureBlack,
                          fontWeight = FontWeight.Bold,
                          fontFamily = poppinsbold
                      )
                  )
              }

              Row {
                  Text(
                      text = "Device Id: ",
                      style = TextStyle(
                          fontSize = MaterialTheme.typography.bodySmall.fontSize,
                          color = pureBlack,
                          fontFamily = poppinsregular
                      )
                  )

                  Text(
                      text = mainViewModel.deviceId,
                      style = TextStyle(
                          fontSize = MaterialTheme.typography.bodySmall.fontSize,
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
                          fontSize = MaterialTheme.typography.bodySmall.fontSize,
                          color = pureBlack,
                          fontFamily = poppinsregular
                      )
                  )
                  Text(
                      text = mainViewModel.employeeId,
                      style = TextStyle(
                          fontSize = MaterialTheme.typography.bodySmall.fontSize,
                          color = pureBlack,
                          fontWeight = FontWeight.Bold,
                          fontFamily = poppinsbold
                      )
                  )
              }
              Text(
                  text = currentDateTime.value,
                  style = TextStyle(
                      fontSize = MaterialTheme.typography.bodySmall.fontSize,
                      color = pureBlack,
                      fontFamily = poppinsregular
                  )
              )


                  Image(
                      painter = painterResource(id = R.drawable.ic_account),
                      contentDescription = "Account",
                      modifier = Modifier
                          .size(MaterialTheme.dimens.logoSize)
                          .clickable {
                              myComponents.navController.navigate(PROFILE)
                          }
                  )

          }
      }
          Column(modifier = Modifier
              .padding(
                  top = MaterialTheme.dimens.mediumPadding,
                  bottom = MaterialTheme.dimens.largePadding
              )
              .fillMaxHeight()
              .fillMaxWidth(),
              verticalArrangement = Arrangement.SpaceBetween,
              horizontalAlignment = Alignment.CenterHorizontally
          ) {
                    Text(text = "WELCOME",
                      style = TextStyle(fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                          fontWeight = FontWeight.Bold,
                          color = orange,
                          fontFamily = poppinsbold)
                  )


              Column (modifier = Modifier.fillMaxWidth(),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
              )
              {
                  /*Box(
                      modifier = Modifier
                          .size(width = 320.dp, height = 430.dp)
                          .background(color = Color.Transparent, shape = RoundedCornerShape(16.dp))
                  ) {
                      Image(
                          painter = painterResource(id = R.drawable.romuu),
                          contentDescription = "Roman",
                          contentScale = ContentScale.Crop,
                          modifier = Modifier.matchParentSize()
                              .clip(RoundedCornerShape(16.dp))
                      )
                  }*/
                  
//                  Base64Image(base64String = myComponents.mainViewModel.profilePic, modifier = Modifier.size(width = 320.dp, height = 430.dp).border(shape = RoundedCornerShape(8.dp), color = Color.Transparent, width = 0.dp))


                  Box (modifier = Modifier.border(shape = RoundedCornerShape(16.dp), color = Color.Transparent, width = 0.dp)){
                      Image(modifier = Modifier.size(width = 200.dp, height = 250.dp),
                          bitmap = bitmap.asImageBitmap(),
                          contentDescription = "Roman"

                      )
                  }

                  Spacer(modifier = Modifier.height(MaterialTheme.dimens.topPadding))
                  Text(text = myComponents.mainViewModel.name,
                      style = TextStyle(fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                          fontWeight = FontWeight.Bold,
                          color = pureBlack,
                          fontFamily = nkbold
                      )
                  )

                  Spacer(modifier = Modifier.height(MaterialTheme.dimens.padding))

                  Skills()

              }

              Column(modifier = Modifier.fillMaxWidth(),
                  verticalArrangement = Arrangement.Center,
                  horizontalAlignment = Alignment.CenterHorizontally
              ) {
                  
                  Text(text = "Click Here To Get Your Assigned Task",
                          fontSize = MaterialTheme.typography.bodySmall.fontSize,
                      fontFamily = nk
                  )
                  Spacer(modifier = Modifier.height(MaterialTheme.dimens.mediumPadding))

                  CustomRoundedButton(onClick = {
                      mainViewModel.initializeSocket()
                      mainViewModel.getTask(mainViewModel.getStationValue())

                  }, text = "Get Task")
              }

          }
      }
    }
}
/*

@Composable
fun Base64Image(
    base64String: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    Box(modifier = modifier) {



        // Use Coil to load the Bitmap into an Image composable
        Image(
//            bitmap = bitmap.asImageBitmap(),
//        painter = rememberAsyncImagePainter(bitmap.asImageBitmap()),
            contentDescription = null,
            contentScale = contentScale
        )
    }
}*/
