package com.cti.displayuni.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cti.displayuni.R
import com.cti.displayuni.ui.theme.blue
import com.cti.displayuni.utility.CONFIGURE
import com.cti.displayuni.utility.LOGIN
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.navController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {

    val imageSize = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        imageSize.animateTo(
            targetValue = 250f,
            animationSpec = tween(
                    durationMillis = 2000,
                    easing = { OvershootInterpolator(9f).getInterpolation(it) }
            )
        )
        delay(3000L)

        if (myComponents.mainViewModel.getStationValue().isNotEmpty()){
            navController.popBackStack()
            navController.navigate(LOGIN)
        }
        else {
            navController.popBackStack()
            navController.navigate(CONFIGURE)
        }
    }

    val animatedSize = animateDpAsState(targetValue = imageSize.value.dp, label = "Animate as dp")

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(blue)
            .fillMaxSize()) {
        Image(
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.interfacelogo),
            contentDescription = "Interface Logo",
            modifier = Modifier.size(animatedSize.value)
        )
    }
}
