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
import androidx.compose.material3.MaterialTheme
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
import com.cti.displayuni.ui.theme.dimens
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

   var startPadding1 = 8.dp
   var startPadding2 = 10.dp

   Log.d("dwinsize: ", wd.toString())

   mParameters.dnsty = dnsty
   Log.d("mparam density: ", mParameters.dnsty.toString())

   if (wd <= 2048 && mParameters.dnsty == 320) {

      startPadding1 = 4.dp
      startPadding2 = 12.dp

      Log.d("lwinsize: ", wd.toString())

   } else if (wd <= 2048 && mParameters.dnsty == 160) {

      startPadding1 = 8.dp
      startPadding2 = 10.dp

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
               .fillMaxWidth(MaterialTheme.dimens.fillMaxWidth)
               .padding(start = startPadding1),
            text = item.csp_id,
            color = pureBlack,
            fontFamily = nk,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
         )


         Text(
            modifier = Modifier
               .fillMaxWidth(MaterialTheme.dimens.fillMaxWidth2)
               .padding(start = MaterialTheme.dimens.padding),
            text = item.csp_name,
            color = pureBlack,
            fontFamily = nk,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
         )

         Text(
            modifier = Modifier
               .fillMaxWidth(MaterialTheme.dimens.fillMaxWidth3)
               .padding(start = MaterialTheme.dimens.startPadding),
            text = item.specification,
            color = pureBlack,
            fontFamily = nk,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
         )

         Text(
            modifier = Modifier
               .fillMaxWidth(MaterialTheme.dimens.fillMaxWidth4)
               .padding(start = MaterialTheme.dimens.topPadding),
            text = item.control_method,
            color = pureBlack,
            fontFamily = nk,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
         )

         Text(
            modifier = Modifier
               .fillMaxWidth(MaterialTheme.dimens.fillMaxWidth5)
               .padding(start = startPadding2),
            text = item.frequency,
            color = pureBlack,
            fontFamily = nk,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
         )

         DropDown(item.csp_id, index,notificationIdState[item.csp_id].toString())

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
   var startPadding1 = 8.dp
   var startPadding2 = 10.dp

   Log.d("dwinsize: ", wd.toString())

   mParameters.dnsty = dnsty
   Log.d("mparam density: ", mParameters.dnsty.toString())

   if (wd <= 2048 && mParameters.dnsty == 320) {

      startPadding1 = 5.dp
      startPadding2 = 12.dp

      Log.d("lwinsize: ", wd.toString())

   } else if (wd <= 2048 && mParameters.dnsty == 160) {

      startPadding1 = 8.dp
      startPadding2 = 10.dp
      Log.d("Desktop: ", wd.toString())
   }

   Column(
      modifier = Modifier
         .padding(top = MaterialTheme.dimens.padding, bottom = MaterialTheme.dimens.topPadding)
         .background(color = lightGrey)
         .fillMaxWidth()
   ) {

      Row(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier
               .fillMaxWidth(MaterialTheme.dimens.fillMaxWidth)
               .padding(start = startPadding1),
               text = "S. No.",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = MaterialTheme.typography.bodySmall.fontSize,
            )

            Text(modifier = Modifier
               .fillMaxWidth(MaterialTheme.dimens.fillMaxWidth2)
               .padding(start = MaterialTheme.dimens.padding),
               text = "CHECKPOINTS",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = MaterialTheme.typography.bodySmall.fontSize,
            )

            Text(modifier = Modifier
               .fillMaxWidth(MaterialTheme.dimens.fillMaxWidth3)
               .padding(start = MaterialTheme.dimens.startPadding),
               text = "Specification",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = MaterialTheme.typography.bodySmall.fontSize,
            )

            Text(modifier = Modifier
               .fillMaxWidth(MaterialTheme.dimens.fillMaxWidth4)
               .padding(start = MaterialTheme.dimens.topPadding),
               text = "Control Method",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = MaterialTheme.typography.bodySmall.fontSize,
            )

            Text(modifier = Modifier
               .fillMaxWidth(MaterialTheme.dimens.fillMaxWidth5)
               .padding(start = startPadding2),
               text = "Frequency",
               fontWeight = FontWeight.Bold,
               color = pureBlack,
               fontFamily = nkbold,
               fontSize = MaterialTheme.typography.bodySmall.fontSize,
            )

         Text(modifier = Modifier
            .fillMaxWidth(MaterialTheme.dimens.statusMaxW),
//            .padding(start = MaterialTheme.dimens.smallPadding),
            text = "Status",
            fontWeight = FontWeight.Bold,
            color = pureBlack,
            fontFamily = nkbold,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            textAlign = TextAlign.Center
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
   val mChecksheetData by myComponents.mainViewModel.mChecksheetData.observeAsState()
   mChecksheetData?.let { ItemListColumn(it) }
}

