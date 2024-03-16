package com.cti.displayuni.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cti.displayuni.screens.Configure
import com.cti.displayuni.screens.Login
import com.cti.displayuni.utility.CONFIGURE
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
    }
}