package com.cti.displayuni

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.amzi.displayunit.networks.RetrofitBuilder
import com.cti.displayuni.dialogBox.MessageDialog
import com.cti.displayuni.dialogBox.NetworkErrorDialog
import com.cti.displayuni.navigation.Navigate
import com.cti.displayuni.networks.OtherAPIs
import com.cti.displayuni.repository.Repository
import com.cti.displayuni.utility.NetworkMonitor
import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.myComponents.authAPI
import com.cti.displayuni.utility.myComponents.mUiViewModel
import com.cti.displayuni.utility.myComponents.mUiViewModelFactory
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.myComponents.mainViewModelFactory
import com.cti.displayuni.utility.myComponents.networkMonitor
import com.cti.displayuni.utility.myComponents.otherAPIs
import com.cti.displayuni.utility.myComponents.repository
import com.cti.displayuni.viewmodels.MainViewModel
import com.cti.displayuni.viewmodels.MainViewModelFactory
import com.cti.displayuni.viewmodels.UiViewModel
import com.cti.displayuni.viewmodels.UiViewModelFactory

class MainActivity : ComponentActivity() {

    val context = this
    lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onDestroy() {
        super.onDestroy()
        networkMonitor.unregisterNetworkCallback(networkCallback)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.d("Network: ", "Connected")
                Log.d("Network: ", "Connected")
                Log.d("Network: ", "Connected")
                mUiViewModel.hideNetworkDialog()

            }

            override fun onLost(network: Network) {
                Log.d("Network: ", "Disconnected")
                Log.d("Network: ", "Disconnected")
                Log.d("Network: ", "Disconnected")
                mUiViewModel.showNetworkDialog()
            }
        }

        authAPI = RetrofitBuilder.createApiServiceWithoutToken()
        repository = Repository()
        mainViewModelFactory = MainViewModelFactory(context)
        mainViewModel = mainViewModelFactory.create(MainViewModel::class.java)
        networkMonitor = NetworkMonitor(applicationContext)
        Log.d("temp", networkMonitor.isNetworkAvailable(applicationContext).toString())
        mUiViewModelFactory = UiViewModelFactory(context)
        mUiViewModel = mUiViewModelFactory.create(UiViewModel::class.java)
        networkMonitor.registerNetworkCallback(networkCallback)


        setContent {
                Navigate()
              NetworkDialog(mUiViewModel , applicationContext )
                MessageDia(mUiViewModel )
        }
    }

@Composable
fun NetworkDialog(mUiViewModel: UiViewModel, applicationContext: Context) {
        if (mUiViewModel.isNetworkDialogShown) {
            NetworkErrorDialog(onDismiss = {
            },
                onConfirm = {

                },

                onRetry = {
                    if (networkMonitor.isNetworkAvailable(applicationContext)) {
                        mUiViewModel.hideNetworkDialog()
                    }
                },
                dialogText = mUiViewModel.dialogText,
                uiviewModel = mUiViewModel
            )
        }
}

@Composable
fun MessageDia(mUiViewModel: UiViewModel) {
        if (mUiViewModel.isMessageDialogShown) {
            MessageDialog(onDismiss = {
            },
                onConfirm = {

                },
                dialogModel = mUiViewModel.dialogModel,

            )
        }
}

}