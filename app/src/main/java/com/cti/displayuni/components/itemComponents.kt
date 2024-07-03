package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.R
import com.cti.displayuni.response.CheckSheetData
import com.cti.displayuni.ui.theme.lightGrey
import com.cti.displayuni.ui.theme.orange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.mFont.nk
import com.cti.displayuni.utility.mFont.nkbold
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ItemComponents(index:Int,item: CheckSheetData) {

   val conf = LocalConfiguration.current
   val dnsty = conf.densityDpi

   Log.d("mdpi density: ", dnsty.toString())


   val wd = mParameters.mWidthinPx
   //myUI variables
   var fillMaxWidth = 0.05f
   var fillMaxWidth2 = 0.63f
   var fillMaxWidth3 = 0.65f
   var fillMaxWidth4 = 0.65f
   var fillMaxWidth5 = 0.6f
   var startPadding1 = 8.dp
   var endPadding = 12.dp
   var startPadding2 = 10.dp
   var startPadding3 = 12.dp
   var textFont = 16.sp
   var imgSize = 40.dp


   Log.d("dwinsize: ", wd.toString())

   mParameters.dnsty = dnsty
   Log.d("mparam density: ", mParameters.dnsty.toString())

   if (wd <= 2048 && mParameters.dnsty == 320) {

      fillMaxWidth = 0.045f
      fillMaxWidth2 = 0.3f
      fillMaxWidth3 = 0.25f
      fillMaxWidth4 = 0.3f
      fillMaxWidth5 = 0.25f
      textFont = 12.sp
      startPadding1 = 4.dp
      endPadding = 4.dp
      startPadding2 = 12.dp
      startPadding3 = 16.dp
      imgSize = 20.dp

      Log.d("lwinsize: ", wd.toString())

   } else if (wd <= 2048 && mParameters.dnsty == 160) {

      fillMaxWidth = 0.05f
      fillMaxWidth2 = 0.38f
      fillMaxWidth3 = 0.32f
      fillMaxWidth4 = 0.35f
      fillMaxWidth5 = 0.25f
      textFont = 17.sp
      startPadding1 = 8.dp
      startPadding2 = 10.dp
      startPadding3 = 12.dp
      imgSize = 40.dp
      endPadding = 12.dp

      Log.d("Desktop: db ", wd.toString())
   }

   Column {
//      val notificationIdState = remember { item.notificationId }
//      val notificationIdState by myComponents.mainViewModel.myChecksheetNotificationMap.collectAsState()
      val notificationIdState: SnapshotStateMap<String, String> = myComponents.mainViewModel.myChecksheetNotificationMap
      Row(
         modifier = Modifier
            .fillMaxWidth(),
         verticalAlignment = Alignment.CenterVertically


      ) {
         Text(
            modifier = Modifier
               .fillMaxWidth(fillMaxWidth)
               .padding(start = startPadding1),
            text = item.csp_id,
            color = pureBlack,
            fontFamily = nk,
            fontSize = textFont,
         )


         Text(
            modifier = Modifier
               .fillMaxWidth(fillMaxWidth2)
               .padding(start = startPadding2),
            text = item.csp_name,
            color = pureBlack,
            fontFamily = nk,
            fontSize = textFont,
         )

         Text(
            modifier = Modifier
               .fillMaxWidth(fillMaxWidth3)
               .padding(start = startPadding3),
            text = item.specification,
            color = pureBlack,
            fontFamily = nk,
            fontSize = textFont,
         )

         Text(
            modifier = Modifier
               .fillMaxWidth(fillMaxWidth4)
               .padding(start = startPadding3),
            text = item.control_method,
            color = pureBlack,
            fontFamily = nk,
            fontSize = textFont,
         )

         Text(
            modifier = Modifier
               .fillMaxWidth(fillMaxWidth5)
               .padding(start = startPadding2),
            text = item.frequency,
            color = pureBlack,
            fontFamily = nk,
            fontSize = textFont,
         )

         DropDown(item.csp_id, index)

         Text(
            modifier = Modifier
               .fillMaxWidth(fillMaxWidth5)
               .padding(start = startPadding1, end = endPadding),
            text = notificationIdState[item.csp_id]?:"00" ,
            color = pureBlack,
            fontFamily = nk,
            fontSize = textFont,
         )

         Spacer(modifier = Modifier.width(startPadding2))
         Surface {
            Image(
               painter = painterResource(id = R.drawable.ic_notification),
               contentDescription = "notification",
               modifier = Modifier
                  .size(imgSize)
                  .clickable
                  {
                     /*myComponents.mainViewModel.notify(
                        myComponents.mainViewModel.getStationValue(),
                        item.csp_id,
//                        myComponents.mainViewModel.floorNum
                        myComponents.mainViewModel.getStationValue().split(" ").take(2).joinToString(" ")
                     )*/
                  }
            )
         }
      }
      Divider(
         modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
      )
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
   var fillMaxWidth2 = 0.63f
   var fillMaxWidth3 = 0.65f
   var fillMaxWidth4 = 0.65f
   var fillMaxWidth5 = 0.6f
   var startPadding1 = 8.dp
   var startPadding2 = 10.dp
   var startPadding22 = 16.dp
   var maintextFont = 14.sp
   var startPadding3 = 12.dp
   var statusMaxW = 0.65f



   Log.d("dwinsize: ", wd.toString())

   mParameters.dnsty = dnsty
   Log.d("mparam density: ", mParameters.dnsty.toString())

   if (wd <= 2048 && mParameters.dnsty == 320) {

      fillMaxWidth = 0.045f
      fillMaxWidth2 = 0.3f
      fillMaxWidth3 = 0.25f
      fillMaxWidth4 = 0.3f
      fillMaxWidth5 = 0.25f
      maintextFont = 13.sp
      startPadding1 = 5.dp
      startPadding2 = 12.dp
      startPadding22 = 1.dp
      startPadding3 = 16.dp
      statusMaxW = 0.58f

      Log.d("lwinsize: ", wd.toString())

   } else if (wd <= 2048 && mParameters.dnsty == 160) {

      fillMaxWidth = 0.05f
      fillMaxWidth2 = 0.38f
      fillMaxWidth3 = 0.32f
      fillMaxWidth4 = 0.35f
      fillMaxWidth5 = 0.25f
      maintextFont = 20.sp
      startPadding1 = 8.dp
      startPadding2 = 10.dp
      startPadding22 = 24.dp
      startPadding3 = 12.dp
      statusMaxW = 0.65f

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
            )

            Text(modifier = Modifier
               .fillMaxWidth(fillMaxWidth2)
               .padding(start = startPadding2),
               text = "CHECKPOINTS",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = maintextFont,
            )

            Text(modifier = Modifier
               .fillMaxWidth(fillMaxWidth3)
               .padding(start = startPadding3),
               text = "Specification",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = maintextFont,
            )

            Text(modifier = Modifier
               .fillMaxWidth(fillMaxWidth4)
               .padding(start = startPadding3),
               text = "Control Method",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = maintextFont,
            )

            Text(modifier = Modifier
               .fillMaxWidth(fillMaxWidth5)
               .padding(start = startPadding2),
               text = "Frequency",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = maintextFont,
            )

         Text(modifier = Modifier
            .fillMaxWidth(statusMaxW)
            .padding(start = startPadding22),
            text = "Status",
            fontWeight = FontWeight.Bold,
            color = pureBlack,
            fontFamily = nkbold,
            fontSize = maintextFont,
            textAlign = TextAlign.Center
         )

         Text(modifier = Modifier
//            .fillMaxWidth(fillMaxWidth5)
            .padding(start = startPadding2),
            text = "Notification",
            fontWeight = FontWeight.Bold,
            color = pureBlack,
            fontFamily = nkbold,
            fontSize = maintextFont,
         )

         }
      Spacer(modifier = Modifier.height(24.dp))
         LazyColumn {
            itemsIndexed(mChecksheetData){ index,item ->
               ItemComponents(index = index,item = item)
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

