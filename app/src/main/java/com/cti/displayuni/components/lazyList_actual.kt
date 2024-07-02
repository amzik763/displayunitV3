package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.utility.Actual_Param
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.showLogs
import kotlinx.coroutines.delay

@Composable
fun ActualLazyList(
    dataListActual: MutableList<Actual_Param>
) {

    val conf = LocalConfiguration.current
    val widthDP = conf.screenWidthDp.dp
    val dnsty = conf.densityDpi

    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables
    var mainHeaderFont = 52.sp
    var semiHeaderFont = 36.sp
    var maxWidth = widthDP/3f
    var textFont = 15.sp
    var width = 180.dp
    var textFont1 = 18.sp
    var textFont2 = 18.sp
    var height = 40.dp
    var padding = 24.dp
    var imgSize = 50.dp
    var interfaceW = 300.dp
    var interfaceH = 70.dp
    var widthdp = widthDP/3f
    var start = 36.dp
    var top = 8.dp
    var bottom = 36.dp
    var btnpadding = 9.dp
    var paddingEndSmall = 10.dp
    var gap = 30.dp
    var unitWidth = 60.dp
    var enterValueWidth = 180.dp

    Log.d("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {
        mainHeaderFont = 34.sp
        maxWidth = widthDP/3.5f
        semiHeaderFont = 20.sp
        textFont = 12.sp
        width = 120.dp
        height = 30.dp
        textFont1 = 14.sp
        textFont2 = 12.sp
        padding = 20.dp
        interfaceW = 180.dp
        interfaceH = 50.dp
        widthdp = widthDP/3.7f
        start = 28.dp
        top = 4.dp
        bottom = 28.dp
        btnpadding = 6.dp
        paddingEndSmall = 4.dp
        gap = 20.dp
        unitWidth = 40.dp
        enterValueWidth = 160.dp



    } else if (wd <= 2048 && mParameters.dnsty == 160) {
        maxWidth = widthDP/3f
        mainHeaderFont = 58.sp
        semiHeaderFont = 36.sp
        textFont = 15.sp
        width = 210.dp
        height = 50.dp
        textFont1 = 24.sp
        textFont2 = 20.sp
        padding = 24.dp
        interfaceW = 300.dp
        interfaceH = 70.dp
        widthdp = widthDP/3f
        start = 36.dp
        top = 8.dp
        bottom = 36.dp
        btnpadding = 9.dp
        Log.d("Desktop: ", wd.toString())
    }

    val enterValues = rememberSaveable { mutableStateOf(mutableMapOf<Int, String>()) }

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        itemsIndexed(dataListActual) { index,item ->

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = paddingEndSmall),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.param_name.toString(),

                        style = TextStyle(
                            fontSize = textFont,
                            color = pureBlack,
                            fontFamily = mFont.nkbold,
                        ),
                        modifier = Modifier.padding(top = top)
                            .fillMaxWidth(0.7f),
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(gap))
                    Row (verticalAlignment = Alignment.CenterVertically){
                        // Assigning the remembered enterValue to the item's param_value
                        var enterValue by rememberSaveable { mutableStateOf(enterValues.value[index] ?: "") }

                        Row(modifier = Modifier.width(enterValueWidth)){
                            CustomOutlinedTextField(
                                text = enterValue,
                                label = item.min + " - " + item.max,
                                onTextChange = { newValue ->
                                    enterValue = newValue
                                    enterValues.value = enterValues.value.toMutableMap().apply {
                                        this[index] = newValue
                                    }
                                    item.param_value = newValue
                                },
                                color = pureBlack,
                                maxLength = 15,
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                        Text(
                            text = item.param_unit.toString(),
                            style = TextStyle(
                                fontSize = textFont,
                                color = pureBlack,
                                fontFamily = mFont.nk,
                            ),
                            modifier = Modifier
                                .padding(top = top, start = 4.dp)
                                .width(unitWidth),
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )


                    }
                }

                Divider(modifier = Modifier.padding(top = top))
            }
        }
    }

    LaunchedEffect(myComponents.mUiViewModel.clearFields.value) {
        enterValues.value =  mutableMapOf()
//        enterValue = ""

        myComponents.mainViewModel.dataListSetting.forEach {
            it.param_value = ""
        }

        myComponents.mainViewModel.dataListActual.forEach {
            it.param_value = ""
        }
        mainViewModel.showZoomableImage = !mainViewModel.showZoomableImage
        delay(1500)
        mainViewModel.showZoomableImage = !mainViewModel.showZoomableImage
        showLogs("val","val cleared: ACTUAL")
    }
}
