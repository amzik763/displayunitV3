package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.response.CheckSheetData
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.mFont.nk
import com.cti.displayuni.utility.mFont.nkbold
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents

@Composable
fun ItemComponents(item: CheckSheetData) {

   val conf = LocalConfiguration.current
   val dnsty = conf.densityDpi

   Log.d("mdpi density: ", dnsty.toString())

   val wd = mParameters.mWidthinPx
   //myUI variables
   var fillMaxWidth = 0.05f
   var fillMaxWidth2 = 0.65f
   var fillMaxWidth3 = 0.65f
   var fillMaxWidth4 = 0.65f
   var fillMaxWidth5 = 0.65f
   var startPadding1 = 8.dp
   var startPadding2 = 16.dp
   var textFont = 16.sp
   var maintextFont = 16.sp


   Log.d("dwinsize: ", wd.toString())

   mParameters.dnsty = dnsty
   Log.d("mparam density: ", mParameters.dnsty.toString())

   if (wd <= 2048 && mParameters.dnsty == 320) {


      fillMaxWidth = 0.05f
      fillMaxWidth2 = 0.35f
      fillMaxWidth3 = 0.3f
      fillMaxWidth4 = 0.34f
      fillMaxWidth5 = 0.3f
      textFont = 12.sp
      maintextFont = 16.sp
      startPadding1 = 6.dp
      startPadding2 = 12.dp

      Log.d("lwinsize: ", wd.toString())

   } else if (wd <= 2048 && mParameters.dnsty == 160) {

      fillMaxWidth = 0.05f
      fillMaxWidth2 = 0.4f
      fillMaxWidth3 = 0.32f
      fillMaxWidth4 = 0.35f
      fillMaxWidth5 = 0.3f
      textFont = 17.sp
      maintextFont = 20.sp
      startPadding1 = 8.dp
      startPadding2 = 16.dp



      Log.d("Desktop: ", wd.toString())
   }

   Row(
      modifier = Modifier
         .fillMaxWidth()

   ) {
      Text(modifier = Modifier
         .fillMaxWidth(fillMaxWidth)
         .padding(start = startPadding1),
         text ="${item.csp_id}",
         color = pureBlack,
         fontFamily = nk,
         fontSize = textFont,
         textAlign = TextAlign.Center
      )

      Text(modifier = Modifier
         .fillMaxWidth(fillMaxWidth2)
         .padding(start = startPadding2),
         text = if(myComponents.mUiViewModel.isHindi.value) "${item.csp_name_hindi}" else "${item.csp_name}" ,
         color = pureBlack,
         fontFamily = nk,
         fontSize = textFont,
         textAlign = TextAlign.Center
      )

      Text(modifier = Modifier
         .fillMaxWidth(fillMaxWidth3)
         .padding(start =startPadding2),
         text ="${item.specification}",
         color = pureBlack,
         fontFamily = nk,
         fontSize = textFont,
         textAlign = TextAlign.Center
      )

      Text(modifier = Modifier
         .fillMaxWidth(fillMaxWidth4)
         .padding(start = startPadding2),
         text ="${item.control_method}",
         color = pureBlack,
         fontFamily = nk,
         fontSize = textFont,
         textAlign = TextAlign.Center
      )

      Text(modifier = Modifier
         .fillMaxWidth(fillMaxWidth5)
         .padding(start = startPadding2),
         text = item.frequency,
         color = pureBlack,
         fontFamily = nk,
         fontSize = textFont,
         textAlign = TextAlign.Center
      )

      Text(modifier = Modifier
         .fillMaxWidth(fillMaxWidth5)
         .padding(start = startPadding2),
         text ="Notify",
         color = pureBlack,
         fontWeight = FontWeight.Bold,
         fontStyle = FontStyle.Italic,
         fontFamily = nk,
         fontSize = textFont,
         textAlign = TextAlign.Center
      )

      DropDown(item.csp_id)


   }
}
@Composable
fun ItemListColumn(mChecksheetData: List<CheckSheetData>) {

   val conf = LocalConfiguration.current
   val dnsty = conf.densityDpi

   Log.d("mdpi density: ", dnsty.toString())

   val wd = mParameters.mWidthinPx
   //myUI variables
   var fillMaxWidth = 0.05f
   var fillMaxWidth2 = 0.65f
   var fillMaxWidth3 = 0.65f
   var fillMaxWidth4 = 0.65f
   var fillMaxWidth5 = 0.65f
   var startPadding1 = 8.dp
   var startPadding2 = 16.dp
   var textFont = 16.sp
   var maintextFont = 14.sp



   Log.d("dwinsize: ", wd.toString())

   mParameters.dnsty = dnsty
   Log.d("mparam density: ", mParameters.dnsty.toString())

   if (wd <= 2048 && mParameters.dnsty == 320) {


      fillMaxWidth = 0.05f
      fillMaxWidth2 = 0.35f
      fillMaxWidth3 = 0.3f
      fillMaxWidth4 = 0.34f
      fillMaxWidth5 = 0.3f
      textFont = 12.sp
      maintextFont = 14.sp

      startPadding1 = 6.dp
      startPadding2 = 12.dp


      Log.d("lwinsize: ", wd.toString())

   } else if (wd <= 2048 && mParameters.dnsty == 160) {

      fillMaxWidth = 0.05f
      fillMaxWidth2 = 0.4f
      fillMaxWidth3 = 0.32f
      fillMaxWidth4 = 0.35f
      fillMaxWidth5 = 0.3f
      textFont = 17.sp
      maintextFont = 20.sp
      startPadding1 = 8.dp
      startPadding2 = 16.dp


      Log.d("Desktop: ", wd.toString())
   }

   Column(
      modifier = Modifier
         .padding(top = 8.dp, bottom = 24.dp)
         .background(color = lightGrey)
         .fillMaxWidth()
   ) {

      Row(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier
               .fillMaxWidth(fillMaxWidth)
               .padding(start = startPadding1),
               text = "S. No.",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = maintextFont,
               textAlign = TextAlign.Center
            )

            Text(modifier = Modifier
               .fillMaxWidth(fillMaxWidth2)
               .padding(start = startPadding2),
               text = "CHECKPOINTS",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = maintextFont,
               textAlign = TextAlign.Center
            )

            Text(modifier = Modifier
               .fillMaxWidth(fillMaxWidth3)
               .padding(start = startPadding2),
               text = "Specification",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = maintextFont,
               textAlign = TextAlign.Center
            )

            Text(modifier = Modifier
               .fillMaxWidth(fillMaxWidth4)
               .padding(start = startPadding2),
               text = "Control Method",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = maintextFont,
               textAlign = TextAlign.Center
            )

            Text(modifier = Modifier
               .fillMaxWidth(fillMaxWidth5)
               .padding(start = startPadding2),
               text = "Frequency",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = maintextFont,
               textAlign = TextAlign.Center
            )

         Text(modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(start = startPadding2),
            text = "Status",
            fontWeight = FontWeight.Bold,
            color = pureBlack,
            fontFamily = nkbold,
            fontSize = maintextFont,
            textAlign = TextAlign.Center
         )
         }
      Spacer(modifier = Modifier.height(24.dp))
         LazyColumn {
            items(mChecksheetData) { item ->
               ItemComponents(item = item)
            }
      }
   }
}
@Composable
fun ItemListScreen() {
//   var itemList by remember { mutableStateOf<List<ItemDataClass>>(emptyList()) }
//   val mChecksheetData by myComponents.mainViewModel.mChecksheet.observeAsState()

   val mChecksheetData by myComponents.mainViewModel.mChecksheetData.observeAsState()
   mChecksheetData?.let { ItemListColumn(it) }
}