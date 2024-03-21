package com.cti.displayuni.utility

import androidx.navigation.NavHostController
import com.cti.displayuni.networks.AuthAPIs
import com.cti.displayuni.networks.OtherAPIs
import com.cti.displayuni.repository.Repository
import com.cti.displayuni.response.login_response
import com.cti.displayuni.viewmodels.MainViewModel
import com.cti.displayuni.viewmodels.MainViewModelFactory
import com.cti.displayuni.viewmodels.UiViewModel
import com.cti.displayuni.viewmodels.UiViewModelFactory
import retrofit2.Response

object myComponents {

    lateinit var authAPI: AuthAPIs
    lateinit var otherAPIs: OtherAPIs
    lateinit var repository: Repository
    lateinit var mUiViewModelFactory: UiViewModelFactory
    lateinit var mUiViewModel: UiViewModel
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var mainViewModel: MainViewModel
    lateinit var networkMonitor: NetworkMonitor
    lateinit var navController:NavHostController

}

object responses{

    lateinit var loginResponse: Response<login_response>

}