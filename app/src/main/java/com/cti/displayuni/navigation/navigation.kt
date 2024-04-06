package com.cti.displayuni.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cti.displayuni.screens.Checksheet
import com.cti.displayuni.screens.Checksheet2
import com.cti.displayuni.screens.Configure
import com.cti.displayuni.screens.FillParam
import com.cti.displayuni.screens.GetTask
import com.cti.displayuni.screens.Header
import com.cti.displayuni.screens.Login
import com.cti.displayuni.utility.CHECKSHEET
import com.cti.displayuni.utility.CONFIGURE
import com.cti.displayuni.utility.FILL_PARAMETERS
import com.cti.displayuni.utility.GETTASK
import com.cti.displayuni.utility.LOGIN
import com.cti.displayuni.utility.myComponents.navController


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigate() {

    navController = rememberNavController()

    NavHost(
        navController =  navController,
        startDestination = CONFIGURE,
    )

    {
        composable(CONFIGURE) {
            Configure()
        }

        composable(LOGIN){
          Login()
        }

        composable(GETTASK){
            GetTask()
        }
        composable(CHECKSHEET){
            Checksheet2()
        }

       /* composable(CHECKSHEET){
            Checksheet()
        }*/

        composable(FILL_PARAMETERS){
            FillParam()
        }
    }
}