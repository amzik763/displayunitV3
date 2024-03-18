package com.cti.displayuni.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cti.displayuni.R
import com.cti.displayuni.utility.DialogModel

class UiViewModel(context: Context) : ViewModel(){

    var isNetworkDialogShown by mutableStateOf(false)
        private set

    var isMessageDialogShown by mutableStateOf(false)
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

    var dialogModel: DialogModel = DialogModel("Please Enter Password","Loading...","", R.drawable.thanks)

    fun setDialogDetails(headerText:String, subHeaderText:String, dialogText:String, imageResource:Int){
        dialogModel.dialogHeaderText = headerText
        dialogModel.dialogSubHeaderText = subHeaderText
        dialogModel.dialogText = dialogText
        dialogModel.imageResource = imageResource
    }
    fun showMessageDialog(){
        isMessageDialogShown = true
    }

    fun hideMessageDialog(){
        isMessageDialogShown = false
    }

}