package com.cti.displayuni.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UiViewModel(context: Context) : ViewModel(){

    var isNetworkDialogShown by mutableStateOf(false)
        private set

    var dialogText by mutableStateOf("Network error...")
        private set

    fun setMyDialogText(s: String) {
        dialogText = s
    }

    fun showNetworkDialog(){
        isNetworkDialogShown = true
    }
    fun hideNetworkDialog(){
        isNetworkDialogShown = false
    }

}