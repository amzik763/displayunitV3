package com.cti.displayuni

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.cti.displayuni.dialogBox.NetworkErrorDialog
import com.cti.displayuni.networks.AuthAPIs
import com.cti.displayuni.networks.RetrofitBuilder
import com.cti.displayuni.repository.Repository
import com.cti.displayuni.screens.Configure
import com.cti.displayuni.utility.NetworkMonitor
import com.cti.displayuni.utility.myComponents.authAPI
import com.cti.displayuni.utility.myComponents.mUiViewModel
import com.cti.displayuni.utility.myComponents.mUiViewModelFactory
import com.cti.displayuni.utility.myComponents.mainViewModel
import com.cti.displayuni.utility.myComponents.mainViewModelFactory
import com.cti.displayuni.utility.myComponents.networkMonitor
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.d("Network: ", "Connected")
                Log.d("Network: ", "Connected")
                Log.d("Network: ", "Connected")
                Log.d("Network: ", "Connected")
                Log.d("Network: ", "Connected")
                Log.d("Network: ", "Connected")
                Log.d("Network: ", "Connected")
                mUiViewModel.hideNetworkDialog()

            }

            override fun onLost(network: Network) {
                Log.d("Network: ", "Disconnected")
                Log.d("Network: ", "Disconnected")
                Log.d("Network: ", "Disconnected")
                Log.d("Network: ", "Disconnected")
                Log.d("Network: ", "Disconnected")
                Log.d("Network: ", "Disconnected")
                mUiViewModel.showNetworkDialog()
            }
        }

        authAPI = RetrofitBuilder.instance.create(AuthAPIs::class.java)
        repository = Repository()
        mainViewModelFactory = MainViewModelFactory(context)
        mainViewModel = mainViewModelFactory.create(MainViewModel::class.java)
        networkMonitor = NetworkMonitor(applicationContext)
        Log.d("temp", networkMonitor.isNetworkAvailable(applicationContext).toString())
//        networkMonitor.registerNetworkCallback(networkCallback)
        mUiViewModelFactory = UiViewModelFactory(context)
        mUiViewModel = mUiViewModelFactory.create(UiViewModel::class.java)
        networkMonitor.registerNetworkCallback(networkCallback)


        setContent {
                Configure()
              mDialogNetwork(mUiViewModel , applicationContext )
        }
    }

@Composable
fun mDialogNetwork(mUiViewModel: UiViewModel, applicationContext: Context) {
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

}