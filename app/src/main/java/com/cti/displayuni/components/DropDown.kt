package com.cti.displayuni.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureBlack
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.mFont
import com.cti.displayuni.utility.mParameters
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DropDown(paramId: String) {
    Log.d("abc",myComponents.mainViewModel.checkSheetList.size.toString())

    var expanded by remember { mutableStateOf(false) }
//    val selectedItem by rememberUpdatedState("Status")
    var selectedItem by remember { mutableStateOf("Status") }
    val items = listOf("OK", "NG", "SUP_OK")

    LaunchedEffect(selectedItem,expanded) {
        // Use LaunchedEffect to update selectedItem after recomposition
        showLogs("UPDATED"," : UPDATED")
        selectedItem = myComponents.mainViewModel.checkSheetList[Integer.parseInt(paramId) - 1]
    }

    Column {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentSize()
        ) {
            Text(selectedItem) //Display the selected item
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    { Text(text = item,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = pureBlack,
                            textAlign = TextAlign.Center
                        )
                    ) },
                    onClick = {

                        if (item == "SUP_OK") {
                            expanded = false
                            myComponents.mainViewModel.tempParamID = paramId
                            // Show the custom dialog
                            CoroutineScope(Dispatchers.IO).launch {
                                myComponents.mUiViewModel.showLoginSupDialog()
                            }

                        } else {
                            selectedItem = item
                            myComponents.mainViewModel.checkSheetList.set(Integer.parseInt(paramId) - 1, item)
                            myComponents.mainViewModel.checkSheetList.forEach { println(it) }
                            expanded = false
                        }

                })
            }
        }
    }

    if(myComponents.mUiViewModel.isLoginSupShown){
        SupLoginDialog(
            selectedItem = selectedItem,
            onDismiss = { expanded = false },
            updateSelectedItem = { newItem ->
                selectedItem = newItem
            },
            apiCall = { username, password ->
                var mResponse =  myComponents.otherAPIs.supLogin(username, password)
                if(mResponse.isSuccessful){
                    var a = "SUP_OK"
                        selectedItem = "SUP_OK"
                    myComponents.mainViewModel.checkSheetList.set(Integer.parseInt(myComponents.mainViewModel.tempParamID) - 1, a)
                    showLogs("mParamID: ",myComponents.mainViewModel.tempParamID)
                    myComponents.mainViewModel.checkSheetList.forEach { println(it) }
                    expanded = false
                    showLogs("EXPANDED",expanded.toString())
                    myComponents.mUiViewModel.hideLoginSupDialog()

                }else{
                    //error message or string
                }
            }
        )
    }
}

//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun SupLoginDialog(
    selectedItem: String,
    onDismiss: () -> Unit,
    updateSelectedItem: (String) -> Unit, // Callback to update selectedItem
    apiCall: suspend (username: String, password: String) -> Unit
) {

    val conf = LocalConfiguration.current
    val dnsty = conf.densityDpi

    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Log.d("mdpi density: ", dnsty.toString())

    val wd = mParameters.mWidthinPx
    //myUI variables
    var fillMaxWidth = 0.65f
    var fillMaxHeight = 0.45f
    var mainHeaderFont = 58.sp
    var semiHeaderFont = 24.sp
    var textFont1 = 18.sp
    var maxWidth = 0.3f
    var width = 180.dp
    var topPadding = 64.dp
    var startPadding = 36.dp
    var endPadding = 48.dp
    var bottomPadding = 48.dp
    var height = 40.dp
    var imgSize = 50.dp

    Log.d("dwinsize: ", wd.toString())

    mParameters.dnsty = dnsty
    Log.d("mparam density: ", mParameters.dnsty.toString())

    if (wd <= 2048 && mParameters.dnsty == 320) {

        topPadding = 40.dp
        startPadding = 20.dp
        endPadding = 20.dp
        bottomPadding = 16.dp
        fillMaxWidth = 0.6f
        fillMaxHeight = 0.45f
        maxWidth = 0.24f
        startPadding = 16.dp
        mainHeaderFont = 30.sp
        semiHeaderFont = 14.sp
        textFont1 = 15.sp
        topPadding = 20.dp
        width = 180.dp
        height = 40.dp
        imgSize = 80.dp

        Log.d("lwinsize: ", wd.toString())

    } else if (wd <= 2048 && mParameters.dnsty == 160) {

        topPadding = 64.dp
        startPadding = 36.dp
        endPadding = 48.dp
        bottomPadding = 48.dp
        fillMaxWidth = 0.65f
        fillMaxHeight = 0.45f
        maxWidth = 0.3f
        startPadding = 36.dp
        mainHeaderFont = 56.sp
        semiHeaderFont = 36.sp
        textFont1 = 24.sp
        topPadding = 24.dp
        width = 210.dp
        height = 50.dp
        imgSize = 200.dp
        Log.d("Desktop: ", wd.toString())
    }

    Dialog(
        onDismissRequest = { myComponents.mUiViewModel.hideLoginSupDialog()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .border(1.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.9f)
                    .background(pureWhite)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(maxWidth)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_account),
                        contentDescription = "Login",
//                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(imgSize)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(8.dp)
                ) {
                    Divider(
                        Modifier
                            .fillMaxHeight()
                            .padding(
                                top = 4.dp,
                                bottom = 10.dp
                            ),
                        color = lightOrange
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(
                            top = topPadding,
                            start = startPadding,
                            end = endPadding,
                            bottom = bottomPadding
                        )
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Login Supervisor",
                            style = TextStyle(
                                fontSize = mainHeaderFont,
                                color = lightBlack,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontFamily = mFont.nkbold
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))


                        SupUserId(
                            text = name,
                            label = "Username",
                            onTextChange = { name = it },
                            color = pureBlack,
                            iconResId = R.drawable.usericon,
                            maxLength = 40,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                            shape = RoundedCornerShape(8.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        SupPassword(
                            text = password,
                            label = "Password",
                            onTextChange = { password = it },
                            color = pureBlack,
                            iconResId = R.drawable.ic_lock,
                            maxLength = 20,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            shape = RoundedCornerShape(8.dp)
                        )

                        Text(
                            modifier = Modifier.padding(top = 36.dp),
                            text = "dialogModel.dialogSubHeader Text",
                            style = TextStyle(
//                                fontSize = semiHeaderFont,
                                fontSize = 24.sp,
                                color = lightBlack,
                                textAlign = TextAlign.Center,
                                fontFamily = mFont.nk
                            )
                        )

                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Surface(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .size(width = width, height = height),
                            color = darkBlue,
                            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                            border = BorderStroke(width = 1.dp, color = darkBlue)
                        ) {
                            ClickableText(
                                text = AnnotatedString("LOGIN"),
                                style = TextStyle(
                                    color = pureWhite,
                                    fontSize = textFont1,
                                    fontFamily = mFont.poppinsregular,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(9.dp)
                                    .align(Alignment.CenterHorizontally),

                                onClick = {
                                    // Call the API with username and password
                                    CoroutineScope(Dispatchers.IO).launch {
                                        try {
                                            apiCall(name, password)
                                        } catch (e: Exception) {
                                            // Handle API call failure
                                            Log.e("API Call", "Exception: ${e.message}")
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
