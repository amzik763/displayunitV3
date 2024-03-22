package com.cti.displayuni.dialogBox

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.darkBlue
import com.cti.displayuni.ui.theme.lightBlack
import com.cti.displayuni.ui.theme.lightOrange
import com.cti.displayuni.ui.theme.pureWhite
import com.cti.displayuni.utility.mFont.nkbold
import com.cti.displayuni.utility.mFont.nkmedium
import com.cti.displayuni.utility.mFont.poppinsregular
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.viewmodels.UiViewModel

//@Preview(name = "Tablet", device = "spec:width=1920px,height=1080px,dpi=160,isRound=false,orientation=landscape", showBackground = true, showSystemUi = true)
@Composable
fun TaskNotApproved(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    uiviewModel: UiViewModel
//    dialogText: String,
//    onClick() -> Unit
){
    Dialog(
        onDismissRequest = {
//            onDismiss()
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
            Row(modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.75f)
                .background(pureWhite)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight(0.8f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Image(painter = painterResource(id = R.drawable.ic_notest),
                        contentDescription = "no_Test",
//                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(200.dp)
                    )
                }

                Column(modifier = Modifier
                    .padding(8.dp)
                    .width(8.dp)) {
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
                        .padding(top = 64.dp, start = 36.dp, end = 48.dp, bottom = 48.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Task not approved",
                            style = TextStyle(
                                fontSize = 50.sp,
                                color = lightBlack,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontFamily = nkbold
                            )
                        )

                        Text(modifier = Modifier.padding(top = 36.dp),
                            text = "Ask floor-in-charge to approve task",
                            style = TextStyle(
                                fontSize = 36.sp,
                                color = lightBlack,
                                textAlign = TextAlign.Center,
                                fontFamily = nkmedium
                            )
                        )
                    }

                    Column(modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End){
                        Surface(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .size(width = 248.dp, height = 50.dp),
                            color = darkBlue,
                            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                            border = BorderStroke(width = 1.dp, color = darkBlue)
                        ) {
                            ClickableText(
                                text = AnnotatedString("Refresh"),
                                style = TextStyle(
                                    color = pureWhite,
                                    fontSize = 26.sp,
                                    fontFamily = poppinsregular,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(9.dp)
                                    .align(Alignment.CenterHorizontally),
                                onClick = {
//                                    myComponents.mainViewModel.taskApproved(myComponents.mainViewModel.MASTERDATA.value?.task_assigned_data?.task_id.toString())
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
